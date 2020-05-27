package com.example.thinkinjava.enumtest;

/**
 * Created by bing.zhao on 2016/12/9.
 */

/**
 * 枚举类
 * 特性：
 * 1.不能有public的构造方法
 * 2.所有枚举值都是public static final
 *
 * 作用：
 * 枚举是一种规范，它规范了参数的形式，这样就可以不用考虑类型的不匹配并且显式的替代了int型参数可能带来的模糊概念
 *
 */
public enum Season {

    SPRING("Spring"),

    SUMMER("Summer"),

    AUTUMN("Autumn"),

    WINTER("Winter");

    Season(String name){
        this.name = name;
    }

    private String name;

    public String getName(){
        return this.name;
    }


    public static String getNameById(int index){
        for (Season c : Season.values()) {
            if (c.ordinal() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static int getIdByName(String name){
        for (Season c : Season.values()) {
            if (c.name.equals(name)) {
                return c.ordinal();
            }
        }
        return -1;
    }

}

