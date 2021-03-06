package com.example;

import com.example.javatest.DigestUtils;
import com.example.javatest.stringutil.Contant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by bing.zhao on 2016/12/2.
 *
 * @author zb
 * @version 1.0
 */
public class JavaTest {

    /**
     * @see DigestUtils
     * shuoming {@link DigestUtils}
     * @param p
     * @return
     */
    public int test(boolean p){
        return 1;
    }
    static int x;
//    public static void main(String args[]) {
//        getStringFileList("F:\\excel" + File.separator + "res");
//    }

    public static void main(String[] args)  {
        List data = null;
        try{
            data.size();
        }catch (Exception e){
            System.out.println("异常");
        }
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
                    System.out.println(Contant.ROOT_FILE_PATH + File.separator + item + File.separator + "strings.xml");
                }
            }
        }
        return strList;
    }


    public static String getTimeLineForAm(long timestamp)
    {
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String str = formatter.format(date);
        if(str.contains("a.m.")){
            str = str.replaceAll("a.m.","AM");
        }else if(str.contains("p.m.")){
            str = str.replaceAll("p.m.","PM");
        }
        return str;
    }

    /*
     * 判断一个时区从1970年开始是否使用过夏时令
     */
    public static void testUseDST(TimeZone timeZone) {
        System.out.println("====> testUseDST 判断一个时区ID是否使用过夏时令");
        System.out.println("Time Zone is : " + timeZone.getDisplayName() + " : " + timeZone.getID());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar start = Calendar.getInstance(timeZone);
        start.setTime(new Date(0)); // UTC 1970-01-01
        System.out.println("start=" + df.format(start.getTime())); // will
        // print:
        // start=1970-01-01
        // 08:00:00
        Calendar end = Calendar.getInstance(timeZone);
        // end.add(Calendar.YEAR, 1);
        System.out.println("end=" + df.format(end.getTime()));
        boolean find = false;
        for (long i = start.getTimeInMillis(); i < end.getTimeInMillis(); i = start.getTimeInMillis()) {
            start.add(Calendar.DATE, 1); // add one day
            if ((start.getTimeInMillis() - i) % (24 * 3600 * 1000L) != 0) { // 是否能被24整除
                find = true;
                System.out.println("from " + df.format(new Date(i)) + " to " + df.format(start.getTime()) + " has "
                        + (start.getTimeInMillis() - i) + "ms" + "[" + (start.getTimeInMillis() - i) / (3600 * 1000L)
                        + "hours]");
            }

        }
        if (!find) {
            System.out.println("Every day is ok.");
        }
    }

    /**
     * TimeZone类使用测试
     */
    public static void testTimeZone() {
        System.out.println("====> testTimeZone()");
        // 输出TimeZone支持的时区ID
        System.out.println(Arrays.asList(TimeZone.getAvailableIDs()));

        System.out.println("==== 测试0 - 基本使用测试");
        // Gets the default TimeZone for this host.
        System.out.println("Default TimeZone    ID : " + TimeZone.getDefault().getID());
        System.out.println("Default TimeZone ZoneId: " + TimeZone.getDefault().toZoneId());
        System.out.println("Default TimeZone Display Name: " + TimeZone.getDefault().getDisplayName());
        System.out.println("Default TimeZone getDSTSavings: " + TimeZone.getDefault().getDSTSavings());
        System.out.println("Default raw offset: " + TimeZone.getDefault().getRawOffset());

        // Create TimeZone by the time zone ID
        System.out.println("getId    : " + TimeZone.getTimeZone("Asia/Shanghai").getID());
        System.out.println("toZoneId : " + TimeZone.getTimeZone("Asia/Shanghai").toZoneId());
        System.out.println("toString : " + TimeZone.getTimeZone("America/New_York").toString());

        // Create TimeZone by the specified custom time zone ID
        System.out.println("getId    : " + TimeZone.getTimeZone("GMT+0800").getID());
        System.out.println("toZoneId : " + TimeZone.getTimeZone("GMT+0800").toZoneId());
        System.out.println("toString : " + TimeZone.getTimeZone("GMT+0800").toString());
        System.out.println("raw offset: " + TimeZone.getTimeZone("GMT+0800").getRawOffset());

        System.out.println("==== 测试1 - 夏时令问题");
        System.out.println("====> testSimpleTimeZone()");
        int timeZoneOffset = +2;
        TimeZone timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, "Asia/Shanghai");
        System.out.println(timeZone.getID());
        System.out.println(
                "SimpleTimeZone is a concrete subclass of TimeZone that represents a time zone for use with a Gregorian calendar. ");

        Thread t ;
    }

    public void sum(){}

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
