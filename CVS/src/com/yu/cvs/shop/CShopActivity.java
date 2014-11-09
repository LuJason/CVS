package com.yu.cvs.shop;

import com.yu.cvs.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class CShopActivity extends ActionBarActivity {
	
	private static final String ListTag = "list";
	private static final String InfoTag = "info";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.common_fragment_activity);

		ActionBar bar = getSupportActionBar();
		int change = bar.getDisplayOptions() ^ ActionBar.DISPLAY_HOME_AS_UP;
		bar.setDisplayOptions(change, ActionBar.DISPLAY_HOME_AS_UP);
		
		
		Fragment f = createOrGetFragment(ListTag);
		getSupportFragmentManager().beginTransaction().add(R.id.container, f, ListTag).commit();
		
		
	}
	
	private Fragment createOrGetFragment(String tag){
		
		Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
		
		if(f == null){
			if(ListTag.equals(tag)){
				f = Fragment.instantiate(this, CShopListFragment.class.getName());
			}
			
			if(InfoTag.equals(tag)){
				f = Fragment.instantiate(this, CShopInfoFragment.class.getName());
			}
			
		}
		
		return f;
	}

}
