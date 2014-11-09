package com.yu.cvs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

public class AccountActivity extends BaseFragmentActivity {

	public static final String KeyFragmentMark = "FragmentMark";

	public static final int FragmentLogin = 0x001;
	public static final int FragmentRegisterEmail = 0x002;
	public static final int FragmentRegisterPhone = 0x003;
	public static final int FragmentAddress = 0x004;
	public static final int FragmentModifyPwd = 0x005;
	public static final int FragmentBindPhone = 0x006;

	private FragmentManager fm;
	
	private AccBaseFragment mCurrentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.account_activity);
		
		fm = getSupportFragmentManager();

		Bundle bundle = getIntent().getExtras();
		int mark = bundle.getInt(KeyFragmentMark);

		switch (mark) {
		case FragmentRegisterEmail:
			break;
		case FragmentRegisterPhone:
			break;
		case FragmentLogin:
			showLoginFragment();
			break;
		case FragmentAddress:
			break;
		case FragmentModifyPwd:
			showModifyFragment();
			break;
		case FragmentBindPhone:
			showBindPhoneFragment();
			break;
		}

	}

	@Override
	public boolean onSupportNavigateUp() {
		// TODO Auto-generated method stub
		
//		android.app.FragmentManager a = getFragmentManager();
//		a.popBackStack();
//		a.putFragment(bundle, key, fragment);
//		
//		android.app.FragmentTransaction b = a.beginTransaction();
		
		
//		return super.onSupportNavigateUp();
		
		return true;
		
	}
	
	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		
		mCurrentFragment.doHomeAsUp();
		
	}
	
	public void showRegisterFragment() {
		// TODO Auto-generated method stub

		mCurrentFragment = (AccBaseFragment) AccountRegisterFragment
				.Instance(this);
		mCurrentFragment.setArguments(new Bundle());
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container, mCurrentFragment, "Register")
				.commitAllowingStateLoss();

	}

	public void showResetPwdFragment() {
		// TODO Auto-generated method stub

		mCurrentFragment = (AccBaseFragment) AccountResetPwdFragment
				.Instance(this);
		mCurrentFragment.setArguments(new Bundle());
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container, mCurrentFragment, "ResetPwd")
				.commitAllowingStateLoss();

	}

	public void showLoginFragment() {
		// TODO Auto-generated method stub

		mCurrentFragment = (AccBaseFragment) AccountLoginFragment
				.Instance(this);
		mCurrentFragment.setArguments(new Bundle());
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, mCurrentFragment, "Login")
				.commitAllowingStateLoss();

	}
	
	public void showModifyFragment(){
		
		mCurrentFragment = (AccBaseFragment) AccountModifyPwdFragment
				.Instance(this);
		mCurrentFragment.setArguments(new Bundle());
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, mCurrentFragment, "Modify")
				.commitAllowingStateLoss();
		
	}
	

	private void showBindPhoneFragment() {
		// TODO Auto-generated method stub
		mCurrentFragment = (AccBaseFragment) AccountBindPhoneFragment
				.Instance(this);
		mCurrentFragment.setArguments(new Bundle());
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, mCurrentFragment, "BindPhone")
				.commitAllowingStateLoss();
	}
}
