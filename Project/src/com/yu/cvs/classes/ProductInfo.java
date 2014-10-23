package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductInfo {
	
	private String _pid;
	private String _descript;
	private String _price;
	private String _discount;
	private String _store_count;
	private String _pay_way;
	private String _post_way;
	private String _expire_time;
	private String _discount2;
	private String _post_price;
	private String _return_days;
	private String _change_days;
	private String _ext_attri;
	private String _cata_fullcode;
	private String _infourl;
	private String _update_time;
	private String _status;
	private String _flag;
	
	public boolean initialize(JSONObject item){
		
		//接口返回数据中不包含“pid”字段 ！！！！！ 与接口文档有出入  
		
		try {
			
			if(item.has("pid"))
				_pid = item.getString("pid");
			
			_descript = item.getString("descript");
			_price = item.getString("price");
			_discount = item.getString("discount");
			_store_count = item.getString("store_count");
			_pay_way = item.getString("pay_way");
			_post_way = item.getString("post_way");
			_expire_time = item.getString("expire_time");
			_discount2 = item.getString("discount2");
			_post_price = item.getString("post_price");
			_return_days = item.getString("return_days");
			_change_days = item.getString("change_days");
			_ext_attri = item.getString("ext_attri");
			_cata_fullcode = item.getString("cata_fullcode");
			_infourl = item.getString("infourl");
			_update_time = item.getString("update_time");
			_status = item.getString("status");
			_flag = item.getString("flag");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}

	public void setPid(String pid){
		this._pid = pid;
	}
	
	public String getPid() {
		return _pid;
	}

	public String getDescript() {
		return _descript;
	}

	public String getPrice() {
		return _price;
	}

	public String getDiscount() {
		return _discount;
	}

	public String getStoreCount() {
		return _store_count;
	}

	public String getPayWay() {
		return _pay_way;
	}

	public String getPostWay() {
		return _post_way;
	}

	public String getExpireTime() {
		return _expire_time;
	}

	public String getDiscount2() {
		return _discount2;
	}

	public String getPostPrice() {
		return _post_price;
	}

	public String getReturnDays() {
		return _return_days;
	}

	public String getChangeDays() {
		return _change_days;
	}

	public String getExtAttri() {
		return _ext_attri;
	}

	public String getCataFullcode() {
		return _cata_fullcode;
	}

	public String getInfourl() {
		return _infourl;
	}

	public String getUpdateTime() {
		return _update_time;
	}

	public String getStatus() {
		return _status;
	}

	public String getFlag() {
		return _flag;
	}

	@Override
	public String toString() {
		return "ProductInfo [_pid=" + _pid + ", _descript=" + _descript
				+ ", _price=" + _price + ", _discount=" + _discount
				+ ", _store_count=" + _store_count + ", _pay_way=" + _pay_way
				+ ", _post_way=" + _post_way + ", _expire_time=" + _expire_time
				+ ", _discount2=" + _discount2 + ", _post_price=" + _post_price
				+ ", _return_days=" + _return_days + ", _change_days="
				+ _change_days + ", _ext_attri=" + _ext_attri
				+ ", _cata_fullcode=" + _cata_fullcode + ", _infourl="
				+ _infourl + ", _update_time=" + _update_time + ", _status="
				+ _status + ", _flag=" + _flag + "]";
	}
	
}
