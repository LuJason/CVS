/**
 * 
 */
package com.yu.cvs.dialog;

import com.yu.cvs.AccountActivity;
import com.yu.cvs.R;
import com.yu.cvs.shop.CartFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

/**
 * @author jason
 * 
 */
public class CartWarnToLoginDialog extends Dialog implements android.view.View.OnClickListener {
	
	Button mConfirm, mCancel;
	Fragment mFrament;
	
	DialogInterface.OnClickListener _confirmListener;

	/**
	 * @param context
	 */
	public CartWarnToLoginDialog(Fragment fragment) {
		super(fragment.getActivity());
		// TODO Auto-generated constructor stub
		mFrament = fragment;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_cart_warn_login);

		mConfirm = (Button) findViewById(R.id.DialogConfirm);
		mConfirm.setOnClickListener(this);
		mCancel = (Button) findViewById(R.id.DialogCancel);
		mCancel.setOnClickListener(this);
		
	}
	
	public CartWarnToLoginDialog setConfirmListener(DialogInterface.OnClickListener listener){
		
		_confirmListener = listener; 
		
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.DialogConfirm:

			if(_confirmListener != null){
				_confirmListener.onClick(this, 0);
			}
			
			this.dismiss();
			
			break;

		case R.id.DialogCancel:
			
			this.dismiss();
			
			break;

		}
	}

}
