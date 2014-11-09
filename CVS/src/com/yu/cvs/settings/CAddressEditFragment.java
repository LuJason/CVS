package com.yu.cvs.settings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.R;
import com.yu.cvs.classes.AddressBean;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.task.NetworkTask;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CAddressEditFragment extends Fragment {
	
	public static String KEY_ADDRESS_ID = "address_id";
	
	private String _addressid;
	
	private EditText mName, mPhone, mPostCode, mAddress;
	private TextView mProvince, mCity, mDistrict;

	private CAddressActivity mParent;

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		mParent = (CAddressActivity) activity;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		_addressid = getArguments().getString(KEY_ADDRESS_ID);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.addressadd_fragment, null);
		
		mName = (EditText) root.findViewById(R.id.AddressName);
		mPhone = (EditText) root.findViewById(R.id.AddressPhone);
		mPostCode = (EditText) root.findViewById(R.id.AddressPostcode);
		mAddress = (EditText) root.findViewById(R.id.AddressStreet);

		mProvince = (TextView) root.findViewById(R.id.AddressProvince);
		mCity = (TextView) root.findViewById(R.id.AddressCity);
		mDistrict = (TextView) root.findViewById(R.id.AddressDistrict);
		
		queryAddress();
		
		return root;
	}
	
	
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.address_edit, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_save:
			updateAddress();
			break;
		}
		
		
		return super.onOptionsItemSelected(item);
	}

	private void updateAddress(){
		
		String prov = mProvince.getText().toString();
		String city = mCity.getText().toString();
		String district = mDistrict.getText().toString();
		String address = mAddress.getText().toString();
		
		String postCode = mPostCode.getText().toString();
		
		String name = mName.getText().toString();
		String isdn = mPhone.getText().toString();
		
		
		JSONObject param = new JSONObject();
		
		try {
			
			param.put("addrid", _addressid);
			param.put("prov", prov);
			param.put("city", city);
			param.put("citycode", "");
			param.put("district", district);
			param.put("subdistrict", "");
			param.put("address", address);
			param.put("postcode", postCode);
			param.put("uname", name);
			param.put("isdn", isdn);
			param.put("flag", "0");
			


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		NetworkTask task = new NetworkTask((CApplication) mParent.getApplication(), "user_addr_qry", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject result = new JSONObject(str);
					
					int status = result.getInt("status");
					
					switch(status){
					
					case 0:
						
						mParent.showAddressListFragment();
						
						break;
						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		};
		
		task.execute(param);
	}

	private void queryAddress() {
		// TODO Auto-generated method stub
		
		CAccount acc = mParent.getAccount();
		
		JSONObject param = new JSONObject();
		
		try {
			
			param.put("userid", acc.getUserid());
			param.put("addrid", _addressid);


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetworkTask task = new NetworkTask((CApplication) mParent.getApplication(), "user_addr_qry", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				
				try {
					JSONObject result = new JSONObject(str);
					
					int status = result.getInt("status");
					
					switch(status){
					
					case 0:
						
						JSONArray data = result.getJSONObject("result").getJSONArray("dataset");
						
						if(data.length() > 0){
							
							AddressBean bean = new AddressBean();
							bean.initialize(data.getJSONObject(0));
							
							mName.setText(bean.getUname());
							mPhone.setText(bean.getIsdn());
							
							mProvince.setText(bean.getProvince());
							mCity.setText(bean.getCity());
							mDistrict.setText(bean.getDistrict());
							mPostCode.setText(bean.getPostCode());
							mAddress.setText(bean.getAddress());
							
						}
						
						break;
						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		};
		
		task.execute(param);
		
	}

}
