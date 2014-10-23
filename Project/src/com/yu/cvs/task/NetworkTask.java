/**
 * 
 */
package com.yu.cvs.task;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.network.HttpConnection;
import com.yu.cvs.network.HttpResponse;
import com.yu.cvs.network.ProtocolDefinition;

import android.os.AsyncTask;

/**
 * @author jason
 *
 */
public class NetworkTask extends AsyncTask<JSONObject, Integer, JSONObject> {
	
	private static final String KEY_CODE = "code";
	private static final String KEY_CONTENT = "content";
	
	protected CApplication mApplication;
	
	private boolean mDoInBackground;
	
	private String mCommand;
	
	public NetworkTask(CApplication app, String command, boolean doInBackground) {
		super();
		this.mApplication = app;
		this.mDoInBackground = doInBackground;
		this.mCommand = new String(command);
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected JSONObject doInBackground(JSONObject... params) {
		// TODO Auto-generated method stub
		JSONObject param = params[0];
		
		JSONObject request = new JSONObject();
		
		try {
			request.put("cmd", mCommand);
			request.put("status", 0);
			request.put("seq", TaskHelper.GenerageSequence(mApplication));
			request.put("mac", "123456");
			request.put("param", param);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		int time = 5 ;
		HttpResponse r = null;
		while(time > 0){
			
			HttpConnection conn = HttpConnection.CreateHttpConnection();
			System.out.println(request.toString());
			r = conn.sendRequestInPost(ProtocolDefinition.COMMANDURL, String.format("q=%s",request.toString()), "", null);
			System.out.println(r.content);
			
			if (r.responseCode == HttpConnection.IO_ERROR) {

				time--;

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			} else{
				
				break;
				
			}
		}
		
		if(mDoInBackground){
			
			switch(r.responseCode){
			case HttpConnection.IO_ERROR:
				ioError(r.content, mDoInBackground);
				break;
			case HttpConnection.SUCCESS:
				responseData(r.content, mDoInBackground);
				break;
			}
			return null;
			
		}else{
			JSONObject res = null;
			try {
				res = new JSONObject();
				res.put(KEY_CODE, r.responseCode);
				res.put(KEY_CONTENT, r.content);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return res;
		}
	}
		

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(result == null){
			return ;
		}
		
		int code;
		try {
			code = result.getInt(KEY_CODE);
			String content = result.getString(KEY_CONTENT);
			switch (code) {
			case HttpConnection.IO_ERROR:
				ioError(content, mDoInBackground);
				break;
			case HttpConnection.SUCCESS:
				responseData(content, mDoInBackground);
				break;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void responseData(String str, boolean isBackground){
		System.out.println(String.format("responseData(), Running is background is %b",isBackground));
	};
	protected void ioError(String str, boolean isBackground){
		System.out.println(String.format("ioError(), Running is background is %b",isBackground));
	};
	
}
