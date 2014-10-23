package com.yu.cvs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.classes.CAccount;
import com.yu.cvs.classes.CacheCart;
import com.yu.cvs.classes.Cart;
import com.yu.cvs.classes.Good;
import com.yu.cvs.network.CThreadExecutor;
import com.yu.cvs.network.HttpCallback;
import com.yu.cvs.network.HttpConnection;
import com.yu.cvs.network.HttpRequest;
import com.yu.cvs.network.HttpResponse;
import com.yu.cvs.network.ProtocolDefinition;
import com.yu.cvs.provider.AppInfoContent;
import com.yu.cvs.task.NetworkTask;
import com.yu.cvs.task.UploadCacheCartTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AccountLoginFragment extends Fragment implements TextWatcher, OnClickListener {

	public static Fragment SELF;

	private Button mLogin;

	private EditText mAccoutId, mAccountPwd;
	
	private LoginFramentCallBack mCallBack;
	
	private ProgressDialog mProgressDialog ;

	public static Fragment Instance() {
		if (SELF == null) {
			SELF = new AccountLoginFragment();
		}

		return SELF;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		((BaseFragmentActivity) getActivity()).getSupportActionBar().setTitle(R.string.login);
		View v = inflater.inflate(R.layout.account_login_fragment, null);
		mLogin = (Button) v.findViewById(R.id.AccountLogin);
		mLogin.setOnClickListener(this);

		mAccoutId = (EditText) v.findViewById(R.id.AccoutId);
		mAccoutId.setText(AppInfoContent.getLoginid(getActivity()));
		mAccoutId.addTextChangedListener(this);
		
		mAccountPwd = (EditText) v.findViewById(R.id.AccoutPwd);
		mAccountPwd.setText(AppInfoContent.getPassword(getActivity()));
		mAccountPwd.addTextChangedListener(this);
		
		checkLoginButton();

		return v;
	}
	
	private void checkLoginButton(){
		
		if(mAccoutId.length() == 0 || mAccountPwd.length() == 0){
			mLogin.setEnabled(false);
		}else{
			mLogin.setEnabled(true);
		}
		
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		mCallBack = (LoginFramentCallBack) activity;
		
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.account_login, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.action_register:
			
			if(mCallBack != null)
				mCallBack.showRegisterFragment();
			
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		checkLoginButton();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.AccountLogin:
			mLogin.setEnabled(false);
			login(mAccoutId.getText().toString(), mAccountPwd.getText().toString());
			break;
		}
	}

	private String getMD5(String data) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		md5.update(data.getBytes());
		byte[] m = md5.digest();// 加密

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < m.length; i++) {
			int b = (0xFF & m[i]);
			// if it is a single digit, make sure it have 0 in front (proper
			// padding)
			if (b <= 0xF)
				sb.append("0");
			// add number to string
			sb.append(Integer.toHexString(b));
		}

		return sb.toString();
	}

	private void login(String id, String pwd) {

		// userid 用户名，邮箱，手机
		// userpwd 用户密码md5、32位

		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "user_login", false){
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				
				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setMessage("正在验证用户信息");
				mProgressDialog.setCanceledOnTouchOutside(false);
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
				
			}
			

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mLogin.setEnabled(false);
			}


			@Override
			protected void ioError(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.ioError(str, isBackground);
			}


			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject resObj = new JSONObject(str);
					int status = resObj.getInt("status");
					
//					switch(status){
//					case 0:
//						passLogin(resObj.getJSONObject("result"));
//						break;
//					case 
//					default:
//					}
					
					if(status == 0){
						passLogin(resObj.getJSONObject("result"));
					}else{
						mProgressDialog.dismiss();
						showLoginErrorDialog();
					}
					
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};

		JSONObject param = new JSONObject();

		try {

			param.put("userid", mAccoutId.getText().toString());
			param.put("userpwd", getMD5(mAccountPwd.getText().toString()));


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AsyncTask<JSONObject, Integer, JSONObject> t = task.execute(param);

	}
	
	private void showLoginErrorDialog() {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder builder = new Builder(getActivity());

		builder.setTitle(R.string.login)
				.setMessage(R.string.login_error)
				.setPositiveButton(R.string.confirm,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,	int which) {
								// TODO Auto-generated method stub
								checkLoginButton();
							}
						}).create().show();
		
	}

	
	private void passLogin(JSONObject data){
		
		JSONArray array;
		try {
			array = data.getJSONArray("dataset");
			
			BaseFragmentActivity a = (BaseFragmentActivity) getActivity();
			CAccount acc = a.getAccount();
			
			acc.initilize(array.getJSONObject(0));
			
			AppInfoContent.saveAccount(getActivity(), mAccoutId.getText().toString(), mAccountPwd.getText().toString());
			
			queryCart();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void queryCart(){
		
		UploadCacheCartTask task = new UploadCacheCartTask((CApplication) getActivity().getApplication()){
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				if(mProgressDialog == null){
					mProgressDialog = new ProgressDialog(getActivity());
					mProgressDialog.setCanceledOnTouchOutside(false);
					mProgressDialog.setCancelable(false);
					mProgressDialog.show();
				}
				
				mProgressDialog.setMessage("更新购物车信息");
			}

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
				mProgressDialog.dismiss();
				
				getActivity().setResult(Activity.RESULT_OK);
				getActivity().finish();
			}
			
		};
		task.execute(new JSONObject());
	}
	
	

	public interface LoginFramentCallBack {
		public void showRegisterFragment();
	}
	

}
