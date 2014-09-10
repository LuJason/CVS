package com.yu.cvs.shop;

import com.yu.cvs.R;
import com.yu.cvs.adapters.CommodityListAdapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class CCommodityListFragment extends Fragment {
	
	public static final String ARG_KEY = "CATEGORY";
	
	private TextView mContent;
	private ListView mCommodityList;
	
	private CommodityListAdapter mAdapter;
	
	public CCommodityListFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.commodity_list_fragment, container, false);
		
		mContent = (TextView) root.findViewById(R.id.content);
		
		mCommodityList = (ListView) root.findViewById(R.id.CommodityList);
		
		queryCommoditys(getArguments());
		
		return root;
	}
	
	public void queryCommoditys(Bundle arg){
		
		String string = arg.getString(ARG_KEY, "种类：默认");
		
		mContent.setText(string);
	}

}
