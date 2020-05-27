package com.example.tools;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by bing.zhao on 2017/2/7.
 */

public class RandomTest {

    public static void main(String[] args){
        Random random = new Random(new Date().getTime());
        System.out.println("red:");
        for(int i= 0; i<6; i++ ){
            int r = random.nextInt(33)+1;
            System.out.println(r);
        }

        System.out.println("blue:");
        int r = random.nextInt(16)+1;
        System.out.println(r);

    }

    /**
     *
     red:
     4
     19
     18
     24
     15
     6
     blue:
     7
     */

}
