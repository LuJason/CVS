package com.yu.cvs.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.text.TextUtils;
import android.util.Log;

public class AttributeManager {
	
	public static class Attribute{
		
		String name;
		ArrayList<ValueBean> values;

		public Attribute(String name) {
			super();
			// TODO Auto-generated constructor stub
			values = new ArrayList<AttributeManager.ValueBean>();
		}
		
		public ArrayList<ValueBean> getVaulues(){
			return values;
		}
		
		public void addAttriBean(ValueBean bean){
			
			boolean isContain = false;
			
			for(ValueBean b : values){
				if(b.value.equals(bean.value)){
					isContain = true;
					break;
				}
			}
			
			if(!isContain)
				values.add(bean);
			
		}
		
		public void selectAttribute(String attriValue) {

			for (ValueBean bean : values) {

				if (attriValue.equals(bean.value)) {

					if (bean.status == ValueBean.STATUS_DISABLE)
						break;

					if (bean.status == ValueBean.STATUS_ENABLE) {
						bean.status = ValueBean.STATUS_SELECTED;
					} else {
						bean.status = ValueBean.STATUS_ENABLE;
					}

				} else {
					if (bean.status != ValueBean.STATUS_DISABLE)
						bean.status = ValueBean.STATUS_ENABLE;
				}

			}

		}
		
		/**
		 * @return 返回选中的属性， 如果返回null表示没有属性被选中。
		 */
		public ValueBean getSelectedAttribute(){
			
			ValueBean v = null ;
			
			for(ValueBean bean : values){
				
				if(bean.getStatus() == ValueBean.STATUS_SELECTED){
					v = bean;
					break;
				}
			}
			
			return v;
		}
		
		public String getName(){
			return name;
		}
	}
	
	public static class ValueBean{
		
		public static final int STATUS_ENABLE = 0;
		public static final int STATUS_SELECTED = 1;
		public static final int STATUS_DISABLE = 2;
		
		String value;
		int status;
		public ValueBean(String value, int status) {
			super();
			this.value = value;
			this.status = status;
		}
		
		public int getStatus(){
			return status;
		}
		
		public String getValue(){
			return value;
		}
		
	}
	
	
	public interface AttritubeChangedListener{
		public void colorAttributeChange();
		public void standardAttributeChange(ProductInfo p);
	}
	
	
	public static final String ATTR_KEY_COLOR = "color";
	public static final String ATTR_KEY_STANDARD = "standard";
	private static final String TAG = AttributeManager.class.getSimpleName();
	
	private ArrayList<ProductInfo> products;
	private ArrayList<ProductInfo> selectedProducts;
	
	private HashMap<String, Attribute> attributes;
	
	private String mName, mBrief;
	private long minPrice, maxPrice;
	
	private AttritubeChangedListener _listener;
	
	public AttributeManager(Commodity commdity) {
		super();
		// TODO Auto-generated constructor stub
		products = new ArrayList<ProductInfo>();
		selectedProducts = new ArrayList<ProductInfo>();
		attributes = new HashMap<String, AttributeManager.Attribute>();
		
		if(commdity != null){
			mName = commdity.getName();
			mBrief = commdity.getBrief();
		}

	}

	public String getmName() {
		return mName;
	}

	public String getmBrief() {
		return mBrief;
	}

	public void setAttributeChangedListener(AttritubeChangedListener l) {

		_listener = l;
	}
	
	public HashMap<String, Attribute> getAttributes() {
		return attributes;
	}

	public ArrayList<ProductInfo> getSelectedProduct(){
		return selectedProducts;
	}
	
	public void addProoduct(ProductInfo p){
		
		products.add(p);
		
		if(attributes.containsKey(ATTR_KEY_COLOR)){
			Attribute a = attributes.get(ATTR_KEY_COLOR);
			a.addAttriBean(new ValueBean(p.getColor(), ValueBean.STATUS_ENABLE));
		}else{
			Attribute a = new Attribute("颜色");
			a.addAttriBean(new ValueBean(p.getColor(), ValueBean.STATUS_ENABLE));
			attributes.put(ATTR_KEY_COLOR, a);
		}
		
		if(attributes.containsKey(ATTR_KEY_STANDARD)){
			Attribute a = attributes.get(ATTR_KEY_STANDARD);
			a.addAttriBean(new ValueBean(p.getStandard(), ValueBean.STATUS_ENABLE));
		}else{
			Attribute a = new Attribute("规格");
			a.addAttriBean(new ValueBean(p.getStandard(), ValueBean.STATUS_ENABLE));
			attributes.put(ATTR_KEY_STANDARD, a);
		}
		
	}
	
