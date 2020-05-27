package com.example.thinkinjava.charpter_fifteen.bean;

import java.io.Serializable;

/**
 * Created by zhaobing04 on 2018/4/17.
 */

public class Fruit implements Serializable{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
