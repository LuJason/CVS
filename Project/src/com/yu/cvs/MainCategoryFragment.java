package com.yu.cvs;

import com.yu.cvs.adapters.CategoryTopAdapter;
import com.yu.cvs.adapters.PopSearchGategorytAdapter;
import com.yu.cvs.classes.Commodity;
import com.yu.cvs.provider.CvsProviderConfig;
import com.yu.cvs.shop.CCommodityActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainCategoryFragment extends Fragment implements OnClickListener, OnItemClickListener {
	
	private ListView mCategoryTop;
	private CategoryTopAdapter mAdapter;
	
	private TextView mSearchCatetory;
	private PopupWindow mPop;
	
	private ImageButton mSearch;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Cursor c = getActivity().getContentResolver().query(CvsProviderConfig.CategoryTop.CONTENT_URI, null, null, null, null);
		mAdapter = new CategoryTopAdapter(getActivity(), c, true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_conference, null);
		
		mSearch = (ImageButton) v.findViewById(R.id.Search);
		mSearch.setOnClickListener(this);
		
		mSearchCatetory = (TextView) v.findViewById(R.id.SearchCatetory);
		mSearchCatetory.setOnClickListener(this);
		
		mCategoryTop = (ListView) v.findViewById(R.id.categoryTop);
		mCategoryTop.setAdapter(mAdapter);
		
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.Search:
			
			String action = getActivity().getString(R.string.ActionCommodityList);
			getActivity().startActivity(new Intent(action));
			
			break;
			
		case R.id.SearchCatetory:
			
			if(mPop == null){
				initPop();
			}
			System.out.println("弹出PopWindows！");
			mPop.showAsDropDown(mSearchCatetory);  
			
			
			
			break;
		
		}
	}

	private void initPop(){
		
		ListView contentView = (ListView) LayoutInflater.from(getActivity()).inflate(R.layout.pop_search_category_layout, null);  
		contentView.setAdapter(new PopSearchGategorytAdapter(getActivity()));
		contentView.setOnItemClickListener(this);
		contentView.setSelection(0);
		
		contentView.measure(0, 0);

		mPop = new PopupWindow(contentView, contentView.getMeasuredWidth() * 2,
				contentView.getMeasuredHeight()
						* contentView.getAdapter().getCount());
		mPop.setBackgroundDrawable(new BitmapDrawable());
		mPop.setOutsideTouchable(true);
		mPop.setFocusable(true);
				
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		
		int rid = R.string.search_commodity; 
		
		switch(position){
		case 0:
			rid = R.string.search_commodity;
			break;
		case 1:
			rid = R.string.search_shop;
			break;
		}
		
		mSearchCatetory.setText(getActivity().getString(rid));
		
		mPop.dismiss();
	}
	
}
