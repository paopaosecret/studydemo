package com.example.io;

import java.util.regex.Pattern;

public class StringFilterTest {
    private static Pattern pattern = Pattern.compile("[0-9]+");
    public static void main(String args[]){
        System.out.println(pattern.matcher("123456").matches());
        System.out.println(pattern.matcher("123adfds").matches());
        System.out.println(pattern.matcher("123adfds").find(2)); //从索引为2（包含索引为2的字符）的字符开始匹配
        System.out.println(pattern.matcher("123ad123").find(5));
    }
}
