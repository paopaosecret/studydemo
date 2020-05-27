package com.example.javatest.stringutil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing.zhao on 2017/4/28.
 */

public class TranslateUtil {

    public static boolean isExistInXMl(String ab){
        for(int i = 0; i< Contant.EXCEL_NAME.length; i++){
            if(Contant.EXCEL_NAME[i].equals(ab)){
                return true;
            }
        }
        return false;
    }

    public static String getFolderNameByAb(String ab){
        System.out.println("current cloumn:" + ab);
        if("en".equals(ab)){
            return Contant.XML_NAME[0];
        }

        for(int i = 1; i< Contant.XML_NAME.length; i++){
            String[] str = Contant.XML_NAME[i].split("-",2);
            if(str[1].equals(ab)){
                return Contant.XML_NAME[i];
            }
        }
        return "";
    }


    public static File getStringFileInFolder(String folderPath){
        //创建文件夹
        File folder = new File(Contant.ROOT_FILE_PATH + File.separator + folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        //创建文件
        File file = new File(folder,"strings.xml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static void writeItemToXML(PrintWriter out, String key, String value){
        String item=  "   <string name=\"" + key + "\">" + value + "</string>";
        out.println(item);
    }

    public static void writeItemArrayToXML(PrintWriter out, String key, String value){
        String itemHead =  "   <string-array name=\""+ key +"\">";
        String itemEnd  =  "   </string-array>";

        out.println(itemHead);
        out.println(value);
        out.println(itemEnd);
    }

    public static void writeXMLHead(PrintWriter out){
        String head1=  "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        String head2 = "<resources xmlns:xliff=\"urn:oasis:names:tc:xliff:document:1.2\">";
        out.println(head1);
        out.println(head2);
    }

    public static void writeXMLEnd(PrintWriter out){
        String end=  "</resources>";
        out.println(end);
    }

    public static boolean isStringArrayByValue(String value){
        if(value == null){
            return false;
        }else if(value.length() <= 0){
            return false;
        }else if(value.contains("<item>") && value.contains("</item>")){
            return true;
        }
        return false;
    }

    public static List<String> getStringFileList(String path){
        String[] list = null;
        List<String> strList = new ArrayList<>();
        File root = new File(path);
        list = root.list();
        for(String str : list){
            for(String item : Contant.XML_NAME){
                if(item.equals(str)){
                    strList.add(Contant.ROOT_FILE_PATH + File.separator + item + File.separator + "strings.xml");
                }
            }
        }
        return strList;
    }
}