	public String getPrice(){
		
		String price = String.format("￥ %.2f-%.2f", (float)minPrice/100, (float)maxPrice/100);
		
		return price;
		
	}
	
	/**
	 *  初始化AttributeManager
	 */
	public void initialize(){
		
		maxPrice = Long.MIN_VALUE;
		minPrice = Long.MAX_VALUE;
		
		long tmpPrice = 0;
		
		for(ProductInfo p : products){
			
			tmpPrice = p.getPrice();
			
			if(tmpPrice > maxPrice)
				maxPrice = tmpPrice;
			
			if(tmpPrice < minPrice)
				minPrice = tmpPrice;
		
		}
		
		Log.d(TAG,String.format("Products' Price domain is %d - %d", minPrice, maxPrice));
		
		selectedProducts.clear();
		selectedProducts.addAll(products);
		
	}
	
	/**
	 * 选中的商品的一个属性，然后筛选商品
	 */
	private void selectAttribute(String attriKey, String attriValue) {
		// TODO Auto-generated method stub
		
		Attribute attribute = attributes.get(attriKey);
		attribute.selectAttribute(attriValue);
		
		filtrateProducts();
		
	}
	
	public void selectColor(String attriValue){
		Attribute attribute = attributes.get(ATTR_KEY_COLOR);
		attribute.selectAttribute(attriValue);
		
		filterateColor();
		
	}

	private void filterateColor() {
		// TODO Auto-generated method stub
		Attribute a = attributes.get(ATTR_KEY_COLOR);
		ValueBean bean = a.getSelectedAttribute();
		
		selectedProducts.clear();
		
		for (ProductInfo p : products) {
			if(bean.value.equals(p.getColor()))
				selectedProducts.add(p);
		}
		
		refreshAttributes();
		
	}
	
	public void selectStanard(String attriValue){
		
		if(TextUtils.isEmpty(attriValue))
			return ;
		
		ProductInfo selectedProduct = null;
		
		for(Iterator<ProductInfo> it = selectedProducts.iterator(); it.hasNext();){
			
			ProductInfo p = it.next();
			
			if(attriValue.equals(p.getStandard())){
				
				selectedProduct = p;
				
			}
			
		}
		
		
		
		if(_listener != null){
			_listener.standardAttributeChange(selectedProduct);
		}
		
	}
	

	/**
	 * 根据选中的属性，筛选商品加入到“选中商品列表” 
	 */
	private void filtrateProducts() {
		// TODO Auto-generated method stub
		
		// 1 取得选中属性的值，放入HashMap
		HashMap< String, ValueBean> keyAttri = new HashMap<String, ValueBean>();
		
		for(String key : attributes.keySet()){
			Attribute a = attributes.get(key);
			keyAttri.put(key, a.getSelectedAttribute());
		}
		
		// 2 根据属性筛选商品，放入到selectedProducts中
		selectedProducts.clear();
		
		ValueBean tmp ;
		
		boolean isSelected ;
		
		for(ProductInfo p : products){
			
			isSelected = true;
		
			tmp = keyAttri.get(ATTR_KEY_COLOR);
			if(tmp != null){
				isSelected = isSelected && tmp.value.equals(p.getColor());
			}
			
			tmp = keyAttri.get(ATTR_KEY_STANDARD);
			if(tmp != null){
				isSelected = isSelected && tmp.value.equals(p.getStandard());
			}
			
			if(isSelected){
				selectedProducts.add(p);
			}
		}
		
		// 3 依据选中的商品，重新设置属性列表的状态（ValueBean.status）
		if(selectedProducts.size() == 1){
			//找到唯一的商品
			
			
			
		
		}else{
			
			refreshAttributes(keyAttri);
			
		}
		
		
	}
	
	private void refreshAttributes(){
		
		HashMap<String, Integer> selectAttribute = new HashMap<String, Integer>();
		
		String tmp ;
		
		for(ProductInfo p : selectedProducts){
			tmp = p.getStandard();
			selectAttribute.put(tmp, ValueBean.STATUS_ENABLE);
		}
		
		Attribute attr = attributes.get(ATTR_KEY_STANDARD);
		
		ArrayList<ValueBean> values = attr.getVaulues();
		
		for(ValueBean bean : values){
			
			if(selectAttribute.containsKey(bean.value))
				bean.status = ValueBean.STATUS_ENABLE;
			else
				bean.status = ValueBean.STATUS_DISABLE;
			
		}
		
		if(_listener != null){
			_listener.colorAttributeChange();
		}
		
	}

	private void refreshAttributes(HashMap<String, ValueBean> keyAttri) {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		for(ProductInfo p : selectedProducts){
		
			p.getColor();
		
		}
		
	}
	

}
