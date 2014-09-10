package com.yu.cvs.provider;

import android.content.Context;
import android.content.SharedPreferences;

public class AppInfoContent {

	private static final String APP_INFOS = "CVS";

	private static final String PASSWORD = "password";
	private static final String LOGINID = "loginid";
	
	private static final String TAG = "AppInfoContent";

	public static void saveAccount(Context context, String loginid, String password) {

		SharedPreferences settings = context.getSharedPreferences(APP_INFOS, 0);
		SharedPreferences.Editor editor = settings.edit();

		editor.putString(LOGINID, loginid);
		editor.putString(PASSWORD, password);

		editor.commit();
	}

	public static String getLoginid(Context context) {
		SharedPreferences settings = context.getSharedPreferences(APP_INFOS, 0);
		return settings.getString(LOGINID, "");
	}

	public static String getPassword(Context context) {
		SharedPreferences settings = context.getSharedPreferences(APP_INFOS, 0);
		return settings.getString(PASSWORD, "");
	}

}
