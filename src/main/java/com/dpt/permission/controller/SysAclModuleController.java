package com.dpt.permission.controller;

import com.dpt.permission.common.JsonData;
import com.dpt.permission.dto.AclModuleLevelDto;
import com.dpt.permission.dto.DeptLevelDto;
import com.dpt.permission.param.AclModuleParam;
import com.dpt.permission.param.DeptParam;
import com.dpt.permission.service.SysAclModuleService;
import com.dpt.permission.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限模块Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/25 11:32
 */
@RequestMapping("/sys/aclModule")
@Controller
@Slf4j
public class SysAclModuleController {

    @Autowired
    private SysAclModuleService sysAclModuleService;
    @Autowired
    private SysTreeService sysTreeService;

    /**
     * 权限模块页面.
     * @return
     */
    @RequestMapping("/acl.page")
    public ModelAndView page() {
        return new ModelAndView("acl");
    }

    /**
     *新增权限模块.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveAclModule(AclModuleParam param) {
        sysAclModuleService.save(param);
        return JsonData.success();
    }

    /**
     * 权限模块树.
     * @return
     */
    @ResponseBody
    @RequestMapping("/tree.json")
    public JsonData tree() {
        List<AclModuleLevelDto> aclModuleLevelDto = sysTreeService.aclModuleTree();
        return JsonData.success(aclModuleLevelDto);
    }

    /**
     *更新权限模块.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData updateAclModule(AclModuleParam param) {
        sysAclModuleService.update(param);
        return JsonData.success();
    }

    /**
     *更新权限模块.
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete.json")
    public JsonData deleteAclModule(@RequestParam("id") int id) {
        sysAclModuleService.delete(id);
        return JsonData.success();
    }

}
