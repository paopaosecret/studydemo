package com.example.thinkinjava.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * Created by zhaobing04 on 2018/4/23.
 */

public class Student {




    @Show(id=10)
    public String showStudent(){
//        int id = this.getClass().getAnnotation(Show.class).id();
//        String show = this.getClass().getAnnotation(Show.class).show();
//        return "Student id = " + id + ", show = " + show;
        return "";
    }

    public static void main(String[] args){
        Student student = new Student();
        Class<Student> c = Student.class;
        try {
            Method method = c.getMethod("showStudent", new Class[]{});
            if(method.isAnnotationPresent(Show.class)){
                Show show =  method.getAnnotation(Show.class);
//                method.invoke(student,new Object[]{});
                int id = show.id();
                String msg = show.show();
                System.out.println( "Student id = " + id + ", show = " + msg);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
