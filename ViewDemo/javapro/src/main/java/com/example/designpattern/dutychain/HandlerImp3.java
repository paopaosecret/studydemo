package com.example.designpattern.dutychain;

/**
 * Created by zhaobing04 on 2018/3/17.
 */

public class HandlerImp3 extends Handler {
    @Override
    void handlerRequest(int request) {
        if(request >20){
            System.out.println("request exe by handler3:" + request);
        }else{
            nextHandler.handlerRequest(request);
        }
    }
}
