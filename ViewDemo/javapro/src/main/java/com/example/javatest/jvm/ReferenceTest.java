package com.example.javatest.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by zhaobing04 on 2018/3/28.
 */

public class ReferenceTest {
    public static void main(String[]  args){
        Object obj  = new Object();   //强引用

        //软引用，内存不足，系统gc时会被回收，适合缓存图片等等
        SoftReference<String> softStr = new SoftReference<String>(new String("heallo"));
        System.out.println(softStr.get());
        System.gc();
        System.out.println(softStr.get());

        //弱引用，无论内存是否，系统gc时都会被回收,适用于回调方法中防止内存泄漏
        WeakReference<String> weakStr = new WeakReference<String>(new String("world"));
        System.out.println(weakStr.get());
        System.gc();
        System.out.println(weakStr.get());


        //弱引用，无论内存是否，系统gc时都会被回收,适用于回调方法中防止内存泄漏
        WeakReference<String> weakStrImportant = new WeakReference<String>("important");
        System.out.println(weakStrImportant.get());
        System.gc();
        System.out.println(weakStrImportant.get());

    }
}
