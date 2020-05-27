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
public @interface Show {

    //注解元素, 没有默认值的注解在使用时必须赋值
    public int id();

    //注解元素，设置默认值
    public String show() default "show msg";
}
