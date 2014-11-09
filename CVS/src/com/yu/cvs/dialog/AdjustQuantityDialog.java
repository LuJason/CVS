/**
 * 
 */
package com.yu.cvs.dialog;

import com.yu.cvs.R;
import com.yu.cvs.myview.AddAndSubView;
import com.yu.cvs.shop.CartFragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

/**
 * @author jason
 * 
 */
public class AdjustQuantityDialog extends Dialog implements	android.view.View.OnClickListener {

	AddAndSubView mQuantity;
	Button mConfirm, mCancel;
	
	CartFragment mFragment;
	
	String _pid;
	int _quantity;

	/**
	 * @param context
	 */
	public AdjustQuantityDialog(CartFragment fragment, String pid , int quantity) {
		super(fragment.getActivity());
		// TODO Auto-generated constructor stub
		mFragment = fragment;
		_pid = pid;
		_quantity = quantity;
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_adjust_quantity);

		mQuantity = (AddAndSubView) findViewById(R.id.AdjustQuantity);
		mQuantity.setOnNumChangeListener(new AddAndSubView.OnNumChangeListener() {
			
			@Override
			public void onNumChange(View view, int num) {
				// TODO Auto-generated method stub
				_quantity = num;
			}
		});
		
		mQuantity.setNum(_quantity);
		
		mConfirm = (Button) findViewById(R.id.DialogConfirm);
		mConfirm.setOnClickListener(this);
		mCancel = (Button) findViewById(R.id.DialogCancel);
		mCancel.setOnClickListener(this);
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
			mFragment.adjustGoodQuantity(_pid, _quantity);
			this.dismiss();
			break;

		case R.id.DialogCancel:
			
			this.dismiss();
			
			break;

		}
	}

}
