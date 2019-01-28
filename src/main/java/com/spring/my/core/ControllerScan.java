package com.spring.my.core;

import com.spring.my.annotion.RequestMapping;
import com.spring.my.annotion.RestController;
import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: chensai
 * @Date: 2019/1/25 16:45
 * @Version 1.0
 */
@Getter
public class ControllerScan {
    //Map<String, Object> objects = new HashMap();

    private Map<String, ObjectMethod> urls = new HashMap();

    public void setObjects(List<Class<?>> classes) {
        classes.forEach(e -> {
            if (e.isAnnotationPresent(RestController.class)) {
                RestController controller = e.getAnnotation(RestController.class);
                String name = controller.value();
                Method[] methods = e.getDeclaredMethods();
                String prefix = "";
                if(e.isAnnotationPresent(RequestMapping.class)){
                    RequestMapping requestMapping = e.getAnnotation(RequestMapping.class);
                    prefix = requestMapping.value();
                }

                for (Method method : methods) {
                    String url;
                    ObjectMethod objectMethod = new ObjectMethod();
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                        url = requestMapping.value();
                        url = prefix.concat(url);
                    } else {
                        url  = prefix;
                    }
                    objectMethod.setUrl(url);
                    objectMethod.setMethod(method);
                    urls.put(url,objectMethod);
                    try {
                        objectMethod.setObject(e.newInstance());
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                }
               /* try {
                    objects.put(name,e.newInstance());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }*/
            }
        });
    }

    public List<Class<?>> scan(List<String> packages) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        return packages.stream().map(e -> {
            String name = e.substring(e.indexOf("com"), e.lastIndexOf(".class"));
            name = name.replace('\\', '.');
            Class<?> clazz = null;
            try {
                clazz = classLoader.loadClass(name);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            return clazz;
        }).collect(Collectors.toList());
    }

    public List<String> getClasssByPackage() {
        Path path = Paths.get("");
        try {
            try (Stream<Path> walk = Files.walk(path)) {
                return walk.filter(p -> p.toString().contains(".class")).map(Path::toString).collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ControllerScan controllerScan = new ControllerScan();
        List<String> s = controllerScan.getClasssByPackage();
        controllerScan.setObjects(controllerScan.scan(s));
        System.out.println(1);
       /* controllerScan.getObjects().forEach((k,v)->{
            System.out.println(k);
        });**/


    }
}
