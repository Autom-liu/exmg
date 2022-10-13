package com.edu.exmg.admin.api;


import com.edu.exmg.admin.bean.Department;
import com.edu.exmg.admin.bean.Menu;
import com.edu.exmg.admin.bean.MenuExample;
import com.edu.exmg.admin.bean.Permission;
import com.edu.exmg.admin.bean.PermissionExample;
import com.edu.exmg.admin.bean.Role;
import com.edu.exmg.admin.bean.RoleExample;
import com.edu.exmg.admin.bean.User;
import com.edu.exmg.admin.config.CurrentUser;
import com.edu.exmg.admin.config.IgnorePermission;
import com.edu.exmg.admin.dto.MenuDTO;
import com.edu.exmg.admin.dto.RoleDTO;
import com.edu.exmg.admin.enums.ErrorEnum;
import com.edu.exmg.admin.enums.MenuTypeEnum;
import com.edu.exmg.admin.mapper.DepartmentMapper;
import com.edu.exmg.admin.mapper.MenuMapper;
import com.edu.exmg.admin.mapper.PermissionMapper;
import com.edu.exmg.admin.mapper.RoleMapper;
import com.edu.exmg.admin.mapper.UserMapper;
import com.edu.exmg.admin.mapper.UserRoleMapper;
import com.edu.exmg.admin.vo.UserEntity;
import com.edu.exmg.common.enums.DefaultSysErrorEnum;
import com.edu.exmg.common.query.PageQuery;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限管理接口，获取用户权限信息
 */
@Slf4j
@RestController
@RequestMapping("admin")
public class RoleMenuPermissionAPI {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @PostMapping("role/create")
    public IResult createRole(RoleDTO roleDTO) {
        Role role = ConverterUtils.copyBean(roleDTO, Role.class);
        roleMapper.insertSelective(role);
        return Result.success("success");
    }

    @PostMapping("role/page")
    public IResult rolePage(String roleId, String roleName, PageQuery pageQuery) {
        Integer currentPage = pageQuery.getCurrentPage();
        Integer pageSize = pageQuery.getPageSize();
        if (currentPage == null || currentPage <= 0) {
            currentPage = PageQuery.DEFAULT_CURRENTPAGE;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = PageQuery.DEFAULT_PAGESIZE;
        }

        PageHelper.startPage(currentPage, pageSize, "create_time");
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleIdEqualTo(roleId).andRoleNameEqualTo(roleName);
        List<Role> result = roleMapper.selectByExample(roleExample);
        PageInfo<Role> pageInfo = new PageInfo<>(result);

        PageVO<Role> pageVO = new PageVO<>(pageInfo, pageInfo.getList());

        return ResultPage.success(pageVO);

    }

    @PostMapping("menu/create")
    @Transactional(rollbackFor = Exception.class)
    public IResult createMenu(MenuDTO menuDTO) {

        if (MenuTypeEnum.MENU_TYPE_0.getCode().equals(menuDTO.getMenuType()) && StringUtils.isNotEmpty(menuDTO.getUrl())) {
            return IResult.error(ErrorEnum.ERRCODE_0013);
        }

        Menu upd = new Menu();
        upd.setMenuId(menuDTO.getParentId());
        upd.setLeaf(false);
        menuMapper.updateByPrimaryKeySelective(upd);

        Menu menu = ConverterUtils.copyBean(menuDTO, Menu.class);
        menu.setLeaf(true);
        menuMapper.insertSelective(menu);
        return Result.success("success");
    }

    @PostMapping("menu/edit")
    @Transactional(rollbackFor = Exception.class)
    public IResult editMenu(MenuDTO menuDTO) {

        if (MenuTypeEnum.MENU_TYPE_0.getCode().equals(menuDTO.getMenuType()) && StringUtils.isNotEmpty(menuDTO.getUrl())) {
            return IResult.error(ErrorEnum.ERRCODE_0013);
        }

        Menu menu = ConverterUtils.copyBean(menuDTO, Menu.class);
        menuMapper.updateByPrimaryKeySelective(menu);
        return Result.success("success");
    }

