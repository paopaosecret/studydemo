package com.example.designpattern.decorator;

/**
 * Created by zhaobing04 on 2018/4/23.
 */

public class Decorator implements Show {
    protected Show mBase;
    public Decorator(Show show){
        mBase = show;
    }

    @Override
    public void show() {
        this.mBase.show();
    }
}
