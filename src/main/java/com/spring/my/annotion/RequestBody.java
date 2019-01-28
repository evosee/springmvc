package com.spring.my.annotion;

import java.lang.annotation.*;

/**
 * @Author: chensai
 * @Date: 2019/1/25 16:37
 * @Version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestBody {
}
