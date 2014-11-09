package com.yu.cvs.classes;

import org.json.JSONException;
import org.json.JSONObject;

public class CAdver {

	private String _id;

	private String _linkUrl;
	private String _imgUrl;

	public boolean initialize(JSONObject data) {
		
		try {
			this._id = data.getString("id");
			this._imgUrl = data.getString("img");
			this._linkUrl = data.getString("link_url");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
	}

	public CAdver() {
		// TODO Auto-generated constructor stub
	}

	public CAdver(String id, String imgUrl, String linkUrl) {
		// TODO Auto-generated constructor stub
		_id = id;
		_imgUrl = imgUrl;
		_linkUrl = linkUrl;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getImgUrl() {
		return _imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this._imgUrl = imgUrl;
	}

	public String getLinkUrl() {
		return _linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this._linkUrl = linkUrl;
	}

}
