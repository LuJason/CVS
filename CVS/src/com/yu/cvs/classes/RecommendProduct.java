package com.yu.cvs.classes;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.network.ProtocolDefinition;

import android.util.Log;

public class RecommendProduct {

	private static final String TAG = RecommendProduct.class.getSimpleName();
	
	private String id; // 商品id
	private long price; // 价格
	private String ref_price; // 参考价格
	private String discount; // 折扣0.1-1
	private String count; // 库存数量
	private String code; // 类别代码
	private String expire_time; // 超期时间
	private String merch_id; // 商户id
	private String info_url; // 商品展示页url
	private String stand_url; // 参数展示页
	private String product_logos; // 商品图片
	private String pname; // 商品名称
	private String pbrief; // 商品简介
	private String cata; // 用户自定义分类代码
	private String sold_count; // 销售量
	private String evaluate; // 评价
	private String promote; // 促销信息
	private String sort; // 排序值
	private String barcode; // 产品条码

	public boolean initialize(JSONObject item) {

		try {
			id = item.getString("id");
			price = item.getLong("price");
			ref_price = item.getString("ref_price");
			discount = item.getString("discount");
			count = item.getString("count");
			code = item.getString("code");
			expire_time = item.getString("expire_time");
			merch_id = item.getString("merch_id");
			info_url = item.getString("info_url");
			stand_url = item.getString("standard_url");
			product_logos = item.getString("product_logos");
			pname = item.getString("pname");
			pbrief = item.getString("pbrief");
			cata = item.getString("cata");
			sold_count = item.getString("sold_count");
			evaluate = item.getString("evaluate");
			promote = item.getString("promote");
			sort = item.getString("sort");
			barcode = item.getString("barcode");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.w(TAG, "Initialize recommend product faild.");
			return false;
		}

		return true;
	}

	public String getId() {
		return id;
	}

	public long getPrice() {
		return price;
	}

	public String getRefPrice() {
		return ref_price;
	}

	public String getDiscount() {
		return discount;
	}

	public String getCount() {
		return count;
	}

	public String getCode() {
		return code;
	}

	public String getExpireTime() {
		return expire_time;
	}

	public String getMerchId() {
		return merch_id;
	}

	public String getInfoUrl() {
		return info_url;
	}

	public String getStandUrl() {
		return stand_url;
	}

	public ArrayList<String> getProductLogos() {
		
		ArrayList<String> urls = new ArrayList<String>();
		
		String[] tmp = product_logos.split("\\|");
		if(tmp != null){
			
			for(String s : tmp){
				
				if("-".equals(s))
					continue;
//				"http://www.chaobl.com/publicdata/img/%s/%s/%s"
//				地址为http://www.chaobl.com/publicdata/img/商户编码/分类编码/从数据库查询出来得图片文件名
				String url = String.format(ProtocolDefinition.ProductLogoUrl, this.merch_id, this.code, s);
				
				Log.d(TAG, "Recommend product's logo url is " + url);
				
				urls.add(url);
				
			}
		
		}
		
		
		return urls;
	}

	public String getPname() {
		return pname;
	}

	public String getPbrief() {
		return pbrief;
	}

	public String getCata() {
		return cata;
	}

	public String getSoldCount() {
		return sold_count;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public String getPromote() {
		return promote;
	}

	public String getSort() {
		return sort;
	}

	public String getBarcode() {
		return barcode;
	}

}
