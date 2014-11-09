package com.yu.cvs.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.yu.cvs.R;
import com.yu.cvs.classes.AttributeManager;
import com.yu.cvs.classes.AttributeManager.Attribute;
import com.yu.cvs.classes.AttributeManager.AttritubeChangedListener;
import com.yu.cvs.classes.AttributeManager.ValueBean;
import com.yu.cvs.classes.ProductInfo;
import com.yu.cvs.myview.AddAndSubView;
import com.yu.cvs.myview.AddAndSubView.OnNumChangeListener;
import com.yu.cvs.myview.FlowRadioGroup;

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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CommInfoBriefFragment extends Fragment implements OnClickListener {
	
	private static final String TAG = CommInfoBriefFragment.class.getSimpleName();
	
	private Button mAddToCart;
	
	private AddAndSubView mAdjustQuantity;
	
	private CCommodityInfoActivity mParent;
	
	private AttributeManager mAttributeManager;
	
	private TextView mProductPrice, mProductTitle;
	
	private FlowRadioGroup mAttributeColor, mAttributeStandard;
	
	private ProductInfo pInfo;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		mParent = (CCommodityInfoActivity) activity;
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.commodity_info_brief_fragment, null);
		
		mProductPrice = (TextView) root.findViewById(R.id.ProductPrice);
		mProductTitle = (TextView) root.findViewById(R.id.ProductTitle);

		mAdjustQuantity = (AddAndSubView) root.findViewById(R.id.AdjustQuantity);
		mAdjustQuantity.setOnNumChangeListener(new OnNumChangeListener() {
			
			@Override
			public void onNumChange(View view, int num) {
				// TODO Auto-generated method stub
				checkAddToCartButton();
			}
		});
		
		
		mAttributeColor = (FlowRadioGroup) root.findViewById(R.id.AttributeColor);
		mAttributeColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Log.d(TAG, String.format("Checked RadioButton's id is %d", checkedId));
				RadioButton button = (RadioButton) group.findViewById(checkedId);
				String value = button.getText().toString();
				mAttributeManager.selectColor(value);
			}
			
		});
		
		mAttributeStandard = (FlowRadioGroup) root.findViewById(R.id.AttributeStandard);
		mAttributeStandard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton button = (RadioButton) group.findViewById(checkedId);
				if(button != null){
					String value = button.getText().toString();
					mAttributeManager.selectStanard(value);
				}
			}
		});
		
		mAddToCart = (Button) root.findViewById(R.id.AddToCart);
		mAddToCart.setOnClickListener(this);
		
		getCommodityInfo();
		
		return root;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		mAttributeColor.removeAllViews();
		mAttributeStandard.removeAllViews();
		super.onDestroyView();
	}
	
	protected void uiInitialize() {
		// TODO Auto-generated method stub
		
		if(mAttributeManager == null) return ;
		
		clearUpAttribute();
		
		clearUpProductsInfo();

		mAttributeManager.setAttributeChangedListener(attritubeListener);
		
		checkAddToCartButton();
		
//		ArrayList<ProductInfo> products = mAttributeManager.getSelectedProduct();
//		
//		int size = products.size();
//		
//		System.out.println(String.format("当前选中商品数量：%d",size));
//		
//		if (products.size() > 1) {
//
//			ProductInfo p = products.get(0);
//
//			mProductTitle.setText(p.getName());
//
//			mProductPrice.setText(mAttributeManager.getPrice());
//
//		} else {
//			
//			
//		}
		
		
	}
	
	
	private void clearUpAttribute(){
		
		HashMap<String, Attribute> attributes = mAttributeManager.getAttributes();
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		float density = getResources().getDisplayMetrics().density;
		
		RadioGroup.LayoutParams params_rb = new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		int margin = (int) (3 * density);
		
		params_rb.setMargins(margin, margin, margin, margin);
		
		
		
		Attribute attribute = attributes.get(AttributeManager.ATTR_KEY_COLOR);
		
		ArrayList<ValueBean> values = attribute.getVaulues();
		
		int id = 0;
		
		for(ValueBean v : values){
			
			RadioButton button = (RadioButton) inflater.inflate(R.layout.attribute_radiobutton, null);
			
			button.setText(v.getValue());
			
			switch(v.getStatus()){
			case ValueBean.STATUS_ENABLE:
				button.setEnabled(true);
				button.setChecked(false);
				break;
			case ValueBean.STATUS_DISABLE:
				button.setEnabled(false);
				break;
			case ValueBean.STATUS_SELECTED:
				button.setEnabled(true);
				button.setChecked(true);
				break;
				
			}
			
			
			
			button.setId(id); id ++;

			mAttributeColor.addView(button , params_rb);

		}
		
		id = 0;
		
		attribute = attributes.get(AttributeManager.ATTR_KEY_STANDARD);
		
		values = attribute.getVaulues();
		
		for(ValueBean v : values){
			
			RadioButton button = (RadioButton) inflater.inflate(R.layout.attribute_radiobutton, null);
			
			button.setText(v.getValue());
			
			switch(v.getStatus()){
			case ValueBean.STATUS_ENABLE:
				button.setEnabled(true);
				button.setChecked(false);
				break;
			case ValueBean.STATUS_DISABLE:
				button.setEnabled(false);
				break;
			case ValueBean.STATUS_SELECTED:
				button.setEnabled(true);
				button.setChecked(true);
				break;
				
			}
			
			button.setId(id); id ++;

			mAttributeStandard.addView(button , params_rb);

		}
		
	}
	
	
	private void getCommodityInfo(){
		
		AsyncTask<String, Integer, AttributeManager> task = new AsyncTask<String, Integer, AttributeManager>(){

			@Override
			protected AttributeManager doInBackground(String... params) {
				// TODO Auto-generated method stub
				AttributeManager c = mParent.getCommodityInfo();
				return c;
			}

			@Override
			protected void onPostExecute(AttributeManager result) {
				// TODO Auto-generated method stub
				
				Log.d(TAG, "Read Commodity Info!!");
				
				if(result == null){
					
					Log.d(TAG, "Commodity Info is null");
					
				}else{
					Log.d(TAG, result.toString());
					mAttributeManager = result;
					uiInitialize();
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
			if(pInfo != null){
				int quantity = mAdjustQuantity.getNum();
				mParent.addToCart(this, pInfo, quantity);
			}
			break;
			
		}
	}
	
	
	private AttritubeChangedListener attritubeListener = new AttritubeChangedListener() {
		
		@Override
		public void colorAttributeChange() {
			// TODO Auto-generated method stub
			Log.d(TAG, "Attribute Changed !!!");
			
			ArrayList<ProductInfo> selectedProducts = mAttributeManager.getSelectedProduct();
			
			TreeSet<String> values = new TreeSet<String>();
			
			for(ProductInfo p : selectedProducts){
			
				String standard = p.getStandard();
				
				values.add(standard);
				
			}
			
			mAttributeStandard.check(-1);
			
			for(int i=0,size=mAttributeStandard.getChildCount(); i<size; i++){
				
				View v = mAttributeStandard.getChildAt(i);
				
				if(v instanceof RadioButton){
					
					RadioButton rb = (RadioButton) v;
					
					String attr = rb.getText().toString();
					
					if(values.contains(attr)){
						rb.setEnabled(true);
						Log.d(TAG, "Enable Button ! ");
					}else{
						rb.setEnabled(false);
						Log.d(TAG, "Disalbe Button ! ");
					}
					
				}
				
			}
			
			clearUpProductsInfo();
			
			checkAddToCartButton();
			
		}

		@Override
		public void standardAttributeChange(ProductInfo selectedProduct) {
			// TODO Auto-generated method stub
			
			if(selectedProduct == null) return ;
			
			Log.d(TAG, String.format(
					"Selected product's color is %s ,standard is %s .",
					selectedProduct.getColor(), selectedProduct.getStandard() ));
			
			mProductTitle.setText(selectedProduct.getName());
			
			mProductPrice.setText(String.format("￥ %.2f", (float)selectedProduct.getPrice()/100));
			
			pInfo = selectedProduct;
			
			checkAddToCartButton();
			
		}
	};

	protected void clearUpProductsInfo() {
		// TODO Auto-generated method stub
		
//		mProductTitle.setText(text);
		
		mProductTitle.setText(String.format("%s %s", mAttributeManager.getmName(), mAttributeManager.getmBrief()));
		
		mProductPrice.setText(mAttributeManager.getPrice());
		
		
	}
	
	private void checkAddToCartButton(){
		
		boolean isSelectedColor = mAttributeColor.getCheckedRadioButtonId() != -1;
		boolean isSelectedStandard = mAttributeStandard.getCheckedRadioButtonId() != -1;
		boolean isQuantity = mAdjustQuantity.getNum() > 0;
		
		
		if(isSelectedColor && isSelectedStandard && isQuantity){
			mAddToCart.setEnabled(true);
		}else{
			mAddToCart.setEnabled(false);
		}
		
	}
	

}
