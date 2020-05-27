package com.example.thinkinjava.exception;

/**
 * Created by zhaobing04 on 2018/4/28.
 */

public class MyRuntimeException extends RuntimeException {
    private String message;
    public MyRuntimeException(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return message;
    }
}
