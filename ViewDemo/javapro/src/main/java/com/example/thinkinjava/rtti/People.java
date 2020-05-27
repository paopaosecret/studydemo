package com.example.thinkinjava.rtti;

/**
 * Created by bing.zhao on 2017/3/17.
 */

public class People {
    private String name;
    private String sex;
    public  String salary;


    public static final String TAG = "People";

    static{
        System.out.println("People.class.init()");
    }

    private void secret(){
        System.out.print("this is l secret");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
