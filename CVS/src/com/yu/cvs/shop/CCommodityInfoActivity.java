package com.yu.cvs.shop;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.DownloadManager.Query;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.CApplication;
import com.yu.cvs.MainCategoryFragment;
import com.yu.cvs.MainHomeFragment;
import com.yu.cvs.R;
import com.yu.cvs.classes.AccountCart;
import com.yu.cvs.classes.AttributeManager;
import com.yu.cvs.classes.BaseCart;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.classes.CacheCart;
import com.yu.cvs.classes.Commodity;
import com.yu.cvs.classes.Good;
import com.yu.cvs.classes.ProductInfo;
import com.yu.cvs.task.NetworkTask;

public class CCommodityInfoActivity extends BaseFragmentActivity {
	
	public static final String INTENTACTION = "com.yu.cvs.intent.CommodityInfo.view";
	
	public static final String KEY_COMMODITY = "KEY_COMMODITY";
	
	private final Object mCommodityInfoLock = new Object();
	private boolean mCommodityInitializing = true;

	private FragmentTabHost mTabHost;
	
	private Commodity _commodity;
	
	private AttributeManager mAttributeManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle params = getIntent().getExtras();
		_commodity = params.getParcelable(KEY_COMMODITY);
		
		queryCommodityInfoByBarcode(_commodity.getBarcode());
		
		setContentView(R.layout.commodity_info_activity);
		
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		mTabHost.getTabWidget().setDividerDrawable(null);
		
		View view = this.getLayoutInflater().inflate(R.layout.comm_info_tab, null);
		TextView label = (TextView) view.findViewById(R.id.tab_label);
		label.setText("基本信息");
		mTabHost.addTab(mTabHost.newTabSpec("brief").setIndicator(view), CommInfoBriefFragment.class, null);
		
		view = this.getLayoutInflater().inflate(R.layout.comm_info_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		label.setText("商品详情");
		mTabHost.addTab(mTabHost.newTabSpec("detail").setIndicator(view), CommInfoDetailsFragment.class, null);
		
		view = this.getLayoutInflater().inflate(R.layout.comm_info_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		label.setText("用户评价");
		mTabHost.addTab(mTabHost.newTabSpec("comment").setIndicator(view), CommInfoCommentFragment.class, null);
		
		mAttributeManager = new AttributeManager(_commodity);
		
	}
	
	private void queryCommodityInfoByBarcode(final String barcode) {
		// TODO Auto-generated method stub
		
		NetworkTask task = new NetworkTask((CApplication) getApplication(), "product_qry_bybarcode", true){

			@Override
			protected JSONObject doInBackground(JSONObject... params) {
				// TODO Auto-generated method stub
				
				JSONObject obj = null;
				synchronized (mCommodityInfoLock) {
					
					obj = super.doInBackground(params);
		            
		            mCommodityInitializing = false; // Finished initialization
		            mCommodityInfoLock.notifyAll(); // Wake any waiting threads
		            
		        }
				
				return obj;
			}

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject obj = new JSONObject(str);
					
					int status = obj.getInt("status");
					
					switch(status){
					case 0:
						
						JSONArray dataset = obj.getJSONObject("result").getJSONArray("dataset");
						
//						_products.clear();
						
						for(int i=0,length=dataset.length(); i<length; i++){
							ProductInfo p = new ProductInfo();
							if(p.initialize(dataset.getJSONObject(i))){
//								_products.add(p);
								
								mAttributeManager.addProoduct(p);
								
							}
						}
						
						mAttributeManager.initialize();
						
						break;
						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					
					
				}
				
			}
			
		};
		
		
		JSONObject params = new JSONObject();
		try {
			params.put("barcode", barcode);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		task.execute(params);
		
	}

	public AttributeManager getCommodityInfo() {

		synchronized (mCommodityInfoLock) {
			// Wait while disk cache is started from background thread
			while (mCommodityInitializing) {
				try {
					mCommodityInfoLock.wait();
				} catch (InterruptedException e) { }
			}

			return mAttributeManager;
			
		}
		
	}

	public void addToCart(Fragment f, ProductInfo p, int quantity) {
		// TODO Auto-generated method stub
		CAccount acc = getAccount();
		
		BaseCart cart = acc.getCart();
		
		Good newGood = Good.InstancFromProductInfo(p, quantity);
		
		cart.addGoodToCart(f, newGood);
		
	}
	

}
