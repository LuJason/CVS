package com.yu.cvs.adapters;


import com.yu.cvs.R;
import com.yu.cvs.classes.BaseCart;
import com.yu.cvs.classes.Good;
import com.yu.cvs.dialog.AdjustQuantityDialog;
import com.yu.cvs.shop.CartFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CartGoodsListAdapter extends BaseAdapter implements OnClickListener, OnCheckedChangeListener {
	
	private static String TAG ;
	
	private BaseCart _cart;
	private LayoutInflater _inflater;
	private Context _context;
	private CartFragment _fragment;
	
	public CartGoodsListAdapter(CartFragment fragment, BaseCart cart){
		TAG = this.getClass().getName();
		_fragment = fragment;
		_inflater = (LayoutInflater) fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_cart = cart;
		_cart.registerAttachedAdapter(this);
		
	}
	
	public void setNewCart(BaseCart cart){
		
		Log.d(TAG, "register CartGoodsListAdapter self to new Cart");
		
		_cart.unregisterAttachedAdapter(this);
		_cart = cart;
		_cart.registerAttachedAdapter(this);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _cart.getGoods().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _cart.getGoods().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v;
		ViewHolder holder;
		if(convertView == null){
			
			v = _inflater.inflate(R.layout.cart_goods_list_item, null);
			holder = new ViewHolder();

			holder._selected = (CheckBox) v.findViewById(R.id.GoodSelected);
			holder._selected.setOnCheckedChangeListener(this);
			holder._goodImg = (ImageView) v.findViewById(R.id.GoodImg);
			holder._goodTitle = (TextView) v.findViewById(R.id.GoodTitle);
			holder._goodUnitPirce = (TextView) v.findViewById(R.id.GoodUnitPirce);
			holder._goodQuantity = (TextView) v.findViewById(R.id.GoodQuantity);
			holder._goodQuantity.setOnClickListener(this);
			holder._delete = (ImageView) v.findViewById(R.id.GoodDelete);
			holder._delete.setOnClickListener(this);
			
			v.setTag(holder);
			
		}else{
			v = convertView;
			holder = (ViewHolder) v.getTag();
		}
		
		Good bean = _cart.getGoods().get(position);
		String pid = bean.getProductId();

		holder._delete.setTag(pid);
		holder._selected.setTag(pid);
//		holder._goodQuantity.setTag(pid);
		holder._goodQuantity.setTag(position);
		holder._selected.setChecked(bean.isSelected());
		
//		holder._goodImg 
		
		
		holder._goodTitle.setText(bean.getProductName());
		holder._goodUnitPirce.setText(String.format("%.2f元",(float)bean.getPrice()/100));
		holder._goodQuantity.setText(String.format("%d", bean.getCount()));
		
		
		return v;
	}
	

	private class ViewHolder{
		public CheckBox _selected;
		public ImageView _goodImg;
		public TextView _goodTitle;
		public TextView _goodQuantity;
		public TextView _goodUnitPirce;
		public ImageView _delete;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.GoodDelete:
			
			String pid = (String) v.getTag();
			_cart.removeGoodFromCart(_fragment,pid);
			
			
			
			break;
		case R.id.GoodQuantity:
			
			Good bean = _cart.getGoods().get((Integer) v.getTag());
			
			AdjustQuantityDialog dialog = new AdjustQuantityDialog(_fragment,bean.getProductId(), bean.getCount() );
			
			dialog.setTitle("购买数量");
			
			dialog.show();
			
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		String pid = (String) buttonView.getTag();
		_cart.setSelectProduct(pid, isChecked);
		notifyDataSetChanged();
	}
	
}
