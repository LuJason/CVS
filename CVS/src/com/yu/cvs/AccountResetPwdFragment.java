package com.yu.cvs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.classes.CAccount;
import com.yu.cvs.classes.CustomToast;
import com.yu.cvs.dialog.WarnDialog;
import com.yu.cvs.myview.CountDownButton;
import com.yu.cvs.myview.CountDownButton.CountDownListener;
import com.yu.cvs.task.NetworkTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AccountResetPwdFragment extends AccBaseFragment implements TextWatcher, OnClickListener, CountDownListener {
	
	private EditText mPhone, mVerifyCode;
	
	private Button mResetPwd;
	
	private AccountActivity mParentActivty;
	
	private CountDownButton mGetVerifyCode;
	
	private ImageView mClearPhoneText;
	
	private String verifyCode;
	
	public static Fragment SELF;

	public static Fragment Instance(Context context) {
		if (SELF == null) {
			SELF = Fragment.instantiate(context, AccountResetPwdFragment.class.getName());
		}

		return SELF;
	}

	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mParentActivty = (AccountActivity) activity;
	}
	
	@Override
	protected void doHomeAsUp() {
		// TODO Auto-generated method stub
		super.doHomeAsUp();
		mParentActivty.showLoginFragment();
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.account_getpwd_fragment, null);
		
		setActionBarTitle(R.string.ResetPwd);
		
		mGetVerifyCode = (CountDownButton) rootView.findViewById(R.id.GetVerifyCode);
		mGetVerifyCode.setOnClickListener(this);
		
		mResetPwd = (Button) rootView.findViewById(R.id.AccountResetPwd);
		mResetPwd.setOnClickListener(this);
		
		mClearPhoneText = (ImageView) rootView.findViewById(R.id.clear_phone_text);
		mClearPhoneText.setOnClickListener(this);
		
		mPhone = (EditText) rootView.findViewById(R.id.AccountPhone);
		mPhone.addTextChangedListener(this);
		
		mVerifyCode = (EditText) rootView.findViewById(R.id.AccountCode);
		mVerifyCode.addTextChangedListener(this);
		
		checkButtonsEnable();
		
		return rootView;
	}
	
	private void checkButtonsEnable(){
		
		boolean isToEnableGetVerifyCodeButton = mPhone.length() >= 11;
		
		boolean isToEnableGetpwdButton = mPhone.length() >= 11 && mVerifyCode.length() >= 6 ;
		
		mGetVerifyCode.setEnabled(isToEnableGetVerifyCodeButton);
		
		mResetPwd.setEnabled(isToEnableGetpwdButton);
		
		if(mPhone.length() > 0 ){
			mClearPhoneText.setVisibility(View.VISIBLE);
		}else{
			mClearPhoneText.setVisibility(View.GONE);
		}
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		checkButtonsEnable();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.clear_phone_text:
			
			mPhone.setText("");
			
			break;
		case R.id.GetVerifyCode:
			
			getVerifyCode();
			
			break;
			
		case R.id.AccountGetPwd:
			
			resetUserPwd();
			
			break;
		}
	}

	private void resetUserPwd() {
		// TODO Auto-generated method stub
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "user_reset_pwdnew", false){

			private ProgressDialog mProgressDialog;
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setMessage(getString(R.string.RegisterIsDoing));
				mProgressDialog.setCanceledOnTouchOutside(false);
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
			}

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mProgressDialog.dismiss();
			}

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject result = new JSONObject(str);
					int status = result.getInt("status");
					
					switch(status){
					case 0:
					{
						
						JSONArray array = result.getJSONObject("result").getJSONArray("dataset");
						
						BaseFragmentActivity a = (BaseFragmentActivity) getActivity();
						CAccount acc = a.getAccount();
						acc.clear();
						acc.initilizeAfterRegister(array.getJSONObject(0));
						
//						AppInfoContent.saveAccount(getActivity(), mPhone.getText().toString(), mPwd.getText().toString());
						
						WarnDialog dialog = WarnDialog.newInstance(getActivity());
						
						dialog.setTitle(R.string.ResetPwd).setMessage(R.string.GetPasswordSuccessful).setPositiveBtn(R.string.confirm, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								mParentActivty.showLoginFragment();
							}

						}).show(getFragmentManager(), "RegisterSuccessfully");
						
					}
						break;
					case 4:
						CustomToast.MakeText(getActivity(), getString(R.string.GetPasswordUserExist)).show();
						break;
					case 3:
						CustomToast.MakeText(getActivity(), getString(R.string.RegisterErrorCodeInvalid)).show();
						break;
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		String phone = mPhone.getText().toString();
		
		String code = mVerifyCode.getText().toString();
		
		
		if(TextUtils.isEmpty(verifyCode) || !verifyCode.equals(code) ){
			
			CustomToast.MakeText(getActivity(), getString(R.string.RegisterErrorInputWrongCode)).show();
			
			return ;
		}
		
		try {
			
			JSONObject params = new JSONObject();
			params.put("isdn", phone);
			params.put("code", code);
			
			task.execute(params );
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void getVerifyCode() {
		// TODO Auto-generated method stub
		
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "user_isdn_code", false){
			
			private ProgressDialog mProgressDialog;
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				mProgressDialog = new ProgressDialog(getActivity());
				mProgressDialog.setCanceledOnTouchOutside(false);
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
			}
			
			

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
				mProgressDialog.dismiss();
				
			}

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject result = new JSONObject(str);
					int status = result.getInt("status");
					if(status == 0){
						
						JSONArray array = result.getJSONObject("result").getJSONArray("dataset");
						
						if(array.length() > 0){
							JSONObject obj = array.getJSONObject(0);
							verifyCode = obj.getString("code");
						}
						
						mGetVerifyCode.countDown(AccountResetPwdFragment.this);
						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		};
		
		try {
			String isdn = mPhone.getText().toString();
			JSONObject params = new JSONObject();
			params.put("isdn", isdn);
			task.execute(params);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void CountDownFinish() {
		// TODO Auto-generated method stub
		checkButtonsEnable();
	}

}
