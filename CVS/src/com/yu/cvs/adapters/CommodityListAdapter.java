package com.yu.cvs.adapters;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.yu.cvs.R;
import com.yu.cvs.classes.Commodity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class CommodityListAdapter extends BaseAdapter {
	
	private static final String TAG = null;
	private ArrayList<Commodity> _commodities;
	private LayoutInflater _inflater;
	private Context _context;
	
	private FinalBitmap _fb;
	
	private int _imageWidth, _imageHeight;
	
	private boolean _notCaculateImageDemoin;
	
	public CommodityListAdapter(Context context, ArrayList<Commodity> commodities){
		
		_context = context;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_commodities = new ArrayList<Commodity>();
		if(commodities != null){
			_commodities.addAll(commodities);
		}
		
		_fb = FinalBitmap.create(_context);
		_notCaculateImageDemoin = true;
		
	}
	
	private void caculateImageDemoin(View v) {
		// TODO Auto-generated method stub
		int screenWidth = _context.getResources().getDisplayMetrics().widthPixels;
		
		_imageWidth = (int) (0.3 * screenWidth - v.getPaddingLeft());
		_imageHeight = _imageWidth;
		
		_notCaculateImageDemoin = false;
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
			
			if(_notCaculateImageDemoin){
				caculateImageDemoin(view);
			}
			
			holder._img = (ImageView) view.findViewById(R.id.CommodityImg);
			LayoutParams params = holder._img.getLayoutParams();
			params.width = _imageWidth;
			params.height = _imageHeight;
			holder._img.setLayoutParams(params);
			holder._img.setScaleType(ScaleType.FIT_XY);
			
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
		
		
		
//		Log.d(TAG, String.format("image w : %d, h : %d", _imageWidth, _imageHeight));
		
		if(c.getProductLogos().size() > 0){
			_fb.display(holder._img, c.getProductLogos().get(0));
		}
		
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
