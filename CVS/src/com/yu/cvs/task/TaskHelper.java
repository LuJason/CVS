package com.yu.cvs.task;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class TaskHelper {
	
	private static final String APP_NAME = "CVS_SharedPreferences";
	
	public static long GenerageSequence(Application app){
		
		SharedPreferences p = app.getSharedPreferences(APP_NAME, 0);
		long seq = p.getLong("Sequence", 0);
		
		Editor editor = p.edit();
		editor.putLong("Sequence", ++seq);
		editor.commit();
		
		return seq;
		
	}

}
