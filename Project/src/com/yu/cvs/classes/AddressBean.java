package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class AddressBean {
	
	private String id;
	private String isdn;
	private String province;
	private String city;
	private String city_code;
	private String district;
	private String sub_district;
	private String address;
	private String uname;
	private String update_time;
	private String post_code;
	private boolean defAdd;
	
	public boolean initialize(JSONObject data){
		
		try {
			id = data.getString("id");
			isdn = data.getString("isdn");
			province = data.getString("province");
			city = data.getString("city");
			city_code = data.getString("city_code");
			district = data.getString("district");
			sub_district = data.getString("sub_district");
			address = data.getString("address");
			uname = data.getString("uname");
			update_time = data.getString("update_time");
			post_code = data.getString("post_code");
			
			defAdd = data.getInt("default_tag") == 1;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}

	public String getId() {
		return id;
	}

	public String getIsdn() {
		return isdn;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getCityCode() {
		return city_code;
	}

	public String getDistrict() {
		return district;
	}

	public String getSubDistrict() {
		return sub_district;
	}

	public String getAddress() {
		return address;
	}

	public String getUname() {
		return uname;
	}

	public String getUpdateTime() {
		return update_time;
	}

	public String getPostCode() {
		return post_code;
	}

	public boolean isDefAdd() {
		return defAdd;
	}

	/**
	 * @return
	 */
	public CharSequence getFullAddrss() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		
		if(!TextUtils.isEmpty(getProvince())){
			builder.append(getProvince()).append(" ");
		}
		
		if(!TextUtils.isEmpty(getCity())){
			builder.append(getCity()).append(" ");
		}
		
		if(!TextUtils.isEmpty(getDistrict())){
			builder.append(getDistrict()).append(" ");
		}
		
		if(!TextUtils.isEmpty(getSubDistrict())){
			builder.append(getSubDistrict()).append(" ");
		}
		
		if(!TextUtils.isEmpty(getAddress())){
			builder.append(getAddress());
		}
		
		return builder.toString();
	}
	
}
