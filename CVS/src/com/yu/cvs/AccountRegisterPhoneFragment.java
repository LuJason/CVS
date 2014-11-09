package com.yu.cvs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.classes.CAccount;
import com.yu.cvs.classes.CustomToast;
import com.yu.cvs.dialog.WarnDialog;
import com.yu.cvs.myview.CountDownButton;
import com.yu.cvs.myview.CountDownButton.CountDownListener;
import com.yu.cvs.network.CvsUtils;
import com.yu.cvs.provider.AppInfoContent;
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

public class AccountRegisterPhoneFragment extends AccBaseFragment implements TextWatcher, OnClickListener, CountDownListener {
	
	private EditText mPhone, mPwd, mVerifyCode;
	
	private ImageView mClearPhoneText, mClearPwdText;
	
	private CountDownButton mGetVerifyCode;
	
	private Button mRegister;

	private String verifyCode;
	
	public static Fragment SELF;

	public static Fragment Instance(Context context) {
		if (SELF == null) {
			SELF = Fragment.instantiate(context, AccountRegisterPhoneFragment.class.getName());
		}

		return SELF;
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
		View rootView = inflater.inflate(R.layout.account_register_fragment, null);
		
		mGetVerifyCode = (CountDownButton) rootView.findViewById(R.id.GetVerifyCode);
		mGetVerifyCode.setOnClickListener(this);
		
		mClearPhoneText = (ImageView) rootView.findViewById(R.id.clear_phone_text);
		mClearPhoneText.setOnClickListener(this);
		
		mClearPwdText = (ImageView) rootView.findViewById(R.id.clear_pwd_text);
		mClearPwdText.setOnClickListener(this);
		
		mPhone = (EditText) rootView.findViewById(R.id.AccountPhone);
		mPhone.addTextChangedListener(this);
		
		mPwd = (EditText) rootView.findViewById(R.id.AccountPwd);
		mPwd.addTextChangedListener(this);
		
		mVerifyCode = (EditText) rootView.findViewById(R.id.AccountCode);
		mVerifyCode.addTextChangedListener(this);
		
		mRegister = (Button) rootView.findViewById(R.id.PhoneRegister);
		mRegister.setOnClickListener(this);
		
		checkButtonsEnable();
		
		return rootView;
	}
	
	private void checkButtonsEnable(){
		
		boolean isToEnableGetVerifyCodeButton = mPhone.length() >= 11;
		
		boolean isToEnableRegisterButton = mPhone.length() >= 11 && mPwd.length()>0 && mVerifyCode.length() >= 6 ;
		
		mGetVerifyCode.setEnabled(isToEnableGetVerifyCodeButton);
		
		mRegister.setEnabled(isToEnableRegisterButton);
		
		if(mPhone.length() > 0 ){
			mClearPhoneText.setVisibility(View.VISIBLE);
		}else{
			mClearPhoneText.setVisibility(View.GONE);
		}
		
		if(mPwd.length() > 0 ){
			mClearPwdText.setVisibility(View.VISIBLE);
		}else{
			mClearPwdText.setVisibility(View.GONE);
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
		case R.id.clear_pwd_text:
			mPwd.setText("");
			break;
		case R.id.GetVerifyCode:
			
			getVerifyCode();
			
			break;
			
		case R.id.PhoneRegister:
			
			register();
			
			break;
		}
	}

	private void register() {
		// TODO Auto-generated method stub
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "user_add_byisdn", false){

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
						
						AppInfoContent.saveAccount(getActivity(), mPhone.getText().toString(), mPwd.getText().toString());
						
						WarnDialog dialog = WarnDialog.newInstance(getActivity());
						
						dialog.setTitle(R.string.register).setMessage(R.string.RegisterSuccessful).setPositiveBtn(R.string.confirm, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
								getActivity().setResult(Activity.RESULT_OK);
								getActivity().finish();
							}

						}).show(getFragmentManager(), "RegisterSuccessfully");
						
					}
						break;
					case 3:
						CustomToast.MakeText(getActivity(), getString(R.string.RegisterErrorPhoneExist)).show();
						break;
					case 5:
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
		String pwd = mPwd.getText().toString();
		
		
		String code = mVerifyCode.getText().toString();
		
		
		if(TextUtils.isEmpty(verifyCode) || !verifyCode.equals(code) ){
			
			CustomToast.MakeText(getActivity(), getString(R.string.RegisterErrorInputWrongCode)).show();
			
			return ;
		}
		
		try {
			
			JSONObject params = new JSONObject();
			params.put("isdn", phone);
			params.put("userpwd", CvsUtils.GetMD5(pwd));
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
						
						mGetVerifyCode.countDown(AccountRegisterPhoneFragment.this);
						
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
