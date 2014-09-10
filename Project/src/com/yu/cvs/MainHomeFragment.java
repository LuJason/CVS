package com.yu.cvs;

import java.util.ArrayList;

import com.yu.cvs.adapters.AdvertisementAdapter;
import com.yu.cvs.classes.CAdver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainHomeFragment extends Fragment {

	private ViewPager mViewPager;
	private LinearLayout mDotContainer;
	private ArrayList<ImageView> mDots;
	private AdvertisementAdapter mAdapter;

	private ArrayList<CAdver> _advers;

	private Handler mHandler;

	private int mViewPagerCurrentPosition;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		_advers = new ArrayList<CAdver>();
		
		_advers.add( new CAdver("1", "http://d7.yihaodianimg.com/N02/M05/16/50/CgQCsFN3eaOALf7LAACxMWsFTq830200.jpg", "www.baidu.com"));
		_advers.add( new CAdver("2", "http://d7.yihaodianimg.com/N03/M07/0D/65/CgQCtFN17PmAT7fkAAD_8qdKNVE74400.jpg", "www.163.com"));
		_advers.add( new CAdver("3", "http://d9.yihaodianimg.com/N03/M08/10/96/CgQCtFN3CNaARNjLAAD09AQ6X5Y45100.jpg", "www.sohu.com"));
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_home, null);

		// 初始化导航圆点
		mDotContainer = (LinearLayout) v.findViewById(R.id.dotcontainer);
		initilizeNavigationDots();

		// 初始化广告栏
		mViewPager = (ViewPager) v.findViewById(R.id.advertisements);
		mAdapter = new AdvertisementAdapter(getActivity(), _advers);
		mViewPager.setAdapter(mAdapter);
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
					int count = mAdapter.getCount();
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

		return v;
	}

	private void initilizeNavigationDots() {

		if (mDots == null)
			mDots = new ArrayList<ImageView>();
		else
			mDots.clear();

		for (int i = 0; i < _advers.size(); i++) {
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

}
