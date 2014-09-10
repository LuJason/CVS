package com.yu.cvs.adapters;

import com.yu.cvs.R;
import com.yu.cvs.provider.CvsProviderConfig;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CategoryTopAdapter extends CursorAdapter {
	
	private LayoutInflater _inflater;

	public CategoryTopAdapter(Context context, Cursor c, boolean autoRequery) {
		super(context, c, true);
		// TODO Auto-generated constructor stub
		
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public void bindView(View view, Context arg1, Cursor c) {
		// TODO Auto-generated method stub
		TextView tv = (TextView) view;
		tv.setText(c.getString(c.getColumnIndex(CvsProviderConfig.CategoryTop.TOP)));
	}

	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		TextView tv = (TextView) _inflater.inflate(R.layout.category_item, null);
		return tv;
	}

}
