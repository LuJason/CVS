package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;

import com.yu.cvs.CApplication;

public class CAccount {

	private String id;
	private String userid;
	private String isdn;
	private String isdn2;
	private String email;
	private String email2;
	private String province;
	// private String city;
	private String city_code;
	// private String district;
	private String sub_district;
	private String address;
	private String post_code;
	private String uname;
	private String status;
	private String flag;
	private String record_time;
	private String update_time;

	// 地理位置

	private double latitude;
	private double longitude;
	private String city;
	private String district;
	private String street;

	private boolean _isLogin;
	
	
	private CacheCart _cacheCart; //缓存购物车
	private AccountCart _accountCart; //用户购物车

	public CAccount() {
		_isLogin = false;
		initializeCart();
	}

	public boolean initilize(JSONObject data) {
		
		try {

			id = data.getString("id");
			userid = data.getString("userid");
			isdn = data.getString("isdn");
			isdn2 = data.getString("isdn2");
			email = data.getString("email");
			email2 = data.getString("email2");
			province = data.getString("province");
			// city = data.getString("city");
			city_code = data.getString("city_code");
			// district = data.getString("district");
			sub_district = data.getString("sub_district");
			address = data.getString("address");
			post_code = data.getString("post_code");
			uname = data.getString("uname");
			status = data.getString("status");
			flag = data.getString("flag");
			record_time = data.getString("record_time");
			update_time = data.getString("update_time");

			_isLogin = true;

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}

		return true;

	}

	public boolean isLogin() {

		return _isLogin;
	}

	public String getId() {
		return id;
	}

	public String getUserid() {
		return userid;
	}

	public String getIsdn() {
		return isdn;
	}

	public String getIsdn2() {
		return isdn2;
	}

	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}

	public String getProvince() {
		return province;
	}

	public String getCity_code() {
		return city_code;
	}

	public String getSub_district() {
		return sub_district;
	}

	public String getAddress() {
		return address;
	}

	public String getPost_code() {
		return post_code;
	}

	public String getUname() {
		return uname;
	}

	public String getStatus() {
		return status;
	}

	public String getFlag() {
		return flag;
	}

	public String getRecord_time() {
		return record_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getStreet() {
		return street;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public BaseCart getCart() {
		
		BaseCart cart;
		
		if(isLogin()){
			cart = _accountCart;
		}else{
			cart = _cacheCart;
		}
		
		return cart;
	}
	
	public void initializeCart(){
		
		 _accountCart = new AccountCart();
		 _cacheCart = CacheCart.Instance();
	}

	public void clear(){
		_isLogin = false;
		_accountCart.clear();
		_cacheCart.clear();
//		_accountCart = null;
//		_cacheCart = null;
	}

}
