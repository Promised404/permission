package com.dpt.permission.controller;

import com.dpt.permission.common.JsonData;
import com.dpt.permission.dto.DeptLevelDto;
import com.dpt.permission.param.DeptParam;
import com.dpt.permission.service.SysDeptService;
import com.dpt.permission.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 部门Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/21 17:30
 */
@Controller
@RequestMapping("/sys/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysTreeService sysTreeService;

    /**
     * 部门页面.
     * @return
     */
    @RequestMapping("/dept.page")
    public ModelAndView page() {
        return new ModelAndView("dept");
    }

    /**
     *新增部门.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/save.json")
    public JsonData saveDept(DeptParam param) {
        sysDeptService.save(param);
        return JsonData.success();
    }

    /**
     * 部门树.
     * @return
     */
    @ResponseBody
    @RequestMapping("/tree.json")
    public JsonData tree() {
        List<DeptLevelDto> dtoList = sysTreeService.deptTree();
        return JsonData.success(dtoList);
    }

    /**
     *更新部门.
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update.json")
    public JsonData updateDept(DeptParam param) {
        sysDeptService.update(param);
        return JsonData.success();
    }

    /**
     *删除部门.
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete.json")
    public JsonData deleteDept(@RequestParam("id") int id) {
        sysDeptService.delete(id);
        return JsonData.success();
    }

}
