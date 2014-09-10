package com.yu.cvs;

import android.support.v4.app.Fragment;

public class AccountAddressFragment extends Fragment {

	public static Fragment SELF;

	public static Fragment Instance() {
		if (SELF == null) {
			SELF = new AccountAddressFragment();
		}

		return SELF;
	}

}
