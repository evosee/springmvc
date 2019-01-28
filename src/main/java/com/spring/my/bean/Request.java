package com.spring.my.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: chensai
 * @Date: 2019/1/25 17:52
 * @Version 1.0
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String url;
    private String params;
}
