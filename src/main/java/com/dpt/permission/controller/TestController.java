package com.dpt.permission.controller;

import com.dpt.permission.common.ApplicationContextHelper;
import com.dpt.permission.common.JsonData;
import com.dpt.permission.dao.SysAclModuleMapper;
import com.dpt.permission.exception.ParamException;
import com.dpt.permission.exception.PermissionException;
import com.dpt.permission.model.SysAclModule;
import com.dpt.permission.model.Test;
import com.dpt.permission.param.TestVo;
import com.dpt.permission.util.BeanValidator;
import com.dpt.permission.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试Controller.
 *
 * @author 邓鹏涛
 * @date 2019/1/20 17:00
 */

@RequestMapping("/test")
@Slf4j
@Controller
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        return JsonData.success("hello");
        //throw new RuntimeException("test exception");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        log.info("validate");
        /*try {
            Map<String, String> map = BeanValidator.validateObject(vo);
            if (MapUtils.isNotEmpty(map)) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    log.info("{}->{}",entry.getKey(),entry.getValue());
                }
            }
        } catch (Exception e) {

        }*/
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.object2String(module));
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }

    @RequestMapping("/test1.json")
    @ResponseBody
    public Test test1() {
        Test test = new Test();
        List<String> num = new ArrayList<String>();
        for (int i = 0; i < 26; i++) {
            num.add("A" + i);
        }
        test.setNum(num);
        return test;
    }

}
