package com.yu.cvs;

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

public class AccountRegisterPhoneFragment extends Fragment implements TextWatcher, OnClickListener {
	
	private EditText mPhone;
	
	private ImageView mClearText;
	
	private Button mGetVerifyCode;

	public static Fragment SELF;

	public static Fragment Instance() {
		if (SELF == null) {
			SELF = new AccountRegisterPhoneFragment();
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
		
		mGetVerifyCode = (Button) rootView.findViewById(R.id.GetVerifyCode);
		mClearText = (ImageView) rootView.findViewById(R.id.clear_text);
		mClearText.setOnClickListener(this);
		
		mPhone = (EditText) rootView.findViewById(R.id.AccoutPhone);
		mPhone.addTextChangedListener(this);
		
		checkButtonsEnable();
		
		return rootView;
	}
	
	private void checkButtonsEnable(){
		
		mGetVerifyCode.setEnabled(mPhone.length() >= 11);
		
		if(mPhone.length() > 0){
			mClearText.setVisibility(View.VISIBLE);
		}else{
			mClearText.setVisibility(View.GONE);
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
		case R.id.clear_text:
			mPhone.setText("");
			break;
		}
	}

}
