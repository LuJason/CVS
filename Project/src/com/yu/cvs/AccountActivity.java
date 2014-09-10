package com.yu.cvs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class AccountActivity extends BaseFragmentActivity implements
		AccountLoginFragment.LoginFramentCallBack {

	public static final String KeyFragmentMark = "FragmentMark";

	public static final int FragmentLogin = 0x001;
	public static final int FragmentRegisterEmail = 0x002;
	public static final int FragmentRegisterPhone = 0x003;
	public static final int FragmentAddress = 0x004;

	public static final String TagOfLoginFrament = "LOGIN";
	public static final String TagOfRegisterFrament = "REGISTER";

	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.account_activity);

		fm = getSupportFragmentManager();

		Bundle bundle = getIntent().getExtras();
		int mark = bundle.getInt(KeyFragmentMark);

		FragmentTransaction ft = fm.beginTransaction();

		switch (mark) {
		case FragmentRegisterEmail:
			break;
		case FragmentRegisterPhone:
			break;
		case FragmentLogin:

			Fragment loginFragment = fm.findFragmentByTag(TagOfLoginFrament);

			if (loginFragment == null) {
				// loginFragment =
				// AccountLoginFragment.instantiate(getApplication(),
				// TagOfLoginFrament, bundle);
				loginFragment = AccountLoginFragment.Instance();
				loginFragment.setArguments(bundle);
				ft.replace(R.id.container, loginFragment, TagOfLoginFrament);
			}

			ft.show(loginFragment);
			ft.commitAllowingStateLoss();

			break;
		case FragmentAddress:
			break;
		}

	}

	@Override
	public void showRegisterFragment() {
		// TODO Auto-generated method stub

		Fragment RegisterFragment = fm.findFragmentByTag(TagOfRegisterFrament);

		if (RegisterFragment == null) {
			// loginFragment =
			// AccountLoginFragment.instantiate(getApplication(),
			// TagOfLoginFrament, bundle);
			RegisterFragment = AccountRegisterFragment.Instance();
			RegisterFragment.setArguments(new Bundle());
			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.container, RegisterFragment,
							TagOfRegisterFrament).commit();
		}


	}
}
