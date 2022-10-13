package com.edu.exmg.admin.api;


import com.edu.exmg.admin.bean.Department;
import com.edu.exmg.admin.bean.DepartmentExample;
import com.edu.exmg.admin.bean.User;
import com.edu.exmg.admin.bean.UserExample;
import com.edu.exmg.admin.bean.UserRole;
import com.edu.exmg.admin.bean.UserRoleExample;
import com.edu.exmg.admin.config.CurrentUser;
import com.edu.exmg.admin.config.IgnorePermission;
import com.edu.exmg.admin.dto.DepartmentDTO;
import com.edu.exmg.admin.dto.UserDTO;
import com.edu.exmg.admin.enums.ErrorEnum;
import com.edu.exmg.admin.enums.SessionKeyEnum;
import com.edu.exmg.admin.mapper.DepartmentMapper;
import com.edu.exmg.admin.mapper.RoleMapper;
import com.edu.exmg.admin.mapper.UserMapper;
import com.edu.exmg.admin.mapper.UserRoleMapper;
import com.edu.exmg.admin.query.UserQuery;
import com.edu.exmg.admin.vo.DeptRoleEntity;
import com.edu.exmg.admin.vo.UserEntity;
import com.edu.exmg.common.exception.BizException;
import com.edu.exmg.common.query.PageQuery;
import com.edu.exmg.common.util.CollectionUtils;
import com.edu.exmg.common.util.ConverterUtils;
import com.edu.exmg.common.util.StringUtils;
import com.edu.exmg.common.vo.IResult;
import com.edu.exmg.common.vo.PageVO;
import com.edu.exmg.common.vo.Result;
import com.edu.exmg.common.vo.ResultList;
import com.edu.exmg.common.vo.ResultPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 本地管理的用户机构管理接口
 */
@Slf4j
@RestController
@RequestMapping("admin")
public class LocalDeptUserAPI {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 首次创建用户接口
     * @param userDTO
     * @return
     */
    @PostMapping("user/create")
    @Transactional(rollbackFor = Exception.class)
    public IResult createUser(UserDTO userDTO) throws BizException {
        userDTO.setUserId(null);
        userDTO.setStatus(1);
        if (StringUtils.isEmpty(userDTO.getDeptId()) && StringUtils.isEmpty(userDTO.getRoleId())) {
            return IResult.error(ErrorEnum.ERRCODE_0001);
        }
        User user = ConverterUtils.copyBean(userDTO, User.class);
        String userId = RandomStringUtils.randomNumeric(9);
        String password = RandomStringUtils.randomAlphabetic(8);
        user.setUserId(userId);
        user.setPassword(password);

        userMapper.insertSelective(user);
        return assignDeptRole(userId, userDTO.getDeptId(), userDTO.getRoleId(), true);
    }

    @PostMapping("user/page")
    public IResult userPage(UserQuery userQuery) {

        if (userQuery.getCurrentPage() == null || userQuery.getCurrentPage() <= 0) {
            userQuery.setCurrentPage(PageQuery.DEFAULT_CURRENTPAGE);
        }
        if (userQuery.getPageSize() == null || userQuery.getPageSize() <= 0) {
            userQuery.setPageSize(PageQuery.DEFAULT_PAGESIZE);
        }

        PageHelper.startPage(userQuery.getCurrentPage(), userQuery.getPageSize(), "create_time");

        List<User> result = null;
        if (StringUtils.isEmpty(userQuery.getDeptId())) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andUserIdEqualTo(userQuery.getUserId()).andUserNameLikePrefix(userQuery.getUsername());
            result = userMapper.selectByExample(userExample);
        } else {
            result = userMapper.selectDeptUser(userQuery);
        }

        PageInfo<User> pageInfo = new PageInfo<>(result);

        PageVO<User> pageVO = new PageVO<>(pageInfo, pageInfo.getList());

