package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author jason
 *
 * 用户在购物车中添加的货物。
 *
 *
 */
public class Good implements Cloneable{
	
//	product_id	产品id
//	product_name	产品名称
//	barcode	产品编码
//	price	价格
//	ref_price	参考价格
//	count	购买数量
//	store_count	库存
//	logo_url	缩略图
//	merch_id	商户id
//	merch_name	商户名
	
	private String product_id;
	private String product_name;
	private String barcode;
	private long price;
	private long ref_price;
	private int count;
	private int store_count;
	private String logo_url;
	private String merch_id;
	private String merch_name;
	
	private boolean selected;
	
	public boolean initialize(JSONObject item){
		
		try {
			
			selected = true;
			
			product_id = item.getString("product_id");
			product_name = item.getString("product_name");
			barcode = item.getString("barcode");
			price = item.getLong("price");
			ref_price = item.getLong("ref_price");
			count = item.getInt("count");
			store_count = item.getInt("store_count");
			logo_url = item.getString("logo_url");
			merch_id = item.getString("merch_id");
			merch_name = item.getString("merch_name");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	public String getProductId() {
		return product_id;
	}

	public String getProductName() {
		return product_name;
	}

	public String getBarcode() {
		return barcode;
	}

	public Long getPrice() {
		return price;
	}

	public long getRefPrice() {
		return ref_price;
	}

	public int getCount() {
		return count;
	}

	public int getStoreCount() {
		return store_count;
	}

	public String getLogoUrl() {
		return logo_url;
	}

	public String getMerchId() {
		return merch_id;
	}

	public String getMerchName() {
		return merch_name;
	}

	/**
	 * @return
	 */
	public long calculateGoodPrice() {
		// TODO Auto-generated method stub
		
		long sum = price * count;
		
		return sum;
		
	}

	/**
	 * @param num
	 */
	public void setCount(int num) {
		// TODO Auto-generated method stub
		this.count = num;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		
		Good g = (Good) super.clone();
		
		g.barcode = new String(this.barcode);
		g.count = this.count;
		g.logo_url = new String(this.logo_url);
		g.merch_id = new String(this.merch_id);
		g.merch_name = new String(this.merch_name);
		g.price = this.price;
		g.product_id = new String(this.product_id);
		g.product_name = new String(this.product_name);
		g.ref_price = this.ref_price;
		g.selected = this.selected;
		g.store_count = this.store_count;
		
		return g;
	}
	
	
}
