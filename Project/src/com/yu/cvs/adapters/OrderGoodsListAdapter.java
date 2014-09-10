package com.yu.cvs.adapters;


import java.util.ArrayList;
import com.yu.cvs.R;
import com.yu.cvs.classes.Good;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderGoodsListAdapter extends BaseAdapter {
	
	private static String TAG ;
	
	private LayoutInflater _inflater;
	private Context _context;
	private Context _fragment;
	
	private ArrayList<Good> _goods;
	
	public OrderGoodsListAdapter(Context context, ArrayList<Good> goods){
		TAG = this.getClass().getName();
		_context = context;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_goods = goods;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _goods.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _goods.get(position);
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
			
			v = _inflater.inflate(R.layout.order_goods_list_item, null);
			holder = new ViewHolder();

			holder._goodImg = (ImageView) v.findViewById(R.id.GoodImg);
			holder._goodName = (TextView) v.findViewById(R.id.GoodName);
			holder._goodAddress = (TextView) v.findViewById(R.id.GoodAddress);
			holder._goodSpecification = (TextView) v.findViewById(R.id.GoodSpecification);
			holder._goodTotalPirce = (TextView) v.findViewById(R.id.GoodTotalPirce);
			holder._goodQuantity = (TextView) v.findViewById(R.id.GoodQuantity);
			
			v.setTag(holder);
			
		}else{
			v = convertView;
			holder = (ViewHolder) v.getTag();
		}
		
		Good bean = _goods.get(position);

		holder._goodName.setText(bean.getProductName());
		holder._goodQuantity.setTag(position);
		holder._goodTotalPirce.setText(String.format("%.2f元",(float)bean.getPrice()/100));
		holder._goodQuantity.setText(String.format("%d", bean.getCount()));
		
		return v;
		
	}

	private class ViewHolder{
		public ImageView _goodImg;
		public TextView _goodAddress;
		public TextView _goodName;
		public TextView _goodQuantity;
		public TextView _goodSpecification;
		public TextView _goodTotalPirce;
	}

}
