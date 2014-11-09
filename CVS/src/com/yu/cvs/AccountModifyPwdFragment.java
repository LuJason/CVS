package com.yu.cvs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.classes.CAccount;
import com.yu.cvs.classes.CustomToast;
import com.yu.cvs.dialog.WarnDialog;
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
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AccountModifyPwdFragment extends AccBaseFragment implements TextWatcher, OnClickListener {
	
	private EditText mOld, mNew, mConfirm;
	
	private Button mModify;
	
	private ImageView mClearOld, mClearNew, mClearConfirm;
	
	private AccountActivity mParent;
	
	public static Fragment SELF;
	
	public static Fragment Instance(Context context) {
		if (SELF == null) {
			SELF = Fragment.instantiate(context, AccountModifyPwdFragment.class.getName());
		}

		return SELF;
	}

	@Override
	protected void doHomeAsUp() {
		// TODO Auto-generated method stub
		super.doHomeAsUp();
		mParent.finish();
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mParent = (AccountActivity) activity;
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
		View rootView = inflater.inflate(R.layout.account_modifypwd_fragment, null);
		
		mOld = (EditText) rootView.findViewById(R.id.ModifyOldpwd) ;
		mOld.addTextChangedListener(this);
		mNew = (EditText) rootView.findViewById(R.id.ModifyNewpwd);
		mNew.addTextChangedListener(this);
		mConfirm = (EditText) rootView.findViewById(R.id.ModifyConfirmpwd);
		mConfirm.addTextChangedListener(this);
		
		mClearOld = (ImageView) rootView.findViewById(R.id.clear_old_text);
		mClearOld.setOnClickListener(this);
		mClearNew = (ImageView) rootView.findViewById(R.id.clear_new_text);
		mClearNew.setOnClickListener(this);
		mClearConfirm = (ImageView) rootView.findViewById(R.id.clear_confirm_text);
		mClearConfirm.setOnClickListener(this);
		
		mModify = (Button) rootView.findViewById(R.id.ModifyPwd);
		mModify.setOnClickListener(this);
		
		checkButtonsEnable();
		
		return rootView;
	}
	
	private void checkButtonsEnable(){
		
		boolean isToEnableModifyPwdButton = mOld.length() >= 6 && mNew.length()>=6 && mConfirm.length() >= 6 ;
		mModify.setEnabled(isToEnableModifyPwdButton);
		
		
		if(mOld.length() > 0 ){
			mClearOld.setVisibility(View.VISIBLE);
		}else{
			mClearOld.setVisibility(View.GONE);
		}
		
		if(mNew.length() > 0 ){
			mClearNew.setVisibility(View.VISIBLE);
		}else{
			mClearNew.setVisibility(View.GONE);
		}
		
		if(mConfirm.length() > 0 ){
			mClearConfirm.setVisibility(View.VISIBLE);
		}else{
			mClearConfirm.setVisibility(View.GONE);
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
		case R.id.clear_old_text:
			mOld.setText("");
			break;
		case R.id.clear_new_text:
			mNew.setText("");
			break;
		case R.id.clear_confirm_text:
			mConfirm.setText("");
			break;
		case R.id.ModifyPwd:
			
			String pwd1 = mNew.getText().toString();
			String pwd2 = mConfirm.getText().toString();
			
			if(pwd1.equals(pwd2)){
				modifyPwd();
			}else{
				CustomToast.MakeText(getActivity(), "两次输入的密码不一致").show();
			}
			
			break;
		}
	}

	private void modifyPwd() {
		// TODO Auto-generated method stub
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "user_update_pwd", false){

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
						
						WarnDialog dialog = WarnDialog.newInstance(getActivity());
						dialog.setTitle(R.string.AccountChangePwd).setMessage(R.string.ModifyPwdSuccessful).setPositiveBtn(R.string.confirm, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								mParent.showLoginFragment();
							}

						}).show(getFragmentManager(), "RegisterSuccessfully");
						
						mParent.getAccount().clear();
						
						AppInfoContent.saveAccount(getActivity(), "", "");
						
					}
						break;
					case 4:
						CustomToast.MakeText(getActivity(), getString(R.string.ModifyErrorPhoneNotExist)).show();
						break;
					case 5:
						CustomToast.MakeText(getActivity(), getString(R.string.ModifyErrorOldWrong)).show();
						break;
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		String old = mOld.getText().toString();
		String newpwd = mNew.getText().toString();
		
		String userid = mParent.getAccount().getUserid();

		try {
			
			JSONObject params = new JSONObject();
			params.put("userid", userid);
			params.put("pwd_old", CvsUtils.GetMD5(old));
			params.put("pwd_new", CvsUtils.GetMD5(newpwd));
			
			task.execute(params );
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
