package com.spring.my.core;

import com.alibaba.fastjson.JSON;
import com.spring.my.annotion.RequestBody;
import com.spring.my.bean.Request;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @Author: chensai
 * @Date: 2019/1/25 17:51
 * @Version 1.0
 */
public class Dispatcher {

    public void dispatch(Request request) {
        ControllerScan controllerScan = new ControllerScan();
        List<String> s = controllerScan.getClasssByPackage();
        controllerScan.setObjects(controllerScan.scan(s));
        Map<String, ObjectMethod> map = controllerScan.getUrls();
        String url = request.getUrl();
        ObjectMethod objectMethod = map.get(url);
        if (objectMethod == null) {
            throw new RuntimeException("404");
        }
        Method method = objectMethod.getMethod();
        Annotation[][] annotations = method.getParameterAnnotations();
        String params = request.getParams();
        for (Annotation[] annotations1 : annotations) {
            for (Annotation annotation : annotations1) {
                if (annotation.annotationType() == RequestBody.class) {
                    Class c = method.getParameterTypes()[0];
                    Object o = JSON.parseObject(params, c);
                    try {
                        Object r = method.invoke(objectMethod.getObject(), o);
                        System.out.println(JSON.toJSON(r));

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

    public static void main(String[] args) {
        Dispatcher dispatcher = new Dispatcher();
        Request request = new Request("test", "{\"id\":\"1\",\"name\":\"mvc\"}");
        dispatcher.dispatch(request);
    }
}
