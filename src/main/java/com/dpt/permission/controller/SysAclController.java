package com.dpt.permission.controller;

import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.common.JsonData;
import com.dpt.permission.model.SysRole;
import com.dpt.permission.param.AclParam;
import com.dpt.permission.service.SysAclService;
import com.dpt.permission.service.SysRoleAclService;
import com.dpt.permission.service.SysRoleService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 权限点Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/25 20:50
 */
@RequestMapping("/sys/acl")
@Controller
public class SysAclController {

    @Autowired
    private SysAclService sysAclService;
    @Autowired
    private SysRoleService sysRoleService;


    /**
     *新增权限点.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveAcl(AclParam param) {
        sysAclService.save(param);
        return JsonData.success();
    }

    /**
     *更新权限点.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData updateAcl(AclParam param) {
        sysAclService.update(param);
        return JsonData.success();
    }

    /**
     * 分页查询权限点.
     * @param aclModuleId
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData list(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery page) {
        return JsonData.success(sysAclService.getPageByAclId(aclModuleId, page));
    }

    /**
     * 获取用户和权限点的关系.
     * @param aclId
     * @return
     */
    @ResponseBody
    @RequestMapping("/acls.json")
    public JsonData acls(@RequestParam("aclId") int aclId) {
        List<SysRole> roleList = sysRoleService.getRoleListByAclId(aclId);
        Map<String, Object> map = Maps.newHashMap();
        map.put("roles", roleList);
        map.put("users", sysRoleService.getUserListByRoleList(roleList));
        return JsonData.success(map);
    }

}
