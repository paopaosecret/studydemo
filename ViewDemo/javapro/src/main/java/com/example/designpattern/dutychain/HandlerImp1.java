package com.example.designpattern.dutychain;

/**
 * Created by zhaobing04 on 2018/3/17.
 */

public class HandlerImp1 extends Handler {


    @Override
    public void handlerRequest(int request) {
        if(request >0 && request <=10){
            System.out.println("request exe by handler1:" + request);
        }else{
            nextHandler.handlerRequest(request);
        }
    }
}
