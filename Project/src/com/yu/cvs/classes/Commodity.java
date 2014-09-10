package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Commodity {

	// pid 商品id
	// name 商品名称
	// brief 商品简介
	// ref_price 参考价格
	// merch_id 商户id
	// class_code 商品分类代码
	// standard_url 产品参数页面链接
	// product_logos 产品样图文件名，用|分割多张
	// pack_type 包装
	// style 款式
	// color 颜色（同一单品分类）
	// material 材质
	// p_size 尺寸
	// expire_days 保质期天数
	// barcode 产品编码(条码)
	// standard 产品规格（同一单品分类）
	// made_place 产地
	// keyword 关键字，可以用空格分割
	// brand 品牌
	// attribute 扩展属性：name1#value1 value2 value3 valueN|name2#value1 value2
	// valueN|nameN#value1 value2 valueN
	// update_time 更新时间
	// record_time 记录时间
	// status
	// flag

	private String pid;
	private String name;
	private String brief;
	private long ref_price;
	private String merch_id;
	private String class_code;
	private String standard_url;
	private String product_logos;
	private String pack_type;
	private String style;
	private String color;
	private String material;
	private String p_size;
	private String expire_days;
	private String barcode;
	private String standard;
	private String made_place;
	private String keyword;
	private String brand;
	private String attribute;
	private String update_time;
	private String record_time;
	private String status;
	private String flag;

	public boolean initialize(JSONObject item) {

		try {
			pid = item.getString("pid");
			name = item.getString("name");
			brief = item.getString("brief");
			ref_price = item.getLong("ref_price");
			merch_id = item.getString("merch_id");
			class_code = item.getString("class_code");
			standard_url = item.getString("standard_url");
			product_logos = item.getString("product_logos");
			pack_type = item.getString("pack_type");
			style = item.getString("style");
			color = item.getString("color");
			material = item.getString("material");
			p_size = item.getString("p_size");
			expire_days = item.getString("expire_days");
			barcode = item.getString("barcode");
			standard = item.getString("standard");
			made_place = item.getString("made_place");
			keyword = item.getString("keyword");
			brand = item.getString("brand");
			attribute = item.getString("attribute");
			update_time = item.getString("update_time");
			record_time = item.getString("record_time");
			status = item.getString("status");
			flag = item.getString("flag");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;
	}

	public String getPid() {
		return pid;
	}

	public String getName() {
		return name;
	}

	public String getBrief() {
		return brief;
	}

	public long getRefPrice() {
		return ref_price;
	}

	public String getMerchId() {
		return merch_id;
	}

	public String getClassCode() {
		return class_code;
	}

	public String getStandardUrl() {
		return standard_url;
	}

	public String getProductLogos() {
		return product_logos;
	}

	public String getPackType() {
		return pack_type;
	}

	public String getStyle() {
		return style;
	}

	public String getColor() {
		return color;
	}

	public String getMaterial() {
		return material;
	}

	public String getPSize() {
		return p_size;
	}

	public String getExpireDays() {
		return expire_days;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getStandard() {
		return standard;
	}

	public String getMadePlace() {
		return made_place;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getBrand() {
		return brand;
	}

	public String getAttribute() {
		return attribute;
	}

	public String getUpdateTime() {
		return update_time;
	}

	public String getRecordTime() {
		return record_time;
	}

	public String getStatus() {
		return status;
	}

	public String getFlag() {
		return flag;
	}

}
