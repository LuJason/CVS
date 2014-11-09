package com.yu.cvs.classes;

import com.yu.cvs.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast extends Toast {

	public CustomToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public static CustomToast MakeText(Context context, String text){
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflater.inflate(R.layout.custom_toast, null);
		
		// set a message
		TextView toastText = (TextView) v.findViewById(R.id.toasttext);
		toastText.setText(text);

		// Toast...
		CustomToast toast = new CustomToast(context);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 300);
		toast.setView(v);
		
		return toast;
	}
	
}
