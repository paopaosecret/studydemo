package com.example.tools;

import com.sun.nio.sctp.PeerAddressChangeNotification;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by bing.zhao on 2016/12/5.
 */

public class XmlParser {

    public static void main(String[] args){
        String strReader = "F:\\copy" + File.separator + "strings.xml";
        String strWriter= "F:\\copy" + File.separator + "string.xml";
        File fileR = new File(strReader);
        File fileW = new File(strWriter);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileR));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileW));
            String temp = null;
            while((temp = br.readLine()) != null){
                String key = getKey(temp);
                if(key.equals("null")){

                }else{
                    System.out.println(key);
                    bw.write(temp+"\n");
                }

            }

            br.close();
            bw.flush();
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getKey(String str){
        int startIndex = str.indexOf('\"');
        if(startIndex == -1){
            return "null";
        }
        int endIndex = str.lastIndexOf('\"');
//        System.out.println("sIndex = " + startIndex + ",eIndex = " + endIndex);
        return str.substring(startIndex+1,endIndex);

    }

}
