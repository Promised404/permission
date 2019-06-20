package com.dpt.permission.controller;

import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.common.JsonData;
import com.dpt.permission.param.SearchLogParam;
import com.dpt.permission.service.SysLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/31 12:29
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/log.page")
    public ModelAndView page() {
        return new ModelAndView("log");
    }

    @ResponseBody
    @RequestMapping("/page.json")
    public JsonData searchPage(SearchLogParam param, PageQuery page) {
        return JsonData.success(sysLogService.searchPageList(param, page));
    }

    @ResponseBody
    @RequestMapping("/recover.json")
    public JsonData recover(@RequestParam("id") int id) {
        sysLogService.recover(id);
        return JsonData.success();
    }

}
