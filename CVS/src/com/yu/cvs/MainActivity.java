package com.yu.cvs;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.dialog.WarnDialog;
import com.yu.cvs.shop.CartActivity;

import android.support.v4.app.FragmentTabHost;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseFragmentActivity {
	
	private FragmentTabHost mTabHost;
	private LocationClient mLocClient;
	private MyLocationListener mMyLocationListener;
	
	private String currentSelectTabTag = ""; 
	private String lastSelectTabTag = ""; 

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getLoaction();
		
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.main_real_content);
		mTabHost.getTabWidget().setDividerDrawable(null);
		
		View view = this.getLayoutInflater().inflate(R.layout.main_tab, null);
		TextView label = (TextView) view.findViewById(R.id.tab_label);
		ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
		
		label.setText("首页");
		icon.setImageResource(R.drawable.tab_home);
		mTabHost.addTab(mTabHost.newTabSpec("Home").setIndicator(view), MainHomeFragment.class, null);
		

		view = this.getLayoutInflater().inflate(R.layout.main_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		icon = (ImageView) view.findViewById(R.id.tab_icon);
		label.setText("分类");
		icon.setImageResource(R.drawable.tab_category);
		mTabHost.addTab(mTabHost.newTabSpec("Category").setIndicator(view), MainCategoryFragment.class, null);
		
		
		view = this.getLayoutInflater().inflate(R.layout.main_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		icon = (ImageView) view.findViewById(R.id.tab_icon);
		label.setText("购物车");
		icon.setImageResource(R.drawable.tab_cart);
		mTabHost.addTab(mTabHost.newTabSpec("Cart").setIndicator(view), MainCartFragment.class, null);
		
		
		view = this.getLayoutInflater().inflate(R.layout.main_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		icon = (ImageView) view.findViewById(R.id.tab_icon);
		label.setText("优惠活动");
		icon.setImageResource(R.drawable.tab_promotion);
		mTabHost.addTab(mTabHost.newTabSpec("Conference").setIndicator(view), MainPreferentialFragment.class, null);
		
		
		view = this.getLayoutInflater().inflate(R.layout.main_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		icon = (ImageView) view.findViewById(R.id.tab_icon);
		label.setText("我");
		icon.setImageResource(R.drawable.tab_account);
		mTabHost.addTab(mTabHost.newTabSpec("Account").setIndicator(view), MainAccountFragment.class, null);

		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
		
		currentSelectTabTag = "Home";
		lastSelectTabTag = "";
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				System.out.println( "TAB : " + tabId );
				lastSelectTabTag = currentSelectTabTag;
				currentSelectTabTag = tabId;
				
				
				
				if("Cart".equals(tabId)){

					startActivity(new Intent(MainActivity.this, CartActivity.class));
					
					selectLastTab();
					
				}
				
			}
		});

	}
	
	public void selectLastTab(){
		mTabHost.setCurrentTabByTag(lastSelectTabTag);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			
			
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getLoaction() {
		// TODO Auto-generated method stub
		
		boolean mLocationInit = false;
		
		mLocClient = ((CApplication)getApplication()).mLocationClient;
		mMyLocationListener = new MyLocationListener();
		mLocClient.registerLocationListener(mMyLocationListener);
		
		try {
			LocationClientOption option = new LocationClientOption();
			option.setLocationMode(LocationMode.Hight_Accuracy);
			option.setCoorType("bd09ll");
			option.setScanSpan(10000);// 小于1000 为单次请求，需要主动发起请求
			option.setNeedDeviceDirect(false);
			option.setIsNeedAddress(true);
			mLocClient.setLocOption(option);
			mLocationInit = true;
		} catch (Exception e) {
			e.printStackTrace();
			mLocationInit = false;
		}
		
		//开始定位
		if (mLocationInit) {
			mLocClient.start();
		} else {
			Toast.makeText(this, "请设置定位相关的参数", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (mLocClient.isStarted()) {
			//单次请求定位
			mLocClient.requestLocation();
		} 
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		getAccount().clear();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		int a = getSupportFragmentManager().getBackStackEntryCount();
		if (a == 0){
			WarnDialog dialog = new WarnDialog(this);
			dialog.setTitle(R.string.exit)
					.setMessage(R.string.exit_message)
					.setPositiveBtn(R.string.confirm, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,	int which) {
									// TODO Auto-generated method stub
									finish();
								}
							})
					.setNegativeBtn(R.string.cancel, new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,	int which) {
									// TODO Auto-generated method stub

								}
							});
			
			dialog.show(getSupportFragmentManager(), "EXIT");
			
		} else
			getSupportFragmentManager().popBackStack();
		
	}
	
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			
			if (location == null)
				return;
			
			Log.i("GolfAppliaction", String.format("City code : %s, City : %s", location.getCityCode(), location.getCity()));
			StringBuffer sb = new StringBuffer(256);
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\ndistrict:");
			sb.append(location.getDistrict());
			sb.append("\nstreet:");
			sb.append(location.getStreet());
			Log.i("GolfAppliaction", sb.toString());
			
			CAccount acc = ((CApplication)getApplication()).getAccount();
			acc.setLatitude(location.getLatitude());
			acc.setLongitude(location.getLongitude());
			acc.setCity(location.getCity());
			acc.setDistrict(location.getDistrict());
			acc.setStreet(location.getStreet());
			
			if( ! (Math.abs(acc.getLatitude()-0) < 0.00000001) 
					&& !(Math.abs(acc.getLongitude()-0) < 0.00000001) 
					&& !TextUtils.isEmpty(acc.getCity())){
				mLocClient.stop();
			}
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}