package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

import java.util.Date;

public class ItemView extends FrameLayout{

	TextView imageView;
	TextView  textView;
	ImageView bg;
	boolean isVisiable;
	boolean isCurrentMonth;
	int parentWidth = 0;
	int parentHeight = 0;
	boolean hasImageView = false;
	Date mDate;
	Boolean flag = true;
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	OnViewClick mClick;
	
	public OnViewClick getmClick() {
		return mClick;
	}

	public void setmClick(OnViewClick mClick) {
		this.mClick = mClick;
	}

	public ItemView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusableInTouchMode(true);
		setClickable(true);
		setFocusable(true);

		
		LayoutParams lp2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		
		bg = new ImageView(context);
		bg.setBackgroundColor(Color.TRANSPARENT);
		bg.setImageResource(R.drawable.bluedot);
		bg.setVisibility(View.GONE);
		addView(bg,lp2);
		
		textView = new TextView(context);
		textView.setGravity(Gravity.CENTER);
		addView(textView,lp2);
		
		imageView = new TextView(context);
		imageView.setBackgroundResource(R.drawable.new_alert);
		imageView.setVisibility(View.GONE);
		
		setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus){
//					textView.setBackgroundResource(R.drawable.bluedot);
					bg.setVisibility(View.VISIBLE);
					if(mClick!=null){
						if(flag){
							mClick.OnViewClick(getDate());
						}
					}
				}else{
					bg.setVisibility(View.GONE);
//					textView.setBackgroundColor(Color.parseColor("#00000000"));
				}
			}
		});
	}
	
	public void setText(String text){
		textView.setText(text);
	}
	
	protected void setMarkVisiable(boolean isvisiable){
		isVisiable = isvisiable;
		if (true == isvisiable){
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView.setVisibility(View.GONE);
		}
	}
	
	public void setNow(boolean isCurrentMonth){
		this.isCurrentMonth = isCurrentMonth;
	}
	
	public String getText(){
		return textView.getText().toString().trim();
	}
	
	public void setTextColor(int color){
		textView.setTextColor(color);
	}
	
	/**
	 * 设置标记图标的大小和位置
	 * @param cellWidth
	 * @param cellHeight
	 * @param context
	 */
	protected void setMarkSize(int cellWidth,int cellHeight,Context context){
		if(hasImageView){
			removeView(imageView);
			hasImageView = false;
		}
		Paint paint = new Paint();
		paint.setTextSize(textView.getTextSize());
		
		FontMetricsInt fmi = paint.getFontMetricsInt();
		
		int realTextSize = (fmi.bottom-fmi.top)/2;
		int imageSize = (cellHeight-realTextSize)/4;
		int topMargin = (int) (imageSize*1.5);
//		ProxyTalker.i("test ", "textSize -> "+textView.getTextSize());
//		ProxyTalker.i("test ", "fmi.top -> "+fmi.top);
//		ProxyTalker.i("test ", "fmi.bottom -> "+fmi.bottom);
//		ProxyTalker.i("test ", "fmi.ascent -> "+fmi.ascent);
//		ProxyTalker.i("test ", "fmi.descent -> "+fmi.descent);
//		ProxyTalker.i("test ", "fmi.leading -> "+fmi.leading);
//		
//		ProxyTalker.i("test ", "imageSize ->"+imageSize);
//		ProxyTalker.i("test ", "cellWidth ->"+cellWidth);
//		ProxyTalker.i("test ", "cellHeight ->"+cellHeight);
		
		LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		lp.height = imageSize;
		lp.width = imageSize;
		lp.topMargin = (int) (topMargin);
		float textWidth = paint.measureText(textView.getText().toString());
		lp.rightMargin = (int) (cellWidth*0.5-textWidth*0.75);
		lp.gravity = Gravity.RIGHT | Gravity.TOP;
		addView(imageView,lp);
		hasImageView = true;
	}
	
	public void setDate(Date date){
		this.mDate = date;
	}
	
	public Date getDate(){
		return mDate;
	}
	
	public interface OnViewClick{
		public void OnViewClick(Date date);
	}
}
