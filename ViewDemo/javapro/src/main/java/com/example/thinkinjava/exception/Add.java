package com.example.thinkinjava.exception;

/**
 * Created by zhaobing04 on 2018/4/28.
 */

public class Add {

    /**
     * 使用throw关键字抛出普通异常时，需要配合throw在方法上抛出异常
     * 或者使用try...catch{}语句
     */
    public void testException(int num) throws MyException {
        if(num == 5){
            throw new MyException("操作数等于5");
        }
    }

    /**
     * 使用throw关键字抛出普通异常时，需要配合throw在方法上抛出异常
     * 或者使用try...catch{}语句
     */
    public void testException2(int num) {
        if(num == 5){
            try {
                throw new MyException("操作数等于5");
            } catch (MyException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 当使用throw关键字抛出运行时异常，可以不使用try...catch包裹
     * 或者使用 throws在方法上声明抛出异常
     */
    public void testRuntimeException(int num){
        if(num == 0){
            throw new MyRuntimeException("操作数等于0");
        }
    }


    public static void main(String[] args){
        Add add = new Add();
        try {
            add.testException(5);
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("exception main:" + e.getMessage());
        }
//        add.testException(5);    //非运行时异常必须被捕捉处理
        System.out.println("----------------------------------------------------------");
        add.testException2(5);
        System.out.println("----------------------------------------------------------");



        try{
            add.testRuntimeException(0);
        }catch (MyRuntimeException e){
            System.out.println("MyRuntimeException:" + e.getMessage());
        }

        add.testRuntimeException(0);  //运行时异常可以不被捕捉处理
    }

}
