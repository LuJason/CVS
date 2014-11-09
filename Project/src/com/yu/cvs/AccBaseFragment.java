package com.yu.cvs;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class AccBaseFragment extends Fragment {
	
	protected void doHomeAsUp() {
		
	}
	
	protected void setActionBarTitle(int rid) {
		
		ActionBarActivity activity = (ActionBarActivity) getActivity();
		ActionBar bar = activity.getSupportActionBar();
		
		bar.setTitle(rid);
		
	}

}