    /**
     * 删除菜单接口
     * @author autom
     * @date 2021/12/28 下午9:04
     * @param menuId
     * @return com.edu.scnu.common.vo.IResult
     */
    @PostMapping("menu/remove")
    @Transactional(rollbackFor = Exception.class)
    public IResult removeMenu(String menuId) {

        Menu currentMenu = menuMapper.selectByPrimaryKey(menuId);
        if (currentMenu == null) {
            return IResult.error(ErrorEnum.ERRCODE_0014);
        }

        if (!currentMenu.getLeaf()) {
            return IResult.error(ErrorEnum.ERRCODE_0015);
        }

        menuMapper.deleteByPrimaryKey(menuId);

        String parentId = currentMenu.getParentId();
        MenuExample example = new MenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        long count = menuMapper.countByExample(example);
        if (count == 0) {
            Menu bean = new Menu();
            bean.setMenuId(parentId);
            bean.setLeaf(true);
            menuMapper.updateByPrimaryKeySelective(bean);
        }
        return Result.success("success");
    }

    @PostMapping("menu/list")
    public IResult menuList(String parentId) {
        MenuExample example = new MenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<Menu> menuList = menuMapper.selectByExample(example);
        return ResultList.success(menuList);
    }

    /**
     * 用户分配接口
     * @param userId
     * @param deptId
     * @param roleId
     * @param isDefault
     * @return
     */
    @PostMapping("role/assign")
    public IResult assignDeptRole(String userId, String deptId, String roleId, Boolean isDefault) {
        return IResult.error(DefaultSysErrorEnum.INTERFACE_NOT_FOUND, "暂不支持");
    }

    @PostMapping("permission/assign")
    @Transactional
    public IResult assignPermission(String userId, String deptId, String roleId, String[] menuIds) {

        if (StringUtils.isNotEmpty(userId) && !userMapper.existsWithPrimaryKey(userId)) {
            return IResult.error(ErrorEnum.ERRCODE_0002);
        }

        if (StringUtils.isNotEmpty(deptId) && !departmentMapper.existsWithPrimaryKey(deptId)) {
            return IResult.error(ErrorEnum.ERRCODE_0003);
        }

        if (StringUtils.isNotEmpty(roleId) && !roleMapper.existsWithPrimaryKey(roleId)) {
            return IResult.error(ErrorEnum.ERRCODE_0004);
        }

        String ids = StringUtils.join(menuIds, ',');
        List<Menu> menuList = menuMapper.selectByIds(ids);
        if (menuList.size() != menuIds.length) {
            return IResult.error(ErrorEnum.ERRCODE_0011);
        }

        userId = StringUtils.defaultIfBlank(userId, "0");
        deptId = StringUtils.defaultString(deptId, "0");
        roleId = StringUtils.defaultString(roleId, "0");

        PermissionExample example = new PermissionExample();
        example.createCriteria().andUserIdEqualTo(userId).andDeptIdEqualTo(deptId).andRoleIdEqualTo(roleId);
        permissionMapper.deleteByExample(example);

        for (String menuId : menuIds) {
            Permission permission = new Permission();
            permission.setDeptId(deptId);
            permission.setUserId(userId);
            permission.setRoleId(roleId);
            permission.setMenuId(menuId);
            try {
                permissionMapper.insertSelective(permission);
            } catch (DuplicateKeyException e) {
                // ignore duplicate
                log.warn("ignore duplicate: {} - {}", permission, e.getMessage());
            }
        }
        return Result.success("success");
    }

    @IgnorePermission(permissionCheck = false)
    @PostMapping("permission/current")
    public IResult fetchPermission(@CurrentUser UserEntity userEntity, HttpSession session) {

        User user = userEntity.getUser();
        String userId = user.getUserId();

        Department currentDept = userEntity.getCurrentDept();
        Role currentRole = userEntity.getCurrentRole();
        String deptId = currentDept.getDeptId();
        String roleId = currentRole.getRoleId();
        Permission query = new Permission();
        query.setUserId(userId);
        query.setDeptId(deptId);
        query.setRoleId(roleId);
        List<Menu> menuList = permissionMapper.joinWithMenu(query);
        if (CollectionUtils.isEmpty(menuList)) {
            query.setUserId("0");
            menuList = permissionMapper.joinWithMenu(query);
        }

        List<String> permissionList = menuList.stream().map(Menu::getUrl).collect(Collectors.toList());

        session.setAttribute("CURRENT_PERMISSION", permissionList);

        return ResultList.success(permissionList);
    }


}
