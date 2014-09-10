/**
 * 
 */
package com.yu.cvs.task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.classes.AccountCart;
import com.yu.cvs.classes.BaseCart;
import com.yu.cvs.classes.CacheCart;
import com.yu.cvs.classes.Good;
import com.yu.cvs.network.HttpConnection;
import com.yu.cvs.network.HttpResponse;
import com.yu.cvs.network.ProtocolDefinition;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author jason
 *
 */
public class UploadCacheCartTask extends AsyncTask<JSONObject, Integer, JSONObject> {
	private static final String TAG = "SyncCartTask";
	
	protected CApplication mApp;
	
	public UploadCacheCartTask(CApplication mApp) {
		super();
		this.mApp = mApp;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 * 
	 */
	@Override
	protected JSONObject doInBackground(JSONObject... params) {
		// TODO Auto-generated method stub
		
		String userid = new String(mApp.getAccount().getUserid());
		
		Log.d(TAG, "Upload Good In CacheCart To AccountCart");
		
		CacheCart c = CacheCart.Instance();
		
		try {
			for (Good g : c.getGoods()) {

				JSONObject request = new JSONObject();

				JSONObject param = new JSONObject();
				param.put("userid", userid);
				param.put("product_id", g.getProductId());
				param.put("count", g.getCount());

				request.put("cmd", "user_login");
				request.put("status", 0);
				request.put("seq", 1234);
				request.put("mac", "123456");
				request.put("param", param);

				HttpConnection conn = HttpConnection.CreateHttpConnection();
				HttpResponse r = conn.sendRequestInPost(ProtocolDefinition.COMMANDURL, String.format("q=%s", param.toString()), "", null);

				System.out.println(String.format("q=%s", param.toString()));
				System.out.println(r.content);
				
				if(r.responseCode == HttpConnection.SUCCESS){
					
//					JSONObject resObj = new JSONObject(r.content);
//				
//					int status = resObj.getInt("status");
//					
//					if(status == 0){
//						mApp.getAccount().getCart().addGood(g);
//					}
					
				}else{
					
					
					
					
					
					
					
					
				}
				
				
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			Log.w(TAG, "Upload failed");
			
		}
		
		Log.d(TAG, "Query Good In AccountCart");
		
		JSONObject query = new JSONObject();
		try {
			query.put("cmd", "user_cart_qry");
			query.put("status", 0);
			query.put("seq", 1234);
			query.put("mac", "123456");

			JSONObject param = new JSONObject();
			param.put("userid", userid);

			query.put("param", param);
			
			HttpConnection conn = HttpConnection.CreateHttpConnection();
			HttpResponse r = conn.sendRequestInPost(ProtocolDefinition.COMMANDURL, String.format("q=%s", query.toString()), "", null);
			
			System.out.println(String.format("q=%s", param.toString()));
			System.out.println(r.content);
			
			if(r.responseCode == HttpConnection.SUCCESS){
			
				JSONObject resObj = new JSONObject(r.content);
			
				int status = resObj.getInt("status");
				
				if(status == 0){
					JSONArray array = resObj.getJSONObject("result").getJSONArray("dataset");
					
					BaseCart cart = mApp.getAccount().getCart();
					
					if(cart instanceof AccountCart){
					
						((AccountCart)cart).initialize(array);
					}
				}
				
				
			}else{
				
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {}
		
		return null;
	}
	
}
