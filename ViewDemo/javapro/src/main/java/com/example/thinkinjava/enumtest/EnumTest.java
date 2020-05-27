package com.example.thinkinjava.enumtest;


/**
 * Created by bing.zhao on 2016/12/9.
 */

public class EnumTest {

    static Season season = Season.AUTUMN;
    static Season spring = Season.SPRING;

    public static void main(String args[]){
        switch (season){
            case SPRING:
                System.out.println("Spring");
                break;
            case SUMMER:
                System.out.println("Summer");
                break;
            case AUTUMN:
                System.out.println("Autumn");
                System.out.println(season.toString());
                System.out.println(season.ordinal());
                System.out.println(season.name());
                System.out.println(season.getName());
                System.out.println(season.values());
                System.out.println(spring.ordinal());
                break;
            case WINTER:
                System.out.println("Winter");
                break;
        }
        System.out.println(Season.getNameById(0));
        System.out.println(Season.getNameById(1));
        System.out.println(Season.getNameById(2));
        System.out.println(Season.getNameById(3));
        System.out.println(Season.getIdByName("Spring"));
        System.out.println(Season.getIdByName("Summer"));
        System.out.println(Season.getIdByName("Autumn"));
        System.out.println(Season.getIdByName("Winter"));

        System.out.println("-------------------------------------------------------");
        System.out.println(SorceDetailPlatform.getIdByName("baidu"));
        System.out.println(SorceDetailPlatform.getIdByName("sougou"));
        System.out.println(SorceDetailPlatform.getIdByName("shenma"));

        System.out.println("-------------------------------------------------------");
        System.out.println(SorceDetailTermainal.getValueByName("pc"));
        System.out.println(SorceDetailTermainal.getValueByName("m"));


    }
}
