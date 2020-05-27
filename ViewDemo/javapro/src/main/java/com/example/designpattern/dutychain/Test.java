package com.example.designpattern.dutychain;

/**
 * Created by zhaobing04 on 2018/3/17.
 */

public class Test {

    public static void main(String[] args){
        int[] requests = {2,9,12,34,19,3,89};
        Handler h1 = new HandlerImp1();
        Handler h2 = new HandlerImp2();
        Handler h3 = new HandlerImp3();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        for(int request:requests){
            h1.handlerRequest(request);
        }
    }
}
