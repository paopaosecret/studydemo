package com.example.designpattern.reuseclass.staticproxy;

/**
 * Created by zhaobing04 on 2018/4/12.
 */

public class SleepProxy implements ISleep {

    private ISleep mSleep;

    public SleepProxy(ISleep sleep){
        this.mSleep = sleep;
    }

    @Override
    public void sleep() {
        System.out.println("Sleep ---------start----------");
        this.mSleep.sleep();
        System.out.println("Sleep ---------end-=----------");
    }

    public static void main(String[] args){
        ISleep sleep = new SleepProxy(new SleepImpl());
        sleep.sleep();
    }

}
