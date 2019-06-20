package com.dpt.permission.controller;

import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.beans.PageResult;
import com.dpt.permission.common.JsonData;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.param.DeptParam;
import com.dpt.permission.param.UserParam;
import com.dpt.permission.service.SysCoreService;
import com.dpt.permission.service.SysRoleService;
import com.dpt.permission.service.SysTreeService;
import com.dpt.permission.service.SysUserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 用户Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/23 12:26
 */
@RequestMapping("/sys/user")
@Controller
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysTreeService sysTreeService;
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 跳转无权限页面
     * @return
     */
    @RequestMapping("/noAuth.page")
    public ModelAndView noAuth(){
        return new ModelAndView("noAuth");
    }

    /**
     *新增用户.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveUser(UserParam param) {
        sysUserService.save(param);
        return JsonData.success();
    }

    /**
     *更新用户.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData updateUser(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    /**
     * 分页查询用户.
     * @param deptId
     * @param pageQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery pageQuery) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, pageQuery);
        return JsonData.success(result);
    }

    /**
     * 获取用户和权限点的关系.
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/acls.json")
    public JsonData acls(@RequestParam("userId") int userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("acls", sysTreeService.userAclTree(userId));
        map.put("roles", sysRoleService.getRoleListByUserId(userId));
        return JsonData.success(map);
    }


}
