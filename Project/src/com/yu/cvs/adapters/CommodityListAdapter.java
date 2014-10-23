package com.yu.cvs.adapters;

import java.util.ArrayList;

import com.yu.cvs.R;
import com.yu.cvs.classes.Commodity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommodityListAdapter extends BaseAdapter {
	
	private ArrayList<Commodity> _commodities;
	private LayoutInflater _inflater;
	private Context _context;
	
	public CommodityListAdapter(Context context, ArrayList<Commodity> commodities){
		
		_context = context;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_commodities = new ArrayList<Commodity>();
		if(commodities != null){
			_commodities.addAll(commodities);
		}
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _commodities.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _commodities.get(position);
	}
	
	public void addItemSet(ArrayList<Commodity> cache) {
		// TODO Auto-generated method stub
		_commodities.addAll(_commodities.size(), cache);
		notifyDataSetChanged();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view ;
		if(convertView == null){
			view = _inflater.inflate(R.layout.commodity_list_item, null);
			ViewHolder holder = new ViewHolder();
			
			holder._img = (ImageView) view.findViewById(R.id.CommodityImg);
			holder._desc = (TextView) view.findViewById(R.id.CommodityDesc);
			holder._price = (TextView) view.findViewById(R.id.CommodityPrice);
			holder._shop = (TextView) view.findViewById(R.id.shop);
			holder._distance = (TextView) view.findViewById(R.id.distance);
			
			view.setTag(holder);
		}else{
			view = convertView;
			
		}
		
		ViewHolder holder = (ViewHolder) view.getTag();
		
		Commodity c = _commodities.get(position);
		
		holder._desc.setText(c.getName());
		holder._price.setText(String.format("%.2f ￥", (float)c.getPrice()/100));
		holder._shop.setText(c.getMerchName());
		holder._distance.setText(String.format("%s m", c.getDistance()));
		
		return view;
	}
	
	public static class ViewHolder{
		
		public ImageView _img;
		public TextView _desc;
		public TextView _price;
		public TextView _distance;
		public TextView _shop;
		
	}

}
