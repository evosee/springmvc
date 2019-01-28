package com.spring.my.annotion;

import java.lang.annotation.*;

/**
 * @Author: chensai
 * @Date: 2019/1/25 16:31
 * @Version 1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    String value();
}
