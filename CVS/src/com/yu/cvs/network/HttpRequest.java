package com.yu.cvs.network;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.R;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.dialog.GProgressDialog;
import com.yu.cvs.dialog.WarnDialog;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

public class HttpRequest implements Runnable, Callback {
	private static final String TAG = "HttpRequest";
	
	private static final int MaxReconnectTimes = 5;
	
	public static final int BEGIN_TRANSMISSION = 10000;

	private FragmentActivity _fgAct;
	private JSONObject _params;
	private Handler _handler;
	private HttpCallback _callBack;
	private GProgressDialog _progressDialog;
	
	private int _reconnectTime = 0;

	public HttpRequest(FragmentActivity context, JSONObject params, HttpCallback callBack) {
		_reconnectTime = 0;
		_fgAct = context;
		_params = params;
		_callBack = callBack;
		_handler = new Handler(context.getMainLooper(), this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		CApplication app = (CApplication) _fgAct.getApplicationContext();
		CAccount acc = app.getAccount();
		String session = "";
		
		HttpConnection conn = HttpConnection.CreateHttpConnection();
		System.out.println(_params.toString());
		HttpResponse r = conn.sendRequestInPost(ProtocolDefinition.COMMANDURL, String.format("q=%s",_params.toString()), session, _handler);
		System.out.println(r.content);
		
		acc = null;
	}
	
	public void showProgressDialog(){
		
		if (_progressDialog == null) {

			_progressDialog = new GProgressDialog(_fgAct);
			_progressDialog.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					_callBack = null;
				}
			});
			_progressDialog.setOnDismissListener(new OnDismissListener() {
				
				@Override
				public void onDismiss(DialogInterface dialog) {
					// TODO Auto-generated method stub
					_callBack = null;
				}
			});

			_progressDialog.show();
		} else {
			if (!_progressDialog.isShowing()) {
				_progressDialog.show();
			}
		}
		
	}
	
	public void dissmisProgressDialog(){
		_progressDialog.dismiss();
	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		switch(msg.what){
		case BEGIN_TRANSMISSION:
			
			showProgressDialog();
			
			break;
		case HttpConnection.SUCCESS:
			
			String res = (String) msg.obj;
			
			if(!TextUtils.isEmpty(res)){
				
				try {
					JSONObject resObj = new JSONObject(res);
					
					int status = resObj.getInt("status");
					
					switch(status){
					case 0:
						
						JSONObject result = resObj.getJSONObject("result");
						
						if(_callBack != null) _callBack.sucessData(result.toString());
						
						break;
					case -1:
						
						
						Log.e(TAG, "Session Timeout");
						
						break;
					default:
						if(_callBack != null) _callBack.faildData(status, "");
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e(TAG, "Json 解析失败");
				} finally {
					dissmisProgressDialog();
				}
				
			}
			
			break;
		case HttpConnection.IO_ERROR:
			
			_reconnectTime ++ ;
			
			if(_reconnectTime > MaxReconnectTimes){
				
				if (_callBack != null) _callBack.ioError((String) msg.obj);
				
				if(_fgAct == null) break; 
	
				WarnDialog dialogTimeout = new WarnDialog(_fgAct);
				dialogTimeout
						.setTitle(R.string.network)
						.setMessage(R.string.network_time_out)
						.setPositiveBtn(R.string.retry,
								new DialogInterface.OnClickListener() {
	
									@Override
									public void onClick(DialogInterface dialog,	int which) {
										// TODO Auto-generated method stub
										_reconnectTime = 0;
										CThreadExecutor.execute(HttpRequest.this);
									}
								})
						.setNegativeBtn(R.string.cancel,
								new DialogInterface.OnClickListener() {
	
									@Override
									public void onClick(DialogInterface dialog,	int which) {
										// TODO Auto-generated method stub
										
									}
								});
				
				dissmisProgressDialog();
				
				if( !_fgAct.isFinishing()){
					
					FragmentTransaction transaction = _fgAct.getSupportFragmentManager().beginTransaction();
                    transaction.add(dialogTimeout, "TimeOut");
                    transaction.commitAllowingStateLoss();  
					
				}
				
			}else{
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					CThreadExecutor.execute(this);
				}
				
			}
			
			break;
			
		case HttpConnection.SAVE_SESSION:// 保存session
		{
			String session = (String) msg.obj;
			if (!TextUtils.isEmpty(session)) {

//				CAppliaction app = (CAppliaction) _fgAct.getApplicationContext();
//				CAccount acc = app.getAccount();
//				acc.setSession(session);
//				System.out.println(acc.toString());
			}
			break;
		}
		default:
			if(_callBack != null) _callBack.other((String) msg.obj);
		}
		
		return false;
	}
	
}
