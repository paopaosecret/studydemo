package com.example.thinkinjava.enumtest;

/**
 * Created by zhaobing04 on 2018/5/25.
 */
public enum SorceDetailTermainal {
    PC("pc",(short)1),
    M("m",(short)2);

    SorceDetailTermainal(String name,short value){
        this.name = name;
        this.value = value;
    }
    private String name;

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    private short value;
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public static String getNameByValue(short value){
        for (SorceDetailTermainal c : SorceDetailTermainal.values()) {
            if (c.getValue() == value) {
                return c.name;
            }
        }
        return null;
    }

    public static int getValueByName(String name){
        for (SorceDetailTermainal c : SorceDetailTermainal.values()) {
            if (c.name.equals(name)) {
                return c.value;
            }
        }
        return -1;
    }
}
