package com.example.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandTest {

    public static void main(String args[]){

        try {

            Process process = new ProcessBuilder("ipconfig").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String s;
            while((s = reader.readLine()) != null){
                System.out.println(s);
            }

            process.destroy();
            if(reader != null){
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
