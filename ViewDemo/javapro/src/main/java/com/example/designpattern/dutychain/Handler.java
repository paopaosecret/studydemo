package com.example.designpattern.dutychain;

/**
 * Created by zhaobing04 on 2018/3/17.
 */

public abstract class Handler {

    protected Handler nextHandler;

    public void setSuccessor(Handler nextHandler){
        this.nextHandler = nextHandler;
    }
    abstract void handlerRequest(int request);
}
