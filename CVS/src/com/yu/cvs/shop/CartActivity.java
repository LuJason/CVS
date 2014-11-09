package com.yu.cvs.shop;

import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.R;
import com.yu.cvs.classes.BaseCart;
import com.yu.cvs.classes.CAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

public class CartActivity extends BaseFragmentActivity{
	
	private BaseCart _cart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_activity);
		
		ActionBar bar = getSupportActionBar();
		int change = bar.getDisplayOptions() ^ ActionBar.DISPLAY_HOME_AS_UP;
		bar.setDisplayOptions(change, ActionBar.DISPLAY_HOME_AS_UP);
		
		loadFragment();
		
	}
	
	public BaseCart getCart(){
		return this._cart;
	}
	
//	private Cart initializeCart(){
//		
//		CAccount acc = getAccount(); 
//		
//		if(acc.isLogin()){
//			_cart = acc.getCart();
//		}else{
//			_cart = CacheCart.Instance((CApplication) getApplication());
//		}
//		
//		if(null == _cart.initializeCart(this))
//			loadFragment();
//		
//		return _cart;
//		
//	}

	/**
	 * 
	 */
	public void loadFragment() {
		// TODO Auto-generated method stub
		
		CAccount acc = getAccount(); 
		
		this._cart = acc.getCart();
		
		if (_cart.countSelectedGoodsInCart() == 0) {

			Fragment f = Fragment.instantiate(this,	CartEmptyFragment.class.getName());

			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, f, "Empty").commit();
			
		}else{
			
			Fragment f = Fragment.instantiate(this,	CartFragment.class.getName());

			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, f, "Cart").commit();
			
		}
		
		
		
	}
	
}
