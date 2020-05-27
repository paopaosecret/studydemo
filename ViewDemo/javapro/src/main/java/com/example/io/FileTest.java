package com.example.io;

import java.io.File;
import java.io.FilenameFilter;

public class FileTest {

    public static void main(String args[]){
        //定义一个目录
        File dwork = new File("D:\\work");
        System.out.println("是目录？ " + dwork.isDirectory());
        System.out.println("是文件？ " + dwork.isFile());
        if(dwork.isDirectory()){
            String[] fileList = dwork.list();
            for(String file : fileList){
                System.out.println(file);
            }
        }












        System.out.println("\n过滤文件名中包含a字符的文件----------------------- \n");
        if(dwork.isDirectory()){
            String[] aFile = dwork.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.contains("a");
                }
            });
            for(String str : aFile){
                System.out.println(str);
            }
        }
    }
}
