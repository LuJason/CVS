package com.yu.cvs;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.adapters.AdvertisementAdapter;
import com.yu.cvs.adapters.HomeRecommendProductsAdapter;
import com.yu.cvs.classes.Banner;
import com.yu.cvs.classes.RecommendProduct;
import com.yu.cvs.myview.SimpleHorizontalListView;
import com.yu.cvs.task.NetworkTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainHomeFragment extends Fragment implements OnClickListener {

	protected static final String TAG = MainHomeFragment.class.getSimpleName();
	
	private ScrollView mScroller;
	
	private ViewPager mViewPager;
	private LinearLayout mDotContainer;
	private ArrayList<ImageView> mDots;
	private AdvertisementAdapter mBannerAdapter;
	private ArrayList<Banner> _banners;
//	private ArrayList<CAdver> _advers;
	
	private TextView mCategoryCvs, mCategoryMultipleShop, mCategoryMarket, mCategoryCommunityShop;
	
	private ListView mRecommendProducts;
	private HomeRecommendProductsAdapter mRecommendAdapter;
	
	private SimpleHorizontalListView mCacheGoods;

	private Handler mHandler;
	private int mViewPagerCurrentPosition;
	
	private ArrayList<NetworkTask> mTaskList;
	
//	private View rootView;//缓存Fragment view  

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mTaskList = new ArrayList<NetworkTask>();
		_banners = new ArrayList<Banner>();
		mBannerAdapter = new AdvertisementAdapter(getActivity(), _banners);
		
	}
	
	

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		
		for (NetworkTask t : mTaskList) {
			t.cancel(true);
		}
		
		super.onDestroyView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
//		if(rootView == null){
//			
//			rootView = inflater.inflate(R.layout.fragment_home, null);
//			
//		}else{
//			
//			ViewGroup parent = (ViewGroup) rootView.getParent();  
//	        
//			if (parent != null) {  
//	            parent.removeView(rootView);  
//	        }   
//	        
//	        return rootView; 
//			
//		}
		
		View rootView = inflater.inflate(R.layout.fragment_home, null);

		mScroller = (ScrollView) rootView.findViewById(R.id.homeScroll);
		
		// 初始化导航圆点
		mDotContainer = (LinearLayout) rootView.findViewById(R.id.dotcontainer);
		initilizeNavigationDots();

		// 初始化广告栏
		mViewPager = (ViewPager) rootView.findViewById(R.id.advertisements);
		mViewPager.setAdapter(mBannerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int position) {
						// TODO Auto-generated method stub
						mViewPagerCurrentPosition = position;
						for (int i = 0; i < mDots.size(); i++) {
							ImageView iv = mDots.get(i);
							if (i == position) {
								iv.setImageResource(R.drawable.page_indicator_focused);
							} else {
								iv.setImageResource(R.drawable.page_indicator_unfocused);
							}
						}
					}

					@Override
					public void onPageScrolled(int position,
							float positionOffset, int positionOffsetPixels) {
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
					}
				});

		mHandler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 1) {
					int count = mBannerAdapter.getCount();
					if (count > 1) {
						mViewPager.setCurrentItem((++mViewPagerCurrentPosition)
								% count, true);
					}
				}
				mHandler.removeMessages(1);
				mHandler.sendEmptyMessageDelayed(1, 5000);
				return false;
			}
		});
		mHandler.sendEmptyMessageDelayed(1, 5000);
		
		queryBanners();
		
		
		mCategoryCvs = (TextView) rootView.findViewById(R.id.homeCategoryCvs);
		mCategoryCvs.setOnClickListener(this);
		
		mCategoryMultipleShop = (TextView) rootView.findViewById(R.id.homeCategoryMultipleShop);
		mCategoryMultipleShop.setOnClickListener(this);
		
		mCategoryMarket = (TextView) rootView.findViewById(R.id.homeCategoryMarket);
		mCategoryMarket.setOnClickListener(this);
		
		mCategoryCommunityShop = (TextView) rootView.findViewById(R.id.homeCategoryCommunityShop);
		mCategoryCommunityShop.setOnClickListener(this);
		
		//初始化推荐商品
		mRecommendAdapter = new HomeRecommendProductsAdapter(getActivity(), null);
		mRecommendProducts = (ListView) rootView.findViewById(R.id.homeRecommendGoods);
		mRecommendProducts.setAdapter(mRecommendAdapter);
		setListViewHeightBasedOnChildren(mRecommendProducts);
		queryRecommendGoods();
		
		//初始化最近浏览的商品
		mCacheGoods = (SimpleHorizontalListView) rootView.findViewById(R.id.homeCacheGoods);

		return rootView;
	}

	private void queryBanners() {
		// TODO Auto-generated method stub
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "banner_qry", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);

