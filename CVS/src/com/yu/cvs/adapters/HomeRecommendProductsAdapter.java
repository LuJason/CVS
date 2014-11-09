package com.yu.cvs.adapters;

import java.util.ArrayList;

import net.tsz.afinal.FinalBitmap;

import com.yu.cvs.R;
import com.yu.cvs.classes.Good;
import com.yu.cvs.classes.RecommendProduct;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeRecommendProductsAdapter extends BaseAdapter {
	
	private static String TAG ;
	
	private ArrayList<RecommendProduct> _goods;
	private LayoutInflater _inflater;
	private Context _context;
	
	private int _imageWidth, _imageHeight;
	
	private boolean _notCaculateImageDemoin;
	
	private FinalBitmap _fb; 
	
	public HomeRecommendProductsAdapter(Context context, ArrayList<RecommendProduct> goods) {
		// TODO Auto-generated constructor stub
		TAG = this.getClass().getName();
		_context = context;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (goods == null) {
			_goods = new ArrayList<RecommendProduct>();
		} else {
			_goods = goods;
		}
		_notCaculateImageDemoin = true;
		
		_fb = FinalBitmap.create(_context);
		
		
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
	
	public void addNewProducts(ArrayList<RecommendProduct> newProducts){
		if(newProducts != null){
			_goods.clear();
			_goods.addAll(newProducts);
			notifyDataSetChanged();
		}
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v;
		ViewHolder holder;
		
		if(convertView == null){
			
			v = _inflater.inflate(R.layout.home_recommend_good_time, null);
			
			holder = new ViewHolder();
			holder._goodImg = (ImageView) v.findViewById(R.id.picture);
			holder._goodTitle = (TextView) v.findViewById(R.id.title);
			holder._goodPrice = (TextView) v.findViewById(R.id.price);
			holder._goodShop = (TextView) v.findViewById(R.id.shop);
			holder._goodCart = (ImageView) v.findViewById(R.id.cart);
		
			
		}else{
			v = convertView;
			holder = (ViewHolder) v.getTag();
		}
		
		if(_notCaculateImageDemoin){
			caculateImageDemoin(v);
		}
		
		
		RecommendProduct g = _goods.get(position);
		
		if(g.getProductLogos().size() > 0){
			_fb.display(holder._goodImg, g.getProductLogos().get(0), _imageWidth, _imageHeight);
		}
		
		holder._goodTitle.setText(g.getPname());
		holder._goodPrice.setText(String.format("￥ %.2f",(float) g.getPrice()/100));
		holder._goodShop.setText(g.getMerchId());
		
		
		return v;
	}

	
	private void caculateImageDemoin(View v) {
		// TODO Auto-generated method stub
		int screenWidth = _context.getResources().getDisplayMetrics().widthPixels;
		
		_imageWidth = (int) (0.3 * screenWidth - v.getPaddingLeft());
		_imageHeight = _imageWidth;
		
		_notCaculateImageDemoin = false;
	}
	
	private class ViewHolder{
		public ImageView _goodImg;
		public TextView _goodTitle;
		public TextView _goodPrice;
		public TextView _goodShop;
		public ImageView _goodCart;
	}
	
}
