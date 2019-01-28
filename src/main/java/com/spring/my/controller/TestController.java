package com.spring.my.controller;

import com.spring.my.annotion.RequestBody;
import com.spring.my.annotion.RequestMapping;
import com.spring.my.annotion.RestController;
import com.spring.my.bean.Params;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Author: chensai
 * @Date: 2019/1/25 16:35
 * @Version 1.0
 */
@RestController("testController")
@RequestMapping("test")
public class TestController {

    public Map<String, String> test(@RequestBody Params params) {
        try {
            System.out.println("执行成功了哦");
            return BeanUtils.describe(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/test2")
    public Map<String, String> test2(@RequestBody Params params) {
        try {
            return BeanUtils.describe(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
