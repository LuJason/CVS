package com.yu.cvs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccountRegisterFragment extends AccBaseFragment {
	
	public static Fragment SELF;

	private FragmentTabHost mTabHost;
	
	private AccountActivity mParentActivty;
	
	public static Fragment Instance(Context context) {
		if (SELF == null) {
			SELF = Fragment.instantiate(context, AccountRegisterFragment.class.getName());
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
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	

	@Override
	protected void doHomeAsUp() {
		// TODO Auto-generated method stub
		super.doHomeAsUp();
		mParentActivty.showLoginFragment();
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		View root = inflater.inflate(R.layout.account_register_container, container, false);
//		mTabHost = (FragmentTabHost) root.findViewById(R.id.register_tabhost);
//		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.register_container);
		
		setActionBarTitle(R.string.register);
		
		View view = getActivity().getLayoutInflater().inflate(R.layout.main_tab, null);
		TextView label = (TextView) view.findViewById(R.id.tab_label);
		label.setText(R.string.RegisterByPhone);
		
		mTabHost = new FragmentTabHost(getActivity());
		mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.container);

		mTabHost.addTab(
				mTabHost.newTabSpec("Email").setIndicator(view),
				AccountRegisterPhoneFragment.class, null);

		
		view = getActivity().getLayoutInflater().inflate(R.layout.main_tab, null);
		label = (TextView) view.findViewById(R.id.tab_label);
		label.setText(R.string.RegisterByEmail);
		mTabHost.addTab(
				mTabHost.newTabSpec("Phone").setIndicator(view),
				AccountRegisterEmailFragment.class, null);

		mTabHost.getTabWidget().setDividerDrawable(null);
		
		return mTabHost;
	}

}
