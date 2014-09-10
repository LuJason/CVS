/**
 * 
 */
package com.yu.cvs.classes;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.yu.cvs.CApplication;
import com.yu.cvs.exception.NotLoginException;

/**
 * @author jason
 *
 */
public class CacheCart extends BaseCart {
	
private static CacheCart CART ; 

	private static final String TAG = CacheCart.class.getName();
	
	public static CacheCart Instance(){
		
		if(CART == null)
			CART = new CacheCart();
		return CART;
	}

	public CacheCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#generateOrder()
	 */
	@Override
	public void generateOrder() throws NotLoginException {
		// TODO Auto-generated method stub
		throw new NotLoginException();
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#initializeCart(android.support.v4.app.Fragment)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> initializeCart(Fragment context) {
		// TODO Auto-generated method stub
		notifyGoodsListChanged();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#adjustGoodQuantity(android.support.v4.app.Fragment, java.lang.String, int)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> adjustGoodQuantity(Fragment context, String pid, int quantity) {
		// TODO Auto-generated method stub
		
		for(Good g: _goodsList){
			if(pid.equals(g.getProductId())){
				g.setCount(quantity);
				break;
			}
		}
		
		notifyGoodsListChanged();
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#removeGoodFromCart(android.support.v4.app.Fragment, java.lang.String)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> removeGoodFromCart(Fragment context, String pid) {
		// TODO Auto-generated method stub
		
		Good toDel = null;
		
		for(Good g: _goodsList){
			if(pid.equals(g.getProductId())){
				toDel = g;
				break;
			}
		}
		
		if(toDel != null){
			_goodsList.remove(toDel);
			notifyGoodsListChanged();// 通知购物车里的货物已经发生变化
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#addGoodToCart(android.support.v4.app.Fragment, java.lang.String)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> addGoodToCart(Fragment context, Good newGood) {
		// TODO Auto-generated method stub
		String pid = newGood.getProductId();
		
		boolean findGood = false;
		
		for(Good g: _goodsList){
			
			if(pid.equals(g.getProductId())){
				findGood = true;
				g.setCount(g.getCount() + newGood.getCount());
				break;
			}
			
		}
		
		if(!findGood)
			_goodsList.add(newGood);
		
		notifyGoodsListChanged();
		
		return null;
	}

	
}
