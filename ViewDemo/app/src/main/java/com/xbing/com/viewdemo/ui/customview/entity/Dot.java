package com.xbing.com.viewdemo.ui.customview.entity;

/**
 * Created by bing.zhao on 2017/5/16.
 */

public class Dot {

    public static enum DotType
    {
        STATIC_DOT, LINE_DOT
    }

    public Dot(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DotType getType() {
        return type;
    }

    public void setType(DotType type) {
        this.type = type;
    }

    private int value;
    private DotType type;
}
