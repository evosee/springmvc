package com.spring.my.core;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * @Author: chensai
 * @Date: 2019/1/28 9:27
 * @Version 1.0
 */
@Setter
@Getter
public class ObjectMethod {
    private String url;
    private Method method;
    private Object object;
}
