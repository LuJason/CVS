package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductInfo {
	
	private String pid; // 商品id
	private String name; // 商品名称
	private String brief; // 商品简介
	private long ref_price; // 参考价格
	private String merch_id; // 商户id
	private String class_code; // 商品分类代码
	private String standard_url; // 产品参数页面链接
	private String product_logos; // 产品样图文件名，用|分割多张
	private String pack_type; // 包装
	private String style; // 款式
	private String color; // 颜色（同一单品分类）
	private String material; // 材质
	private String size; // 尺寸
	private String expire_days; // 保质期天数
	private String barcode; // 产品编码(条码)
	private String standard; // 产品规格（同一单品分类）
	private String made_place; // 产地
	private String keyword; // 关键字，可以用空格分割
	private String brand; // 品牌
	private String attribute; // 扩展属性：name1#value1 value2 value3 valueN|name2#value1 value2 valueN|nameN#value1 value2 valueN
	private String update_time; // 更新时间
	private String record_time; // 记录时间
	private String status; // 
	private String flag; // 
	private String descript; // 商户产品描述
	private long price; // 商户定价
	private String discount; // 商户折扣
	private int store_count; // 库存
	private String pay_way; // 复合字段，支持的支付方式，|分割payment_way中的id
	private String post_way; // 符合字段，支持的配送方式
	private String expire_time; // 超期下架时间
	private String discount2; // 团购价格
	private String post_price; // 邮费
	private String return_days; // 退货天数
	private String change_days; // 换货天数
	private String ext_attri; // 扩展属性：name1#value1 value2 value3 valueN|name2#value1 value2 valueN|nameN#value1 value2 valueN
	private String cata_code; // 自定义分类代码（全代码）
	private String infourl; // 产品页面链接
	private String merch_name; // 商户名称
	private String send_district; // 配送区域名称
	private String send_range ; // 配送距离
	
	public boolean initialize(JSONObject item){
		
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
			size = item.getString("p_size");
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
			descript = item.getString("descript");
			price = item.getLong("price");
			discount = item.getString("discount");
			store_count = item.getInt("store_count");
			pay_way = item.getString("pay_way");
			post_way = item.getString("post_way");
			expire_time = item.getString("expire_time");
			discount2 = item.getString("discount2");
			post_price = item.getString("post_price");
			return_days = item.getString("return_days");
			change_days = item.getString("change_days");
			ext_attri = item.getString("ext_attri");
			cata_code = item.getString("cata_code");
			infourl = item.getString("infourl");
			merch_name = item.getString("merch_name");
			send_district = item.getString("send_district");
			send_range = item.getString("send_range");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
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

	public String getSize() {
		return size;
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

	public String getDescript() {
		return descript;
	}

	public Long getPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}

	public int getStoreCount() {
		return store_count;
	}

	public String getPayWay() {
		return pay_way;
	}

	public String getPostWay() {
		return post_way;
	}

	public String getExpireTime() {
		return expire_time;
	}

	public String getDiscount2() {
		return discount2;
	}

	public String getPostPrice() {
		return post_price;
	}

	public String getReturnDays() {
		return return_days;
	}

	public String getChangeDays() {
		return change_days;
	}

	public String getExt_attri() {
		return ext_attri;
	}

	public String getCata_code() {
		return cata_code;
	}

	public String getInfourl() {
		return infourl;
	}

	public String getMerchName() {
		return merch_name;
	}

	public String getSendDistrict() {
		return send_district;
	}

	public String getSendRange() {
		return send_range;
	}

	@Override
	public String toString() {
		return "ProductInfo [pid=" + pid + ", name=" + name + ", brief="
				+ brief + ", ref_price=" + ref_price + ", merch_id=" + merch_id
				+ ", class_code=" + class_code + ", standard_url="
				+ standard_url + ", product_logos=" + product_logos
				+ ", pack_type=" + pack_type + ", style=" + style + ", color="
				+ color + ", material=" + material + ", size=" + size
				+ ", expire_days=" + expire_days + ", barcode=" + barcode
				+ ", standard=" + standard + ", made_place=" + made_place
				+ ", keyword=" + keyword + ", brand=" + brand + ", attribute="
				+ attribute + ", update_time=" + update_time + ", record_time="
				+ record_time + ", status=" + status + ", flag=" + flag
				+ ", descript=" + descript + ", price=" + price + ", discount="
				+ discount + ", store_count=" + store_count + ", pay_way="
				+ pay_way + ", post_way=" + post_way + ", expire_time="
				+ expire_time + ", discount2=" + discount2 + ", post_price="
				+ post_price + ", return_days=" + return_days
				+ ", change_days=" + change_days + ", ext_attri=" + ext_attri
				+ ", cata_code=" + cata_code + ", infourl=" + infourl
				+ ", merch_name=" + merch_name + ", send_district="
				+ send_district + ", send_range=" + send_range + "]";
	}

	
	
}
