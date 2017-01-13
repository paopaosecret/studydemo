package com.xbing.com.viewdemo.ui.customview;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.DateUtil;

public class MyCalenarView extends FrameLayout {

	private ItemView mItems[][] = new ItemView[7][7];
	private ArrayList<Date> mMarkedDay = new ArrayList<Date>();
	
	private GridLayout mGridLayout = null;
	private RelativeLayout mTitleLayout = null;
	
	int mCellWidth ;
	int mCellHeight ;
	
	int mHeight;
	int mWidth;
	
	int mYear = 2015;
	int mMonth = 11;
	int mDay = 1;
	
	public int getmDay() {
		return mDay;
	}
	public void setmDay(int mDay) {
		this.mDay = mDay;
	}

	int mSecondaryColor = Color.parseColor("#fff2f2f2");
	int mPrimaryColor = Color.parseColor("#fff2f2f2");
	
	Context mContext;
	
	TextView mTextViewCenter;
	TextView mTextViewLeft;
	TextView mTextViewRight;
	
	private boolean hasGridLayout = false;
	private boolean hasTitleLayout = false;
	private boolean hasRightBg = false;
	private boolean hasLeftBg = false;
	
	private OnItemclickListener listener;
	
	public MyCalenarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initAllItem(context);
	}
	public MyCalenarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initAllItem(context);
	}
	public MyCalenarView(Context context) {
		super(context);
		mContext = context;
		initAllItem(context);
	}
	
	/**
	 * 控件大小有改变时，重新绘制
	 * @param context
	 */
	public  void reLoadCalendar(Context context){
		mCellWidth = mWidth/7;
		mCellHeight = mHeight/6;
		Log.i("xjz","cellWidth"+mCellWidth);
		Log.i("xjz","cellHeight"+mCellHeight);
		
		if(null == mTitleLayout){
		mTitleLayout = new RelativeLayout(context);
		}
		mTitleLayout.removeAllViews();
		
		if(!hasLeftBg){
			mTextViewLeft.setText("<");
			mTextViewLeft.setGravity(Gravity.CENTER);
			mTextViewLeft.setSingleLine();
		}
		mTextViewLeft.setFocusable(true);
		mTextViewLeft.setClickable(true);
		mTextViewLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getPreviousMonth();
				initCalendar();
				mTextViewCenter.setText(mYear+"/"+mMonth);
			}
		});
		mTextViewLeft.setWidth(mCellWidth);
		mTextViewLeft.setHeight(mCellHeight);
		RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //与父组件顶部对齐
        lp1.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mTitleLayout.setBackgroundColor(R.color.lite_blue);
        //横向居中，是
		mTitleLayout.addView(mTextViewLeft,lp1);
		
		if(!hasRightBg){
		mTextViewRight.setText(">");
		mTextViewRight.setGravity(Gravity.CENTER);
		}
		mTextViewRight.setWidth(mCellWidth);
		mTextViewRight.setHeight(mCellHeight);
		
		mTextViewRight.setFocusable(true);
		mTextViewRight.setClickable(true);
		mTextViewRight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getNextMonth();
				initCalendar();
				mTextViewCenter.setText(mYear+"-"+mMonth);
			}
		});
		RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //与父组件顶部对齐
        lp2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        //横向居中，是
		mTitleLayout.addView(mTextViewRight,lp2);		
		
		
		mTextViewCenter.setText(mYear+"/"+mMonth);
		mTextViewCenter.setGravity(Gravity.CENTER);
		mTextViewCenter.setHeight(mCellHeight);
		RelativeLayout.LayoutParams lp3= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //与父组件顶部对齐
        lp3.addRule(RelativeLayout.CENTER_IN_PARENT);
		mTitleLayout.addView(mTextViewCenter,lp3);
		
		LayoutParams fl = new LayoutParams(mWidth, mCellHeight);
		fl.topMargin = 0;
		if(hasTitleLayout){
			removeView(mTitleLayout);
			hasTitleLayout = false;
		}
		addView(mTitleLayout, fl);
		hasTitleLayout = true;
		
		
		LayoutParams fl2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
		fl2.topMargin = mCellHeight;
		
		fillCalendar(context);
		if(hasGridLayout){
		   removeView(mGridLayout);
		   hasGridLayout = false;
		}
		addView(mGridLayout,fl2);
		hasGridLayout = true;
		mTextViewLeft.setVisibility(View.GONE);
		mTextViewRight.setVisibility(View.GONE);
	}
	
	public int getmYear() {
		return mYear;
	}
	public void setmYear(int mYear) {
		this.mYear = mYear;
	}
	public int getmMonth() {
		return mMonth;
	}
	public void setmMonth(int mMonth) {
		this.mMonth = mMonth;
	}
	/**
	 * 初始化所有的子项
	 * @param context
	 */
	public void initAllItem(Context context){
		
		mTextViewRight = new TextView(context);
		mTextViewCenter = new TextView(context);
		mTextViewLeft = new TextView(context);
		
		String[] string = new String[]{"周日","周一","周二","周三","周四","周五","周六"};
		for(int i=0;i<7;i++){
			ItemView textView = new ItemView(context);
			textView.setText(string[i]);
			textView.setFocusableInTouchMode(false);
			textView.setVisibility(View.VISIBLE);
			textView.setTextColor(R.color.card_background);
			mItems[0][i] = textView;
		}

		for (int i= 1;i<7;i++){
			for(int j=0;j<7;j++){
				ItemView textView = new ItemView(context);
				textView.setText("");
				mItems[i][j] = textView;
			}
		}
		initCalendar();
	}
	
	/**
	 * 计算每个位置的数值，初始化日历（此时没有显示）
	 */
	public void initCalendar(){
		int week = DateUtil.getWeek(DateUtil.getDateFromString(mYear, mMonth));
		int max = DateUtil.getDaysOfMonth(mYear, mMonth);
		
        Log.i("xjz","week"+week);
		Log.i("xjz","max"+max);

		int firstPosition = week%7;

		int previousMax = 0;
		if (1 == mMonth){
			previousMax = DateUtil.getDaysOfMonth(mYear, 12);
		}else{
			previousMax = DateUtil.getDaysOfMonth(mYear, mMonth-1);
		}
		
		//上一个月的日期
		for(int j=4;j>-1;j--){
			 mItems[1][j].setText(""+previousMax--);
			 mItems[1][j].setTextColor(mSecondaryColor);
			 Date date = null;
			 if(1 == mMonth){
				 date = DateUtil.getDateFromString(mYear-1, 12, previousMax+1);
			 }
			 else{
				 date = DateUtil.getDateFromString(mYear, mMonth, previousMax+1);
			 }
			 if(isMarked(date)){
				 mItems[1][j].setMarkVisiable(true);
			 }else{
				 mItems[1][j].setMarkVisiable(false);
			 }
		}

		int count = 0;
		int count1 = 1;
		//默认第一天获得焦点
		mItems[1][firstPosition].setFlag(false);
		mItems[1][firstPosition].requestFocus();
		//当前月份的第一行
	    for(int j = firstPosition;j < 7;j++){
	    	 count++;
	    	 mItems[1][j].setText(""+count);
	    	 mItems[1][j].setTextColor(mPrimaryColor);
	    	 Date date = DateUtil.getDateFromString(mYear, mMonth, count);
	    	 mItems[1][j].setDate(date);
			 if(isMarked(date)){
				 mItems[1][j].setMarkVisiable(true);
			 }else{
				 mItems[1][j].setMarkVisiable(false);
			 }
	    }
	    for(int i =2;i<7;i++){
	    	for(int j=0;j<7;j++){
	    		count++;
	    		if (count > max){
	    			//下一个月部分的日期
	    			mItems[i][j].setText(""+count1);
	    			mItems[i][j].setTextColor(mSecondaryColor);
	    			count1++;
	    			 Date date = null;
	    			 if(12 == mMonth){
	    				 date = DateUtil.getDateFromString(mYear+1, 1, count1-1);
	    			 }
	    			 else{
	    				 date = DateUtil.getDateFromString(mYear, mMonth, count1-1);
	    			 }
	    			 mItems[i][j].setDate(date);
	    			 if(isMarked(date)){
	    				 mItems[i][j].setMarkVisiable(true);
	    			 }else{
	    				 mItems[i][j].setMarkVisiable(false);
	    			 }
	    		}else{
	    			mItems[i][j].setText(""+count);
	    			mItems[i][j].setTextColor(mPrimaryColor);
	    			 Date date = DateUtil.getDateFromString(mYear, mMonth, count);
	    			 mItems[i][j].setDate(date);
	    			 if(isMarked(date)){
	    				 mItems[i][j].setMarkVisiable(true);
	    			 }else{
	    				 mItems[i][j].setMarkVisiable(false);
	    			 }
	    		}
	    	}
	    }

//		Log.d("msg", "max=" + max + ",year=" +mYear+ ",month=" +mMonth);
//        int count = 1;
//		for(int i=1;i<6;i++){
//			for(int j=0;j<7;j++){
//				if(1 == i &&j <4){
//					mItems[i][j].setVisibility(View.GONE);
//				}
//				else if(count > max){
//					mItems[i][j].setVisibility(View.GONE);
//				}else{
//					mItems[i][j].setVisibility(View.VISIBLE);
//					mItems[i][j].setText(""+count);
//					count++;
//					Date date = DateUtil.getDateFromString(mYear, mMonth, count);
//					mItems[i][j].setDate(date);
//					if(isMarked(date)){
//						mItems[i][j].setMarkVisiable(true);
//					}else{
//						mItems[i][j].setMarkVisiable(false);
//					}
//
//				}
//			}
//		}
		getItemView(mDay).setFlag(false);
		getItemView(mDay).requestFocus();
		allItemClickListener();
	}
	
	/**
	 * 网格布局填充View
	 * @param context
	 */
	private void fillCalendar(Context context){
		if(null == mGridLayout){
		mGridLayout = new GridLayout(context);
		mGridLayout.setOrientation(GridLayout.HORIZONTAL);
		mGridLayout.setRowCount(7);
		mGridLayout.setColumnCount(7);
		mGridLayout.setBackgroundResource(R.drawable.bg);
		}
		mGridLayout.removeAllViews();
		for(int i = 0;i<6;i++){
			for(int j=0;j<7;j++){
			 mItems[i][j].setMarkSize(mCellWidth, mCellHeight, context);
		     mGridLayout.addView(mItems[i][j],mCellWidth,mCellHeight);
		}
	  }
   }
	
	
	
	public void setYear(int year){
		if(year < 2000||year>2100){
			Toast.makeText(mContext, "年份不合法", Toast.LENGTH_SHORT).show();
		}else{
		this.mYear = year;
		}
	}
	
	public void setMonth(int month){
		if(month < 1|| month >12){
//			Toast.makeText(mContext, "月份输入不合法", Toast.LENGTH_SHORT).show();
		}else{
			this.mMonth = month;
		}
	}
	
	public void setInitDate(int year,int month){
		setYear(year);
		setMonth(month);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		mHeight = h;
		mWidth = w;
        Log.i("xjz","w"+w);
		Log.i("xjz","h"+h);
		reLoadCalendar(mContext);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	

	/**
	 * 设置日历的大小
	 * @param width 日历的宽度，单位是pixel
	 * @param height 日历的高度，单位是pixel
	 */
	public void setSize(int width,int height){
		onSizeChanged(width, height, mWidth, mHeight);
	}
	

	/**
	 * 设置非当前月日期字体的颜色
	 * @param color
	 */
	public void setSecondaryColor(int color){
		mSecondaryColor = color;
	}
	/**
	 * 设置当前月日期字体的颜色
	 * @param color
	 */
	public void setPrimaryeColor(int color){
		mPrimaryColor = color;
	}
	
	/**
	 * 根据日期得到View
	 * @param day 当前月的号数
	 * @return 访问越界返回null，否则返回ItemView
	 */
	public ItemView getItemView(int day){
	   int row = (day + 3)/7 + 1;
	   int column = day + 10 - (7 * row);
	   return getItemView(row,column);
    }
	/**
	 * 返回指定位置的子项，共七行七列
	 * @param row [0,6]
	 * @param column [0,6]
	 * @return 访问越界返回null，否则返回ItemView
	 */
	public ItemView getItemView(int row,int column){
		if(row<0||row>mItems.length-1){
			return null;
		}else if(column<0||column > mItems[0].length-1){
			return null;
		}
		
		return mItems[row][column];
	}
	
	/**
	 * 标记指定日期
	 * @param date
	 * @return
	 */
	public int addDayMarked(Date date){
    	if(false == isMarked(date)){
    	    mMarkedDay.add(date);
    	    initCalendar();
    	    return 0;
    	}else{
    		return -1;
    	}
	}
	
    /**
     * 标记指定日期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public int addDayMarked(int year,int month,int day){
    	Date date = DateUtil.getDateFromString(year, month, day);
    	return addDayMarked(date);
   	}
	
   /**
    * 移除指定日期的标记
    * @param year
    * @param month
    * @param day
    */
    public void cancelDayMark(int year,int month,int day){
    	Date date = DateUtil.getDateFromString(year, month, day);
    	cancelDayMark(date);
    	initCalendar();
    }
    
    /**
     * 移除指定日期的标记
     * @param date
     */
    public void cancelDayMark(Date date){
    	if(isMarked(date)){
    	    mMarkedDay.remove(date);
    	    initCalendar();
    	}else{
//    		Toast.makeText(mContext, "移除失败，不存在这样的已标记日期", Toast.LENGTH_SHORT).show();
    	}
    }
    
    /**
     * 移除所有的标记的日期
     */
    public void cancelAllMark(){
    	mMarkedDay.clear();
    	initCalendar();
    }
    
    
    /**
     * 设置下一个月按钮的背景
     * @param resid
     */
    public void setNextBackground(int resid){
    	mTextViewRight.setBackgroundResource(resid);
    	hasRightBg = true;
    }
    
    /**
     * 设置上一个月按钮的背景
     * @param resid
     */
    public void setPreivousBackground(int resid){
    	mTextViewLeft.setBackgroundResource(resid);
    	hasLeftBg = true;
    }
    
    public boolean isMarked(Date date){
		for(Date a:mMarkedDay){
			if (0 == date.compareTo(a))
				return true;
		}
		return false;
	}
    
    
    /**
     * 得到显示年月的View
     */
    public TextView getViewTitle(){
    	return mTextViewCenter;
    }
    
    
    /**
     * 得到下一个月
     * @return
     */
	private int getNextMonth(){
		int month = ++this.mMonth;
		if(month > 12){
		    this.mYear++;
		    this.mMonth = 1;
		    return this.mMonth;
		}
		Log.i("xjz", "newMonth ->"+ month);
		return month;
	}
	
	/**
	 * 得到前一个月
	 * @return
	 */
	private int getPreviousMonth(){
		int month = --this.mMonth ;
		if(month < 1){
		    this.mYear--;
		    this.mMonth = 12;
		}
		return month;
	}

	
	public interface OnItemclickListener{
		public void onItemClickListener(Date date);
	}
	
	public void setOnItemClickListener(OnItemclickListener listener){
		Log.d("11111", "step 1");
		this.listener = listener;
	
	}
	
	
	private void allItemClickListener(){
		Log.d("11111", "step 2");
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				 mItems[i][j].setmClick(new ItemView.OnViewClick() {
					@Override
					public void OnViewClick(Date date) {
						if(listener!=null){
							listener.onItemClickListener(date);
						}
						
					}
				});
			}
		}
	}
	
	public void next(){
		getNextMonth();
		initCalendar();
		mTextViewCenter.setText(mYear+"/"+mMonth);
	}
	
	public void previous(){
		getPreviousMonth();
		initCalendar();
		mTextViewCenter.setText(mYear+"/"+mMonth);
	}
}
