package com.example.thinkinjava.rtti;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bing.zhao on 2017/3/17.
 */

public class RttiTest {
    public static void main(String args[]) {
//        testReflect();

        try {
            testParameterizedType();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void test(List<ArrayList<String>[]> a){
    }
    public static <T,U> void applyMethod(Map.Entry<T,U> mapEntry){
        //Map接口就是Map.Entry的所有者
    }

    public static void testParameterizedType() throws Exception{
        Method method = new RttiTest().getClass().getMethod("test", List.class);//这里的第二个参数，和getRawType()意义类似
        Type[] types = method.getGenericParameterTypes();
        ParameterizedType pType = (ParameterizedType) types[0];
        Type type = pType.getActualTypeArguments()[0];
        System.out.println(type);
        //type是Type类型，但直接输出的不是具体Type的五种子类型，而是这五种子类型以及WildcardType具体表现形式
        System.out.println(type.getClass().getName());

        Map<Integer, String> maps = new HashMap<>();
        ParameterizedType pType1 = (ParameterizedType) maps.getClass().getGenericSuperclass();//获得HashMap的父类
        System.out.println(pType1.getRawType());//class java.util.AbstractMap
        if(pType1.getRawType() instanceof Class){
            System.out.println("true");//true
        }
        //注意类型（Type）与类（Class）的区别

        Method method2 = new RttiTest().getClass().getMethod("applyMethod",Map.Entry.class);
        Type[] types2 = method2.getGenericParameterTypes();
        ParameterizedType pType2 = (ParameterizedType)types2[0];
        //返回所有者类型，打印结果是interface java.util.Map
        System.out.println(pType2.getOwnerType());


    }

    public static void testReflect(){
        System.out.println(People.TAG);
        try {
            Class.forName("com.example.thinkinjava.rtti.People");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        People people = new People();
        //获取该对象的Class对象
        System.out.println(people.getClass().getSimpleName());
        System.out.println(people.getClass().getSuperclass().getSimpleName());

        //获取全部字段
        Field[] declaredFields = people.getClass().getDeclaredFields();
        for(Field f : declaredFields){
            System.out.println(f.getName());
        }

        //获取public 字段
        Field[] fields = people.getClass().getFields();
        for(Field f: fields){
            System.out.println(f.getName());
        }

        People people1 = null;


        Class c = null;
        try {
            c = Class.forName("com.example.thinkinjava.rtti.People");
            //c = People.class;
            people1 = (People) c.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        people1.setName("zhangsna");
        System.out.println(people1.getName());
    }
}
