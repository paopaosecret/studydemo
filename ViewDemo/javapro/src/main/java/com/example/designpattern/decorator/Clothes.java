package com.example.designpattern.decorator;

/**
 * Created by zhaobing04 on 2018/4/23.
 */

public class Clothes extends Decorator {
    public Clothes(Show base){
        super(base);
    }

    @Override
    public void show() {
        System.out.println("穿上帅气的衣服");
        this.mBase.show();
    }
}
