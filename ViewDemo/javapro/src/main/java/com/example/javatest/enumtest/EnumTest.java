package com.example.javatest.enumtest;

/**
 * Created by bing.zhao on 2016/12/9.
 */

public class EnumTest {

    static Season season = Season.AUTUMN;

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
                break;
            case WINTER:
                System.out.println("Winter");
                break;
        }
    }
}
