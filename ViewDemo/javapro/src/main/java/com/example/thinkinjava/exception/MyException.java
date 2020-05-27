package com.example.thinkinjava.exception;

/**
 * Created by zhaobing04 on 2018/4/28.
 */

public class MyException extends Exception {

    private String message;
    public MyException(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return message;
    }
}
