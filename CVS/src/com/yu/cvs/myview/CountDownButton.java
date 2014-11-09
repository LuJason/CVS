package com.yu.cvs.myview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

public class CountDownButton extends Button {

	public CountDownButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public CountDownButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CountDownButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static interface CountDownListener{
		public void CountDownFinish();
	}
	
	private Handler mHandler;
	private int second;
	private CountDownListener listener;
	
	private boolean isCounting;
	
	private String cacheText ;
	
	public void countDown(CountDownListener l){
		
		second = 60;
		listener = l;
		cacheText = getText().toString();
		setEnabled(false);
		
		isCounting = true;
		
		if (mHandler == null) {

			mHandler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					super.handleMessage(msg);

					setText(String.format("%d秒", second));
					
					if(second >= 0){
						second --;
						sendEmptyMessageDelayed(0, 1000);
					}else{
						
						setText(cacheText);
						isCounting = false;
						if(listener != null)
							listener.CountDownFinish();
						
					}

				}

			};
		}
		
		mHandler.sendEmptyMessage(0);
		
	}

	@Override
	public void setEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
		if(isCounting){
			super.setEnabled(false);
		}else{
			super.setEnabled(enabled);
		}
	}
	
	
	

}
