package com.yu.cvs.settings;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.R;
import com.yu.cvs.adapters.AddressAdapter;
import com.yu.cvs.classes.AddressBean;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.network.CThreadExecutor;
import com.yu.cvs.network.HttpCallback;
import com.yu.cvs.network.HttpRequest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CAddressListFragment extends Fragment {
	
	private ListView mAddressList;
	private AddressAdapter mAdapter;
	private ArrayList<AddressBean> _tempAddrss;
	
	private CAddressActivity mParentAcitvity;
	
	public CAddressListFragment(){
		
	}
	
	

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		mParentAcitvity = (CAddressActivity) activity;
		
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
		_tempAddrss = new ArrayList<AddressBean>();
		mAdapter = new AddressAdapter(getActivity(), _tempAddrss);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		BaseFragmentActivity a = (BaseFragmentActivity) getActivity();
		ActionBar bar = a.getSupportActionBar();
		bar.setTitle("收货地址");
		
		View root = inflater.inflate(R.layout.addresslist_fragment, null);
		mAddressList = (ListView) root.findViewById(R.id.AddressList);
		mAddressList.setAdapter(mAdapter);
		queryAddress();
		return root;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		inflater.inflate(R.menu.addresslist, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_add:
			mParentAcitvity.showAddressAddFragment();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void queryAddress() {
		// TODO Auto-generated method stub
		BaseFragmentActivity app = (BaseFragmentActivity) getActivity();
		CAccount acc = app.getAccount();
		
		JSONObject queryAddress = new JSONObject();
		
		try {
			
			JSONObject param = new JSONObject();
			param.put("userid", acc.getUserid());
			param.put("addrid", "");
			
			queryAddress.put("cmd", "user_addr_qry");
			queryAddress.put("status", 0);
			queryAddress.put("seq", 1234);
			queryAddress.put("mac", "123456");

			queryAddress.put("param", param);

			// "param":[{"area":"0890"},{"name2":"jacke"},{"page":1},{"pagecnt":31}]

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpRequest noticeQry = new HttpRequest(getActivity(), queryAddress, new HttpCallback() {

			@Override
			public void sucessData(String res) {
				// TODO Auto-generated method stub
				super.sucessData(res);

				try {
					JSONObject result = new JSONObject(res);

					JSONObject pageInfo = result.getJSONObject("pageinfo");

					if (pageInfo.getInt("records") > 0) {

						JSONArray array = result.getJSONArray("dataset");
						_tempAddrss.clear();

						for (int i = 0, length = array.length(); i < length; i++) {
							AddressBean bean = new AddressBean();
							if (bean.initialize(array.getJSONObject(i)))
								_tempAddrss.add(bean);
						}

						mAdapter.swap(_tempAddrss);
						mAddressList.setVisibility(View.VISIBLE);
						
					} else {
						mAddressList.setVisibility(View.GONE);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void faildData(int code, String res) {
				// TODO Auto-generated method stub
				super.faildData(code, res);
				mAddressList.setVisibility(View.GONE);
			}
			
		});

		CThreadExecutor.execute(noticeQry);
		
	}

}
