package com.yu.cvs.classes;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.network.ProtocolDefinition;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Commodity implements Parcelable{

	// pid 商品id
	// name 商品名称
	// brief 商品简介
	// ref_price 参考价格
	// price 实价
	// merch_id 商户id
	// class_code 商品分类全代码
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
	// evaluation 评价
	// attribute 扩展属性：name1#value1 value2 value3 valueN|name2#value1 value2
	// valueN|nameN#value1 value2 valueN
	// count 库存数量
	// sold_count 销量
	// merch_name 商户名称
	// lon 商户经度坐标
	// lat 商户纬度坐标
	// distance 距离
	// update_time 更新时间
	// record_time 记录时间
	// status
	// flag

	private static final String TAG = Commodity.class.getSimpleName();
	private String pid;
	private String name;
	private String brief;
	private long ref_price;
	private long price;
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
	private String evaluation;
	private String attribute;
	private int count;
	private String sold_count;
	private String merch_name;
	private String lon;
	private String lat;
	private String distance;
	private String update_time;
	private String record_time;
	private String status;
	private String flag;
	
	public Commodity(){}

	public boolean initialize(JSONObject item) {

		try {
			pid = item.getString("pid");
			name = item.getString("name");
			brief = item.getString("brief");
			ref_price = item.getLong("ref_price");
			price = item.getLong("price");
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
			evaluation = item.getString("evaluation");
			attribute = item.getString("attribute");
			count = item.getInt("count");
			sold_count = item.getString("sold_count");
			merch_name = item.getString("merch_name");
			lon = item.getString("lon");
			lat = item.getString("lat");
			distance = item.getString("distance");
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

	public long getPrice() {
		return price;
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

	public ArrayList<String> getProductLogos() {
		
		ArrayList<String> urls = new ArrayList<String>();
		
		String[] tmp = product_logos.split("\\|");
		if(tmp != null){
			
			for(String s : tmp){
				
				if("-".equals(s))
					continue;
//				"http://www.chaobl.com/publicdata/img/%s/%s/%s"
//				地址为http://www.chaobl.com/publicdata/img/商户编码/分类编码/从数据库查询出来得图片文件名
				String url = String.format(ProtocolDefinition.ProductLogoUrl, this.merch_id, this.class_code, s);
				
				Log.d(TAG, "Commodity's logo url is " + url);
				
				urls.add(url);
				
			}
		
		}
		
		
		
		
		return urls;
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

	public String getEvaluation() {
		return evaluation;
	}

	public String getAttribute() {
		return attribute;
	}

	public int getCount() {
		return count;
	}

	public String getSold_count() {
		return sold_count;
	}

	public String getMerchName() {
		return merch_name;
	}

	public String getLon() {
		return lon;
	}

	public String getLat() {
		return lat;
	}

	public String getDistance() {
		return distance;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public String getRecord_time() {
		return record_time;
	}

	public String getStatus() {
		return status;
	}

	public String getFlag() {
		return flag;
	}

	
	
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		out.writeString(pid);
		out.writeString(name);
		out.writeString(brief);
		out.writeLong(ref_price);
		out.writeLong(price);
		out.writeString(merch_id);
		out.writeString(class_code);
		out.writeString(standard_url);
		out.writeString(product_logos);
		out.writeString(pack_type);
		out.writeString(style);
		out.writeString(color);
		out.writeString(material);
		out.writeString(p_size);
		out.writeString(expire_days);
		out.writeString(barcode);
		out.writeString(standard);
		out.writeString(made_place);
		out.writeString(keyword);
		out.writeString(brand);
		out.writeString(evaluation);
		out.writeString(attribute);
		out.writeInt(count);
		out.writeString(sold_count);
		out.writeString(merch_name);
		out.writeString(lon);
		out.writeString(lat);
		out.writeString(distance);
		out.writeString(update_time);
		out.writeString(record_time);
		out.writeString(status);
		out.writeString(flag);
	}
	
	public static final Parcelable.Creator<Commodity> CREATOR = new Creator<Commodity>() {
		@Override
		public Commodity[] newArray(int size) {
			return new Commodity[size];
		}

		@Override
		public Commodity createFromParcel(Parcel in) {
			return new Commodity(in);
		}
	};
	
	public Commodity(Parcel in)
    {
		pid = in.readString();
		name = in.readString();
		brief = in.readString();
		ref_price = in.readLong();
		price = in.readLong();
		merch_id = in.readString();
		class_code = in.readString();
		standard_url = in.readString();
		product_logos = in.readString();
		pack_type = in.readString();
		style = in.readString();
		color = in.readString();
		material = in.readString();
		p_size = in.readString();
		expire_days = in.readString();
		barcode = in.readString();
		standard = in.readString();
		made_place = in.readString();
		keyword = in.readString();
		brand = in.readString();
		evaluation = in.readString();
		attribute = in.readString();
		count = in.readInt();
		sold_count = in.readString();
		merch_name = in.readString();
		lon = in.readString();
		lat = in.readString();
		distance = in.readString();
		update_time = in.readString();
		record_time = in.readString();
		status = in.readString();
		flag = in.readString();
    }

}
