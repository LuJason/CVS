package com.yu.cvs.shop;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.R;
import com.yu.cvs.adapters.CommodityListAdapter;
import com.yu.cvs.classes.Commodity;
import com.yu.cvs.task.NetworkTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class CCommodityListFragment extends Fragment implements OnItemClickListener {
	
	public static final String ARG_KEY = "CATEGORY";
	
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
		
		mAdapter = new CommodityListAdapter(getActivity(), null);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.commodity_list_fragment, container, false);
		
		mCommodityList = (ListView) root.findViewById(R.id.CommodityList);
		mCommodityList.setAdapter(mAdapter);
		mCommodityList.setOnItemClickListener(this);
		
		queryCommoditys(getArguments());
		
		return root;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		// TODO Auto-generated method stub
		
		Commodity c = (Commodity) mAdapter.getItem(position);
		
		Intent iProductInfo = new Intent(CCommodityInfoActivity.INTENTACTION); 
		Bundle params = new Bundle();
		params.putParcelable(CCommodityInfoActivity.KEY_COMMODITY,c);
		iProductInfo.putExtras(params);
		
		startActivity(iProductInfo);
		
	}
	
	public void queryCommoditys(Bundle arg){
		
		JSONObject params = new JSONObject();
		
		try {
			
//			code	分类代码（全）
//			keywords	用户输入关键字，可以用空格分割，可以空
//			attribute	用户选择的属性格式：name1#value1|name2#value2|nameN#valueN 可以空
//			lon	用户经度
//			lat	用户纬度
//			distance	距离范围（米）
//			store	库存数量（指定此条件返回大于等于此数）
//			orderby	排序：0表示按照距离，1表示按照价格，2按销量，3按评价
//			sort	0降序，1升序
//			page	第几页
//			pagecnt	每页条数

			params.put("code", "");
			params.put("keywords", "");
			params.put("attribute", "");
			params.put("lon", "36.710509");
			params.put("lat", "117.082275");
			params.put("distance", String.valueOf(Integer.MAX_VALUE));
			params.put("store", "1");
			params.put("orderby", "1");
			params.put("sort", "0");
			params.put("page", "1");
			params.put("pagecnt", "10");
			params.put("merch_id", "");
			

			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "product_search", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject result = new JSONObject(str);
					
					int status = result.getInt("status");
					
					switch(status){
					
					case 0:
						
//						JSONObject pageinfo = result.getJSONObject("result").getJSONObject("pageinfo");

						JSONArray dataset = result.getJSONObject("result").getJSONArray("dataset");
						
						ArrayList<Commodity> cache = new ArrayList<Commodity>();
						
						for(int i=0,size=dataset.length(); i<size; i++){
						
							JSONObject item = dataset.getJSONObject(i);
							Commodity c = new Commodity();
							c.initialize(item);
							
							cache.add(c);
							
						}
						
						mAdapter.addItemSet(cache);
						
						cache.clear();
						cache = null;
						
						break;
					case 5:
						
						break;
					
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
		};
		
		task.execute(params);
		
	}

}
