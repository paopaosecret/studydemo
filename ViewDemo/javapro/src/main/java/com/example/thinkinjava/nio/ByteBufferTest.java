package com.example.thinkinjava.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by bing.zhao on 2017/3/13.
 */

public class ByteBufferTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteBuffer buffer = ByteBuffer.allocate(12);
        byte a = 65;
        byte b = 66;
        buffer.put(a);
        buffer.put(b);

        byte c[] = "ppp".getBytes("UTF-8");
        buffer.put(c);

        byte[] out = buffer.array();

        for(int i =0; i < out.length; i++){
            System.out.print("第" + i+"个："+out[i]);
        }



    }
}
