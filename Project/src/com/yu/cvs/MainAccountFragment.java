package com.yu.cvs;


import com.yu.cvs.classes.CAccount;
import com.yu.cvs.settings.CAddressActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MainAccountFragment extends Fragment implements OnClickListener {

	private final int LoginRequestCode = 0x001;
	
	private BaseFragmentActivity baseActivity;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		baseActivity = (BaseFragmentActivity) activity;
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_account, null);
		
		v.findViewById(R.id.AccountAddress).setOnClickListener(this);

		return v;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		CAccount acc = ((BaseFragmentActivity) getActivity()).getAccount();
		if (!acc.isLogin()) {
			Intent accIntent = new Intent(getActivity().getApplication(),
					AccountActivity.class);

			Bundle bundle = new Bundle();
			bundle.putInt(AccountActivity.KeyFragmentMark,	AccountActivity.FragmentLogin);
			accIntent.putExtras(bundle);

			startActivityForResult(accIntent, LoginRequestCode);

		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		System.out.println(String.format("requestcode:%d, reusultCode:%d", 	requestCode, resultCode));
		switch (requestCode) {

		case LoginRequestCode:
			if (resultCode == Activity.RESULT_OK) {
				
				
				
				
				
			} else {
				((MainActivity)getActivity()).selectLastTab();

			}
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.AccountAddress:
			Intent addressIntent = new Intent(getActivity(), CAddressActivity.class);
			startActivity(addressIntent);
			break;
		}
		
		
	}

}
