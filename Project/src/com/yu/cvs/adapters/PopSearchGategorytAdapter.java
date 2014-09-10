package com.yu.cvs.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PopSearchGategorytAdapter extends BaseAdapter {

	// 必填，Tee台；1、黑Tee；2、金Tee；3、蓝Tee；4、白Tee；5、红Tee

	private String[] Tees = new String[] { 
			"商品", "店铺" 
	};

	private Context mContext;

	public PopSearchGategorytAdapter(Context mContext) {
		super();
		this.mContext = mContext;

//		DisplayMetrics m = mContext.getResources().getDisplayMetrics();
//		int widthPixels = m.widthPixels;
//		width = (widthPixels) / 3 - 2 * 2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Tees.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Tees[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv = new TextView(mContext); 
		tv.setText((CharSequence) getItem(position));
		tv.setTextSize(14);
		tv.setPadding(10, 10, 10, 10);
		return tv;
	}

}
