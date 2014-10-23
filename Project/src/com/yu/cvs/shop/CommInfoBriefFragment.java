package com.yu.cvs.shop;

import com.yu.cvs.R;
import com.yu.cvs.classes.AddAndSubView;
import com.yu.cvs.classes.Commodity;
import com.yu.cvs.classes.ProductInfo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class CommInfoBriefFragment extends Fragment implements OnClickListener {
	
	private static final String TAG = CommInfoBriefFragment.class.getSimpleName();
	
	private Button mAddToCart;
	private AddAndSubView mAdjustQuantity;
	
	private CCommodityInfoActivity parent;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		parent = (CCommodityInfoActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		View root = inflater.inflate(R.layout.commodity_info_brief_fragment, null);

		mAddToCart = (Button) root.findViewById(R.id.AddToCart);
		mAddToCart.setOnClickListener(this);
		mAdjustQuantity = (AddAndSubView) root.findViewById(R.id.AdjustQuantity);
		mAdjustQuantity.setOnClickListener(this);
		
		getCommodityInfo();
		return root;
	}
	
	
	private void getCommodityInfo(){
		
		AsyncTask<String, Integer, ProductInfo> task = new AsyncTask<String, Integer, ProductInfo>(){

			@Override
			protected ProductInfo doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				ProductInfo c = parent.getCommodityInfo();
				
				return c;
			}

			@Override
			protected void onPostExecute(ProductInfo result) {
				// TODO Auto-generated method stub
				
				Log.d(TAG, "Read Commodity Info!!");
				
				if(result == null){
					
					Log.d(TAG, "Commodity Info is null");
					
				}else{
					
					Log.d(TAG, result.toString());
					
				}
				
				super.onPostExecute(result);
			}
			
			
			
		}.execute("");
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.AddToCart:
			int quantity = mAdjustQuantity.getNum();
			parent.addToCart(this, quantity);			
			break;
			
		}
	}
	

}