        return ResultPage.success(pageVO);
    }


    /**
     * 用户分配接口
     * @param userId
     * @param deptId
     * @param roleId
     * @param isDefault
     * @return
     */
    @PostMapping("user/assign")
    @Transactional(rollbackFor = Exception.class)
    public IResult assignDeptRole(String userId, String deptId, String roleId, Boolean isDefault) throws BizException {
        if (StringUtils.isEmpty(userId)) {
            throw new BizException(ErrorEnum.ERRCODE_0002);
        }
        isDefault = isDefault == null ? false : isDefault;
        if (!userMapper.existsWithPrimaryKey(userId)) {
            throw new BizException(ErrorEnum.ERRCODE_0002);
        }
        if (StringUtils.isNotEmpty(deptId) && !departmentMapper.existsWithPrimaryKey(deptId)) {
            throw new BizException(ErrorEnum.ERRCODE_0003);
        }
        if (StringUtils.isNotEmpty(roleId) && !roleMapper.existsWithPrimaryKey(roleId)) {
            throw new BizException(ErrorEnum.ERRCODE_0004);
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        deptId = StringUtils.defaultString(deptId, "0");
        roleId = StringUtils.defaultString(roleId, "0");
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId).andDeptIdEqualTo(deptId).andRoleIdEqualTo(roleId);
        long count = userRoleMapper.countByExample(example);
        if (count > 0) {
            return IResult.error(ErrorEnum.ERRCODE_0005);
        }

        if (isDefault) {
            userRole.setIsDefault(false);
            example = new UserRoleExample();
            example.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true);
            userRoleMapper.updateByExampleSelective(userRole, example);
        }
        userRole.setDeptId(deptId);
        userRole.setRoleId(roleId);
        userRole.setIsDefault(isDefault);
        userRole.setStatus(0);

        try {
            userRoleMapper.insertSelective(userRole);
        } catch (DuplicateKeyException e) {
            log.warn("userRoleMapper.insertSelective 插入失败： {} - {}", userRole, e.getMessage());
            return IResult.error(ErrorEnum.ERRCODE_0005);
        }
        return Result.success("success");
    }

    /**
     * 解除用户机构角色分配
     * @author autom
     * @date 2021/12/29 下午9:19
     * @param userId
     * @param deptId
     * @param roleId
     * @return com.edu.scnu.common.vo.IResult
     */
    @PostMapping("user/resign")
    public IResult resignDeptRole(String userId, String deptId, String roleId) {

        if (StringUtils.isEmpty(userId)) {
            return IResult.error(ErrorEnum.ERRCODE_0002);
        }

        if (!userMapper.existsWithPrimaryKey(userId)) {
            return IResult.error(ErrorEnum.ERRCODE_0002);
        }

        deptId = StringUtils.defaultString(deptId, "0");
        roleId = StringUtils.defaultString(roleId, "0");
        if (StringUtils.isNotEmpty(deptId) && !"0".equals(deptId) && !departmentMapper.existsWithPrimaryKey(deptId)) {
            return IResult.error(ErrorEnum.ERRCODE_0003);
        }
        if (StringUtils.isNotEmpty(roleId) && !"0".equals(roleId) && !roleMapper.existsWithPrimaryKey(roleId)) {
            return IResult.error(ErrorEnum.ERRCODE_0004);
        }

        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId).andDeptIdEqualTo(deptId).andRoleIdEqualTo(roleId);

        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userRoles)) {
            return IResult.error(ErrorEnum.ERRCODE_0016);
        }
        UserRole userRole = userRoles.get(0);
        Boolean isDefault = userRole.getIsDefault();
        if (isDefault != null && isDefault) {
            return IResult.error(ErrorEnum.ERRCODE_0017);
        }
        userRoleMapper.deleteByExample(example);
        return Result.success("success");

    }

    @PostMapping("user/changePasswd")
    public IResult changePasswd(String userId, String oldPasswd, String newPasswd, @CurrentUser UserEntity userEntity) {
        User current = userEntity.getUser();
        if (!current.getUserId().equals(userId)) {
            return IResult.error(ErrorEnum.ERRCODE_0010);
        }

        User user = new User();
        user.setUserId(userId);
        User validation = userMapper.selectOne(user);
        if (!validation.getPassword().equals(oldPasswd)) {
            return IResult.error(ErrorEnum.ERRCODE_0006);
        }
        user.setPassword(newPasswd);
        user.setStatus(0);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success("修改成功");
    }

    @PostMapping("user/resetPasswd")
    public IResult resetPasswd(String userId) {
        String password = RandomStringUtils.randomAlphabetic(8);
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setStatus(1);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success("重置成功");
    }

    /**
     * 用户登陆模块
     * @param userId
     * @param password
     * @param session
     * @return
     */
    @IgnorePermission(loginCheck = false, permissionCheck = false)
    @PostMapping("user/login")
    public IResult userLogin(String userId, String password, HttpSession session) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return IResult.error(ErrorEnum.ERRCODE_0007);
        }
        session.setAttribute(SessionKeyEnum.USER_ID.name(), userId);
        IResult getRes = getCurrentUser(null, session);
        if (getRes.isSuccess()) {
            Result<UserEntity> userResult = (Result<UserEntity>) getRes;
            UserEntity userEntity = userResult.getData();
            session.setAttribute(SessionKeyEnum.CURRENT_USER.name(), userEntity);
            return Result.success(CollectionUtils.singletonMap("token", session.getId()));
        } else {
            session.invalidate();
            return getRes;
        }
    }

    @IgnorePermission(permissionCheck = false)
    @PostMapping("user/logout")
    public IResult userLogout(HttpSession session) {
        session.invalidate();
        return Result.success("success");
    }

    @PostMapping("user/detail")
    public IResult userDetail(String userId) {
        UserEntity userEntity = new UserEntity();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(null);
        userEntity.setUser(user);
        List<DeptRoleEntity> dpList = getDeptRole(userId);
        userEntity.setDeptRoleList(dpList);
        return Result.success(userEntity);

    }

    /**
     * 获取当前登陆用户信息
     * @param userEntity
     * @param session
     * @return
     */
    @IgnorePermission(permissionCheck = false)
    @PostMapping("user/current")
    public IResult getCurrentUser(@CurrentUser UserEntity userEntity, HttpSession session) {
        if (userEntity == null) {
            String userId = StringUtils.toString(session.getAttribute(SessionKeyEnum.USER_ID.name()));
            if (StringUtils.isEmpty(userId)) {
                return IResult.error(ErrorEnum.ERRCODE_0008);
            }
            userEntity = new UserEntity();
            User user = userMapper.selectByPrimaryKey(userId);
            user.setPassword(null);
            userEntity.setUser(user);
            List<DeptRoleEntity> dpList = getDeptRole(userId);
            if (CollectionUtils.isEmpty(dpList)) {
                return IResult.error(ErrorEnum.ERRCODE_0009);
            }
            userEntity.setDeptRoleList(dpList);
            DeptRoleEntity defaultDp = selectDefault(dpList);
            userEntity.setCurrentDept(defaultDp.getDepartment());
            userEntity.setCurrentRole(defaultDp.getRole());

        }
        return Result.success(userEntity);
    }

    private List<DeptRoleEntity> getDeptRole(String userId) {
        List<DeptRoleEntity> deptRoleEntity = userRoleMapper.joinWithDeptRole(userId);
        return deptRoleEntity;
    }

    private DeptRoleEntity selectDefault(List<DeptRoleEntity> dpList) {
        if (CollectionUtils.isEmpty(dpList)) {
            return null;
        }
        for (DeptRoleEntity deptRoleEntity : dpList) {
            if (deptRoleEntity.getIsDefault()) {
                return deptRoleEntity;
            }
        }
        return dpList.get(0);
    }

    @PostMapping("dept/create")
    public IResult createDept(DepartmentDTO departmentDTO) {
        Department department = ConverterUtils.copyBean(departmentDTO, Department.class);
        String parentId = departmentDTO.getParentId();
        if ("0".equals(parentId)) {
            department.setDeptLevel(1);
        } else {
            Department pdq = new Department();
            pdq.setDeptId(parentId);
            Department pd = departmentMapper.selectOne(pdq);
            department.setDeptLevel(pd.getDeptLevel() + 1);
        }

        String deptId = RandomStringUtils.randomNumeric(6);
        department.setDeptId(deptId);
        departmentMapper.insertSelective(department);
        return Result.success(department);
    }

    @PostMapping("dept/list")
    public IResult deptList(String parentId) {

        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<Department> departments = departmentMapper.selectByExample(example);
        return ResultList.success(departments);

    }

}
