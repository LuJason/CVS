package com.yu.cvs.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.R;

public class CAddressActivity extends BaseFragmentActivity {
	
	Fragment mList, mAdd, mEdit; 
	
	public static final String mListTag = "List";
	public static final String mAddTag = "Add";
	public static final String mEditTag = "Edit";

	private FragmentManager _manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_fragment_activity);
		
		ActionBar bar = getSupportActionBar();
		int change = bar.getDisplayOptions() ^ ActionBar.DISPLAY_HOME_AS_UP;
		bar.setDisplayOptions(change, ActionBar.DISPLAY_HOME_AS_UP);
		
		mList = Fragment.instantiate(this, CAddressListFragment.class.getName(), null);
		mAdd = Fragment.instantiate(this, CAddressAddFragment.class.getName(), null);
		mEdit = Fragment.instantiate(this, CAddressEditFragment.class.getName(), null);
		
		_manager = getSupportFragmentManager();
		FragmentTransaction ft = _manager.beginTransaction();
		ft.add(R.id.container, mList, mListTag);
		ft.commitAllowingStateLoss();
		
	}
	
	
	
	public void showAddressAddFragment(){
		FragmentTransaction ft = _manager.beginTransaction();
		ft.replace(R.id.container, mAdd, mAddTag);
		ft.commitAllowingStateLoss();
	}
	
	public void showAddressListFragment(){
		FragmentTransaction ft = _manager.beginTransaction();
		ft.replace(R.id.container, mList, mListTag);
		ft.commitAllowingStateLoss();
	}
	
	public void showAddressEditFragment(String addressid){
		
		Bundle params = new Bundle();
		params.putString(CAddressEditFragment.KEY_ADDRESS_ID, addressid);
		mEdit.setArguments(params);
		
		FragmentTransaction ft = _manager.beginTransaction();
		ft.replace(R.id.container, mEdit, mEditTag);
		ft.commitAllowingStateLoss();
	}

}
