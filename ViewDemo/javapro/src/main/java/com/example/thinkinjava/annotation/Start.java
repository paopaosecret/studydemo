package com.example.thinkinjava.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhaobing04 on 2018/4/23.
 *
 * 标记注解：有元素的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Start {

    //注解元素，设置默认值
    public String start() default "start";
}
