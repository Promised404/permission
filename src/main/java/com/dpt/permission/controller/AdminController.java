package com.dpt.permission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员Controller
 *
 * @author 邓鹏涛
 * @date 2019/1/23 20:14
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    /**
     *
     * @return
     */
    @RequestMapping("/index.page")
    public ModelAndView index() {
        return new ModelAndView("admin");
    }

}
