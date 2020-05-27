package com.example.thinkinjava.rtti;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobing04 on 2018/5/23.
 */

public class TypeTestA<T> {

    public static void main(String args[]){
//        TypeTestA<String> a = new TypeTestA<>();
//        Type[] type = a.getClass().getTypeParameters();
//        System.out.println(type[0].getTypeName());
//
//        Type genericSuperClass = a.getClass().getGenericSuperclass();
//        System.out.println(genericSuperClass);


        List<TypeTestA<String>> list = new ArrayList<>();

        Type[] type = list.getClass().getTypeParameters();
        System.out.println(type[0].getTypeName() + ",length = " + type.length );

        Type genericSuperClass = list.getClass().getGenericSuperclass();
        System.out.println("\ngenericSuperClass:" + genericSuperClass);

        Class componentType = list.getClass().getComponentType();
        System.out.println("\ncomponentType:" + componentType);


        Type[] typesInteface = list.getClass().getGenericInterfaces();
        for(int i = 0; i < typesInteface.length; i++ ){
            System.out.println(typesInteface[i] + ",length = " + typesInteface.length );
        }


//
//        People people = new People();
//        Type types = people.getClass().getGenericSuperclass();
//        System.out.println("Type = " + types);
//        if(types != null && types.length > 0){
//            System.out.println("types.length:" + types.length + ",Type = " + types[0]);
//        }
    }

}
