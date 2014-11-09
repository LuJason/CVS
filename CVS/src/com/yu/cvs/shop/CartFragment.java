/**
 * 
 */
package com.yu.cvs.shop;


import com.yu.cvs.AccountActivity;
import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.R;
import com.yu.cvs.adapters.CartGoodsListAdapter;
import com.yu.cvs.classes.BaseCart;
import com.yu.cvs.classes.BaseCart.TotalPriceCalculateListener;
import com.yu.cvs.dialog.CartWarnToLoginDialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author jason
 * 
 * 用户已经登录
 *
 */
public class CartFragment extends Fragment  implements TotalPriceCalculateListener, OnClickListener {
	
	private final int LoginRequestCode = 0x001;
	
	private ListView mGoodsList;
	private CartGoodsListAdapter mAdapter;
	
	private TextView mTotalAmountDue, mCountOfGoods;
	
	private Button mGoToSettleAccount;
	
	private BaseCart mCart;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mCart = ((BaseFragmentActivity)getActivity()).getAccount().getCart();
		mCart.setOnTotalPriceCalculateListener(this);
		mAdapter = new CartGoodsListAdapter(this, mCart);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,	@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.cart_fragment, null);
		
		mTotalAmountDue = (TextView) root.findViewById(R.id.TotalAmountDue);
		mCountOfGoods = (TextView) root.findViewById(R.id.CountOfGoods);
		mGoToSettleAccount = (Button) root.findViewById(R.id.GoToSettleAccount);
		mGoToSettleAccount.setOnClickListener(this);
		
		mGoodsList = (ListView) root.findViewById(R.id.GoodsList);
		mGoodsList.setAdapter(mAdapter);
		
		mCart.notifyGoodsListChanged();
		
		return root;
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.Cart.TotalPriceCalculateListener#afterCalculate(long)
	 */
	@Override
	public void afterCalculate(long totalPrice) {
		// TODO Auto-generated method stub
		mTotalAmountDue.setText(String.format("%.2f", (float)totalPrice/100));
	}

	/* (non-Javadoc)
	 * @see com.yu.cvs.classes.Cart.TotalPriceCalculateListener#afterAdjustCountOfGoods(int)
	 */
	@Override
	public void afterAdjustCountOfGoods(int num) {
		// TODO Auto-generated method stub
		mCountOfGoods.setText(String.format("%d",num));
		
		if(num > 0){
			mGoToSettleAccount.setEnabled(true);
		}else{
			mGoToSettleAccount.setEnabled(false);
		}
		
		
	}

	/**
	 * @param _pid
	 * @param _quantity
	 */
	public void adjustGoodQuantity(String pid, int quantity) {
		// TODO Auto-generated method stub
		mCart.adjustGoodQuantity(this, pid, quantity);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.GoToSettleAccount:
			// 结算时用户必须登录，如果未登录，则提示登录对话框			
			try {
				
//				mCart.countOfSelectedGoods();
				
				mCart.generateOrder();
				
				startActivity(new Intent(this.getActivity(), SettleAccountActivity.class));
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				

				
				CartWarnToLoginDialog dialog = new CartWarnToLoginDialog(this);
				dialog.setTitle("温馨提示");
				dialog.setConfirmListener(new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent accIntent = new Intent(getActivity(), AccountActivity.class);

						Bundle bundle = new Bundle();
						bundle.putInt(AccountActivity.KeyFragmentMark,	AccountActivity.FragmentLogin);
						accIntent.putExtras(bundle);

						startActivityForResult(accIntent, LoginRequestCode);
						
					}
				});
				dialog.show();
				
			}
			
			break;
		}
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

		case LoginRequestCode:
			if (resultCode == Activity.RESULT_OK) {
				
				mCart = ((BaseFragmentActivity)getActivity()).getAccount().getCart();
				
				mAdapter.setNewCart(mCart);
				
				mCart.notifyGoodsListChanged();
				
			} else {

			}
			break;
		}

	}

}
