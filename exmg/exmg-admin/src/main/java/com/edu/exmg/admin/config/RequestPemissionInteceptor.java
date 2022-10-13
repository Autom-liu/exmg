package com.edu.exmg.admin.config;

import com.edu.exmg.admin.enums.ErrorEnum;
import com.edu.exmg.admin.enums.SessionKeyEnum;
import com.edu.exmg.common.enums.IErrorEnum;
import com.edu.exmg.common.util.JsonUtils;
import com.edu.exmg.common.vo.IResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class RequestPemissionInteceptor implements HandlerInterceptor {


    /**
     *
     * @author autom
     * @date 2021/12/28 下午8:28
     * @param request
     * @param response
     * @param handler
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            log.warn("handler instanceof HandlerMethod false : {}", handler);
            errorReturn(response, ErrorEnum.ERRCODE_0008);
            return false;
        }
        HandlerMethod method = (HandlerMethod) handler;
        IgnorePermission ignorePermission = method.getMethodAnnotation(IgnorePermission.class);
        boolean loginCheck = ignorePermission == null || ignorePermission.loginCheck();
        boolean permissionCheck = ignorePermission == null || ignorePermission.permissionCheck();
        HttpSession session = request.getSession(false);
        if (session == null && loginCheck) {
            errorReturn(response, ErrorEnum.ERRCODE_0008);
            return false;
        }
        if (session == null) {
            return true;
        }
        Object currentUser = session.getAttribute(SessionKeyEnum.CURRENT_USER.name());
        if (currentUser == null && loginCheck) {
            errorReturn(response, ErrorEnum.ERRCODE_0008);
            return false;
        }
        // List<String> currentPermission = (List<String>) session.getAttribute("CURRENT_PERMISSION");
        // String uri = request.getRequestURI();
        //
        // if (CollectionUtils.isEmpty(currentPermission) && permissionCheck) {
        //     throw new BizException(ErrorEnum.ERRCODE_0012);
        // }
        // if (CollectionUtils.isEmpty(currentPermission)) {
        //     return true;
        // }
        // if (!CollectionUtils.contains(currentPermission.iterator(), uri) && permissionCheck) {
        //     throw new BizException(ErrorEnum.ERRCODE_0012);
        // }

        return true;
    }

    /**
     *
     * @author autom
     * @date 2021/12/28 下午8:28
     * @param response
     * @param errorEnum
     * @return void
     */
    public void errorReturn(HttpServletResponse response, IErrorEnum errorEnum) {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        
        try (PrintWriter writer = response.getWriter()) {
            writer.write(JsonUtils.serialize(IResult.error(errorEnum)));
        } catch (IOException e) {

        }
    }
}
