package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.network.ProtocolDefinition;

public class Banner {
	
	private String banner_id;
	private String title;
	private String subject;
	private String url;
	private String merch_id;
	private String position;
	private String banner_logo;
	private String width;
	private String height;
	private String sort;
	private String update_time;
	
	public boolean initialize(JSONObject item){
		
		try {
			
			banner_id = item.getString("banner_id");
			title = item.getString("title");
			subject = item.getString("subject");
			url = item.getString("url");
			merch_id = item.getString("merch_id");
			position = item.getString("position");
			banner_logo = item.getString("banner_logo");
			width = item.getString("width");
			height = item.getString("height");
			sort = item.getString("sort");
			update_time = item.getString("update_time");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}

	public String getBannerId() {
		return banner_id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubject() {
		return subject;
	}

	public String getUrl() {
		return url;
	}

	public String getMerchId() {
		return merch_id;
	}

	public String getPosition() {
		return position;
	}

	public String getBannerLogo() {
		
		String url = String.format(ProtocolDefinition.BannerLogoUrl, banner_logo);
		
		return url;
	}

	public String getWidth() {
		return width;
	}

	public String getHeight() {
		return height;
	}

	public String getSort() {
		return sort;
	}

	public String getUpdateTime() {
		return update_time;
	}
	
	
	

}
