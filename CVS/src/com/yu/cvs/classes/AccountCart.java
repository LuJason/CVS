/**
 * 
 */
package com.yu.cvs.classes;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.CApplication;
import com.yu.cvs.exception.NotLoginException;
import com.yu.cvs.shop.CartActivity;
import com.yu.cvs.task.NetworkTask;

/**
 * @author jason
 *
 */
public class AccountCart extends  BaseCart {
	
	private Object mInitializationLock = new Object();
	private boolean mInitializedDone;
	
	public AccountCart() {
		super();
		// TODO Auto-generated constructor stub
		mInitializedDone = true;
	}
	
	public void initialize(JSONArray array){
		mInitializedDone = false;
		synchronized (mInitializationLock) {
		
			this._goodsList.clear();
			
			try {
				for (int i = 0, length = array.length(); i < length; i++) {
					Good g = new Good();
					g.initialize(array.getJSONObject(i));
					this._goodsList.add(g);
				}

			} catch (JSONException e) {}
			
			mInitializedDone = true;
			mInitializationLock.notifyAll();
		}
		
	}
	

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.Cart#generateOrder()
	 */
	@Override
	public void generateOrder() throws NotLoginException {
		// TODO Auto-generated method stub
		
		
		
		
	}
	

	@Override
	public ArrayList<Good> getGoods() {
		// TODO Auto-generated method stub
		
		synchronized (mInitializationLock) {

			try {
				while (!mInitializedDone) {
					mInitializationLock.wait();
				}
			} catch (InterruptedException e) {}

			return super.getGoods();

		}
		
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.Cart#refreshGoods(com.yu.cvs.BaseFragmentActivity)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> initializeCart(final Fragment context) {
		// TODO Auto-generated method stub
		
		final BaseFragmentActivity bfa = (BaseFragmentActivity) context.getActivity();
		
		String userid = new String(bfa.getAccount().getUserid());
		
		JSONObject param = new JSONObject();
		try {

			param.put("userid", userid);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		NetworkTask task = new NetworkTask((CApplication) bfa.getApplication(), "user_cart_qry" , false){
			
			private ProgressDialog dialog ;
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
//				dialog = new ProgressDialog(bfa);
//				dialog.setMessage("更新用户数据");
//				dialog.setCanceledOnTouchOutside(false);
//				dialog.setCancelable(false);
//				dialog.show();
				
			}

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
//				dialog.dismiss();
			}

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject resObj = new JSONObject(str);
					int status = resObj.getInt("status");
					
					switch(status){
					case 0:
						JSONArray array = resObj.getJSONObject("result").getJSONArray("dataset");
						initialize(array);
//						((CartActivity)bfa).loadFragment();
						break;
					default:
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		return  task.execute(param);
		
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.Cart#adjustGoodQuantity(com.yu.cvs.BaseFragmentActivity, java.lang.String, int)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> adjustGoodQuantity(final Fragment context, final String pid, final int quantity) {
		// TODO Auto-generated method stub
		
		JSONObject param = new JSONObject();
		try {

			String id = ((BaseFragmentActivity)context.getActivity()).getAccount().getUserid();
			param.put("userid", id);
			param.put("product_id", pid);
			param.put("count", quantity);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetworkTask task = new NetworkTask((CApplication) context.getActivity().getApplication(), "user_cart_edt", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject resObj = new JSONObject(str);
					int status = resObj.getInt("status");
					
					switch(status){
					case 0:
//						reloadGoodsInCart(context);
						for(Good g : _goodsList){
							if(pid.equals(g.getProductId())){
								g.setCount(quantity);
								g.setSelected(true);
								notifyGoodsListChanged();
								break;
							}
						}
						break;
					
					default:
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		};
		
		
		
		return task.execute(param);
	}
	
	public void reloadGoodsInCart(final Fragment context ){
		
		BaseFragmentActivity bfa = (BaseFragmentActivity) context.getActivity();
		
		String userid = new String(bfa.getAccount().getUserid());
		
		JSONObject param = new JSONObject();
		try {
			param.put("userid", userid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		NetworkTask task = new NetworkTask((CApplication) bfa.getApplication(), "user_cart_qry", false){
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
			}

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject resObj = new JSONObject(str);
					int status = resObj.getInt("status");
					
					switch(status){
					case 0:
						JSONArray array = resObj.getJSONObject("result").getJSONArray("dataset");
//						mApplication.getAccount().getCart().initialize(array);
						initialize(array);
						notifyGoodsListChanged();
						break;
					default:
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		task.execute(param);
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#removeGoodFromCart(android.support.v4.app.Fragment, java.lang.String)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> removeGoodFromCart(Fragment context, final String pid) {
		// TODO Auto-generated method stub
		
		BaseFragmentActivity bfa = (BaseFragmentActivity) context.getActivity();
		
		String userid = new String(bfa.getAccount().getUserid());
		
		JSONObject param = new JSONObject();
		try {
			param.put("userid", userid);
			param.put("product_id", pid);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetworkTask task = new NetworkTask((CApplication) bfa.getApplication(), "user_cart_add", true){
			
			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject resObj = new JSONObject(str);
					int status = resObj.getInt("status");
					
					switch(status){
					case 0:
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
						
						break;
					default:
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		return task.execute(param);
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.BaseCart#addGoodToCart(android.support.v4.app.Fragment, com.yu.cvs.classes.Good)
	 */
	@Override
	public AsyncTask<JSONObject, Integer, JSONObject> addGoodToCart(Fragment context, final Good newGood) {
		// TODO Auto-generated method stub
		
		BaseFragmentActivity bfa = (BaseFragmentActivity) context.getActivity();
		
		String userid = new String(bfa.getAccount().getUserid());
		
		JSONObject param = new JSONObject();
		try {

			param.put("userid", userid);
			param.put("product_id", newGood.getProductId());
			param.put("count", newGood.getCount());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetworkTask task = new NetworkTask((CApplication) bfa.getApplication(), "user_cart_add", true){
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
			}

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
			}

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject resObj = new JSONObject(str);
					int status = resObj.getInt("status");
					
					switch(status){
					case 0:
						
						synchronized (mInitializationLock) {

							try {
								while (!mInitializedDone) {
									mInitializationLock.wait();
								}
							} catch (InterruptedException e) {
							}

							String pid = newGood.getProductId();

							boolean findGood = false;

							for (Good g : _goodsList) {

								if (pid.equals(g.getProductId())) {
									findGood = true;
									g.setCount(g.getCount()	+ newGood.getCount());
									break;
								}

							}

							if (!findGood){
								_goodsList.add(newGood);
								notifyGoodsListChanged();
							}
						}
						
						break;
					default:
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		return task.execute(param);
	}
	
}
