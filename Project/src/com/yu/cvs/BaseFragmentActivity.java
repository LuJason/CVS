package com.yu.cvs;

import android.support.v7.app.ActionBarActivity;
import com.yu.cvs.classes.CAccount;

public class BaseFragmentActivity extends ActionBarActivity {
	
	public CAccount getAccount() {
		CApplication app = (CApplication) getApplication();
		CAccount acc = app.getAccount();
		return acc;
	}
	
}
