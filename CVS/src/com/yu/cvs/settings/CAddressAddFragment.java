package com.yu.cvs.settings;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.R;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.task.NetworkTask;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jason
 *
 */
public class CAddressAddFragment extends Fragment {

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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.addressadd_fragment, null);
		mName = (EditText) root.findViewById(R.id.AddressName);
		mPhone = (EditText) root.findViewById(R.id.AddressPhone);
		mPostCode = (EditText) root.findViewById(R.id.AddressPostcode);
		mAddress = (EditText) root.findViewById(R.id.AddressStreet);

		mProvince = (TextView) root.findViewById(R.id.AddressProvince);
		mCity = (TextView) root.findViewById(R.id.AddressCity);
		mDistrict = (TextView) root.findViewById(R.id.AddressDistrict);

		return root;
	}
	
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.address_add, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
			case R.id.action_confirm:
				if(verifyInput()){
					addAddress();
				}else{
					Toast.makeText(getActivity(), R.string.toast_login_text, Toast.LENGTH_SHORT).show();
				}
				break;	
		}
		
		
		return super.onOptionsItemSelected(item);
	}
	
	

	/**
	 * 检查用户输入的信息是否完整
	 */
	private boolean verifyInput() {
		// TODO Auto-generated method stub
		
		Drawable error = getResources().getDrawable(R.drawable.error_mark);
		
		boolean isAllRight = true;
		
		if(mName.length() == 0){
			mName.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		if(mPhone.length() == 0){
			mPhone.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mPhone.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		if(mProvince.length() == 0){
			mProvince.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mProvince.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		if(mCity.length() == 0){
			mCity.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mCity.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		if(mDistrict.length() == 0){
			mDistrict.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mDistrict.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		if(mPostCode.length() == 0){
			mPostCode.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mPostCode.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		if(mAddress.length() == 0){
			mAddress.setCompoundDrawablesWithIntrinsicBounds(null, null, error, null);
			isAllRight = false;
		}else{
			mAddress.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		
		
		return isAllRight;
	}

	/**
	 * 先服务器添加用户信息
	 */
	private void addAddress() {

		CAccount acc = mParent.getAccount();
		
		String prov = mProvince.getText().toString();
		String city = mCity.getText().toString();
		String district = mDistrict.getText().toString();
		String address = mAddress.getText().toString();
		
		String postCode = mPostCode.getText().toString();
		
		String name = mName.getText().toString();
		String isdn = mPhone.getText().toString();

		JSONObject param = new JSONObject();
		try {

			param.put("userid", acc.getUserid());
			param.put("prov", prov);
			param.put("city", city);
			param.put("citycode", "");
			param.put("district", district);
			param.put("subdistrict", "");
			param.put("address", address);
			param.put("postcode", postCode);
			param.put("uname", name);
			param.put("isdn", isdn);
			param.put("flag", "1");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		NetworkTask task = new NetworkTask((CApplication) mParent.getApplication(), "user_addr_add", false) {

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

}
