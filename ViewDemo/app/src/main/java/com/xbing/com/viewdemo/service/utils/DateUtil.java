package com.xbing.com.viewdemo.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	public static int getDaysOfMonth(int year,int month){
		Calendar   cal   =   Calendar.getInstance();   
		cal.set(Calendar.YEAR,year);   
		cal.set(Calendar.MONTH,month-1);//7月   
		int   maxDate   =   cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}
	
	public static int getWeek(Date date){  
	      Calendar cal = Calendar.getInstance();  
	      cal.setTime(date);  
	      int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
	      if(week_index<0){  
	          week_index = 0;  
	      }   
	      return week_index;  
	  }
	
	 public static Date getDateFromString(int year, int month) {  
	      return DateUtil.getDateFromString(year,month,1);  
	  } 
	 
	 public static Date getDateFromString(int year,int month,int day){
		 String dateString = year + "-" + (month > 9 ? month : ("0" + month))  
	              + "-"+(day>9?day:("0"+day));
		 Date date = null;  
	      try {  
	          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	          date = sdf.parse(dateString);  
	      } catch (ParseException e) {  
	          System.out.println(e.getMessage());  
	      }  
	      return date; 
	 }
}
