package com.example.designpattern.dutychain;

/**
 * Created by zhaobing04 on 2018/3/17.
 */

public class HandlerImp2 extends Handler {
    @Override
    void handlerRequest(int request) {
        if(request >10 && request <=20){
            System.out.println("request exe by handler2:" + request);
        }else{
            nextHandler.handlerRequest(request);
        }
    }
}