//				mRecommendAdapter.addGoods(newgoods);
//				setListViewHeightBasedOnChildren(mRecommendGoods);
				
				try {
					JSONObject stuff = new JSONObject(str);
					
					int staus = stuff.getInt("status");
					
					if(staus == 0){
					
						JSONArray dataset = stuff.getJSONObject("result").getJSONArray("dataset");
						
						ArrayList<Banner> banners = new ArrayList<Banner>();
						
						for(int i=0, length=dataset.length(); i < length; i++){
							
							if(this.isCancelled()) return ;
							
							JSONObject item = dataset.getJSONObject(i);
							Banner b = new Banner();
							if(b.initialize(item))
								banners.add(b);
						}
						
						if(banners.size() > 0){
							mBannerAdapter.swapData(banners);
							_banners.clear();
							_banners = banners;
						}
						
						initilizeNavigationDots();
					
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				
				mTaskList.remove(this);
				
			}	
			
			
				
		};
		
		
		try {
			
			
//			position：
//					'1'	=>	'首页滚动',
//					'2'	=>	'首页右单',
//					'3'	=>	'首页中间长条',
//					'4'	=>	'便利购滚动',
//					'5'	=>	'连锁店滚动',
//					'6'	=>	'大超滚动',
//					'7'	=>	'社区店滚动'
			
			JSONObject params = new JSONObject();
			params.put("position", "1");
			params.put("merch_id", "");
			params.put("banner_id", "");
			
			task.execute(params);
			
			mTaskList.add(task);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void queryRecommendGoods() {
		// TODO Auto-generated method stub
		
		NetworkTask task = new NetworkTask((CApplication) getActivity().getApplication(), "qry_recmnd_product", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
//				
//				mRecommendAdapter.addGoods(newgoods);
//				setListViewHeightBasedOnChildren(mRecommendGoods);
				
				try {
					JSONObject stuff = new JSONObject(str);
					
					int staus = stuff.getInt("status");
					
					if(staus == 0){
						
						JSONArray dataset = stuff.getJSONObject("result").getJSONArray("dataset");
						
						ArrayList<RecommendProduct> newRecommendProducts = new ArrayList<RecommendProduct>();
						
						for(int i=0,length=dataset.length(); i<length; i++){
							
							if(this.isCancelled()) return;

							RecommendProduct rp = new RecommendProduct();
							
							if(rp.initialize(dataset.getJSONObject(i))){
								
								newRecommendProducts.add(rp);
								Log.d(TAG, "Add a new recommend prodcut to list ");
							}
						
						}
						
						mRecommendAdapter.addNewProducts(newRecommendProducts);
						setListViewHeightBasedOnChildren(mRecommendProducts);
						
					}
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mTaskList.remove(this);
			}
				
		};
		
		try {
			JSONObject params = new JSONObject();
			params.put("code", "");
			params.put("merch_id", "");
			
			task.execute(params);
			
			mTaskList.add(task);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initilizeNavigationDots() {

		if (mDots == null)
			mDots = new ArrayList<ImageView>();
		else
			mDots.clear();
		
		mDotContainer.removeAllViews();

		for (int i = 0; i < _banners.size(); i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			// 设置每个小圆点距离左边的间距
			params.setMargins(10, 10, 10, 10);
			ImageView imageView = new ImageView(getActivity());
			imageView.setLayoutParams(params);

			if (i == 0) {
				// 默认选中第一张图片
				imageView.setImageResource(R.drawable.page_indicator_focused);
			} else {
				// 其他图片都设置未选中状态
				imageView.setImageResource(R.drawable.page_indicator_unfocused);
			}
			mDots.add(imageView);
			mDotContainer.addView(imageView);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		case R.id.homeCategoryCvs:
			
			break;
		case R.id.homeCategoryMarket:
			
			break;
		case R.id.homeCategoryMultipleShop:
			
			break;
		case R.id.homeCategoryCommunityShop:
			
			break;
		}
		
	}
	
	private void setListViewHeightBasedOnChildren(ListView listView) {   
        // 获取ListView对应的Adapter   
		HomeRecommendProductsAdapter listAdapter = (HomeRecommendProductsAdapter) listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);  
        mScroller.smoothScrollTo(0, 0);
    }

}
