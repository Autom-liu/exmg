package com.edu.exmg.gateway.api;


import com.edu.exmg.common.util.JsonUtils;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.gateway.bean.User;
import com.edu.exmg.gateway.bean.UserRole;
import com.edu.exmg.gateway.config.FileUploadConfig;
import com.edu.exmg.gateway.dto.WxUserSession;
import com.edu.exmg.gateway.enums.ErrorEnums;
import com.edu.exmg.gateway.mapper.UserMapper;
import com.edu.exmg.gateway.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author autom
 * @date 2021-04-20
 **/
@Slf4j
@RestController
@RequestMapping("wx")
public class WxAPI {

    private static final String WXLOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileUploadConfig uploadConfig;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @PostMapping("login")
    @Transactional(rollbackFor = Exception.class)
    public IResult login(@RequestBody String code, HttpSession session) {
        if (StringUtils.isEmpty(code)) {
            return IResult.error(ErrorEnums.ERRCODE_0001, "code");
        }
        Object currentUser = session.getAttribute("CURRENT_USER");
        if (currentUser != null) {
            return Result.success(session.getId());
        }

        String baseUrl = WXLOGIN_URL;
        code = code.replace("=", "");

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("appid", "wx6e1756f4a51b034c")
                .queryParam("secret", "e963ea721a7f3f91fe2e9055371a9c13")
                .queryParam("js_code", code)
                .queryParam("grant_type", "authorization_code")
                .build().encode().toUri();

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            String body = response.getBody();
            WxUserSession wxUserSession = JsonUtils.parse(body, WxUserSession.class);
            log.info("wxUserSession: {}", wxUserSession);
            User user = userMapper.selectByOpenid(wxUserSession.getOpenid());
            if (user == null) {
                user = createUser(wxUserSession);
            }
            return Result.success(user);
        } else {
            return IResult.error(ErrorEnums.ERRCODE_0002);
        }
    }

    private User createUser(WxUserSession wxUserSession) {
        String userId = RandomStringUtils.random(8, false, true);
        String password = RandomStringUtils.random(10, true, true);
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setUserName(wxUserSession.getOpenid());
        user.setWxopenid(wxUserSession.getOpenid());
        user.setStatus(0);
        userMapper.insertSelective(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId("9000");
        userRole.setDeptId("0");
        userRole.setIsDefault(true);
        userRole.setStatus(0);
        userRoleMapper.insert(userRole);
        return user;
    }


    @PostMapping("savetofile")
    public IResult savetofile(@RequestParam("file") MultipartFile uploadFile) throws IOException {
        String originalFilename = uploadFile.getOriginalFilename();
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = RandomStringUtils.randomNumeric(6);
        String fileName = timestamp + random+ originalFilename;
        String uploadPath = uploadConfig.getAbsolutePath();
        log.info("uploadPath: {}, fileName: {}", uploadPath, fileName);
        Path savePath = Paths.get(uploadPath, fileName);
        // uploadFile.transferTo(savePath);
        String relativePath = uploadConfig.getRelativePath();
        Path relPath = Paths.get(relativePath, fileName);
        return Result.success(relPath.toString());
    }

}
