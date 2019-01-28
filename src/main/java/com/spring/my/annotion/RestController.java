package com.spring.my.annotion;

import java.lang.annotation.*;

/**
 * @Author: chensai
 * @Date: 2019/1/25 16:23
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RestController {
    String value();
}
