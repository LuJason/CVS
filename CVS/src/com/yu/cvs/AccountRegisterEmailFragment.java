package com.yu.cvs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class AccountRegisterEmailFragment extends Fragment implements OnClickListener, OnCheckedChangeListener, TextWatcher {
	
	private static Fragment SELF = null;

	public static Fragment Instance(){
		if(SELF == null){
			SELF =  new AccountRegisterEmailFragment();
		}
		
		return SELF;
	}
	
	
	private Button mRegister;
	
	private EditText mRegisterEmail, mRegisterPwd;
	
	private CheckBox mPwdTog, mAgreementTog;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.account_register_email_ragment, container, false);
		
		mRegister = (Button) root.findViewById(R.id.Register);
		mRegister.setOnClickListener(this);
		
		mRegisterEmail = (EditText) root.findViewById(R.id.RegisterEmail);
		mRegisterEmail.addTextChangedListener(this);
		mRegisterPwd = (EditText) root.findViewById(R.id.RegisterPwd);
		mRegisterPwd.addTextChangedListener(this);
		
		mPwdTog = (CheckBox) root.findViewById(R.id.TogPwd);
		mPwdTog.setOnCheckedChangeListener(this);
		mAgreementTog = (CheckBox) root.findViewById(R.id.TogAgreement);
		mAgreementTog.setOnCheckedChangeListener(this);
		
		checkButtonEnable();
		
		return root;
	}
	
	private void checkButtonEnable(){
		boolean enable = mRegisterEmail.length() > 0 && mRegisterEmail.length() > 0 ;
		mRegister.setEnabled(enable);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId()){
		case R.id.TogPwd:
			if(isChecked){
				mRegisterPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
			}else{
				mRegisterPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}
			
			break;
		case R.id.TogAgreement:
			
			
			break;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		checkButtonEnable();
	}
	

}
