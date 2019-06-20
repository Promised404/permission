package com.dpt.permission.controller;

import com.dpt.permission.common.JsonData;
import com.dpt.permission.enums.SysUserEnum;
import com.dpt.permission.model.SysRole;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.param.AclParam;
import com.dpt.permission.param.RoleParam;
import com.dpt.permission.service.*;
import com.dpt.permission.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/26 10:48
 */
@RequestMapping("/sys/role")
@Controller
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysTreeService sysTreeService;
    @Autowired
    private SysRoleUserService sysRoleUserService;
    @Autowired
    private SysRoleAclService sysRoleAclService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/role.page")
    public ModelAndView page() {
        return new ModelAndView("role");
    }

    /**
     *新增角色.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveAcl(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    /**
     *更新角色.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData updateAcl(RoleParam param) {
        sysRoleService.update(param);
        return JsonData.success();
    }

    /**
     * 查询所有的角色.
     * @return
     */
    @ResponseBody
    @RequestMapping("/list.json")
    public JsonData list() {
        return JsonData.success(sysRoleService.getAll());
    }


    /**
     * 角色树.
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/roleTree.json")
    public JsonData roleTree(@RequestParam("roleId") int roleId) {
        return JsonData.success(sysTreeService.roleTree(roleId));
    }

    /**
     * 改变角色权限关系.
     * @param roleId
     * @param aclIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeAcls.json")
    public JsonData changeAcls(@RequestParam(value = "roleId") int roleId,
                               @RequestParam(value = "aclIds", required = false,defaultValue = "") String aclIds) {
        List<Integer> aclIdList = StringUtil.splitToListInt(aclIds);
        sysRoleAclService.changeRoleAcls(roleId, aclIdList);
        return JsonData.success();
    }

    /**
     * 改变角色用户关系.
     * @param roleId
     * @param userIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeUsers.json")
    public JsonData changeUsers(@RequestParam(value = "roleId") int roleId,
                                @RequestParam(value = "userIds", required = false,defaultValue = "") String userIds) {
        List<Integer> userIdList = StringUtil.splitToListInt(userIds);
        sysRoleUserService.changeRoleUsers(roleId, userIdList);
        return JsonData.success();
    }

    /**
     * 获取所有用户.
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/users.json")
    public JsonData users(@RequestParam(value = "roleId") int roleId) {
        List<SysUser> selectedUserList = sysRoleUserService.getListByRoleId(roleId);
        List<SysUser> allUserList = sysUserService.getAll();
        List<SysUser> unSelectedUserList = Lists.newArrayList();
        Set<Integer> selectedUserIdSet = selectedUserList.stream()
                .map(sysUser -> sysUser.getId())
                .collect(Collectors.toSet());
        for (SysUser sysUser : allUserList) {
            if (sysUser.getStatus() == SysUserEnum.STATUS_NORMAL.getCode() &&
                    !selectedUserIdSet.contains(sysUser.getId())) {
                unSelectedUserList.add(sysUser);
            }
        }
        Map<String, List<SysUser>> map = Maps.newHashMap();
        map.put("selected", selectedUserList);
        map.put("unselected",unSelectedUserList);
        return JsonData.success(map);
    }


}
