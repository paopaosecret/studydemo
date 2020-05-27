package com.example.thinkinjava.enumtest;

/**
 * Created by zhaobing04 on 2018/5/25.
 */
public enum SorceDetailPlatform {

    BAIDU("baidu",(short)1),

    SOUGOU("sougou",(short)2),

    SHENMA("shenma",(short)3);

    SorceDetailPlatform(String name,short value){
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

    public static String getNameById(int index){
        for (SorceDetailPlatform c : SorceDetailPlatform.values()) {
            if (c.getValue() == index) {
                return c.name;
            }
        }
        return null;
    }

    public static short getIdByName(String name){
        for (SorceDetailPlatform c : SorceDetailPlatform.values()) {
            if (c.name.equals(name)) {
                return c.getValue();
            }
        }
        return -1;
    }
}
