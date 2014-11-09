package com.yu.cvs.adapters;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.R;
import com.yu.cvs.classes.AddressBean;
import com.yu.cvs.settings.CAddressActivity;
import com.yu.cvs.task.NetworkTask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter implements OnClickListener {
	
	private ArrayList<AddressBean> _addresses;
	private LayoutInflater _inflater;
	private CAddressActivity _activity;
	
	public AddressAdapter(Context context, ArrayList<AddressBean> addresses){
		
		_activity = (CAddressActivity) context;
		_inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		_addresses = new ArrayList<AddressBean>();
		
		if(addresses != null){
			_addresses.addAll(addresses);
		}
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _addresses.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _addresses.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v;
		ViewHolder holder;
		if(convertView == null){
			v = _inflater.inflate(R.layout.addresslist_item, null);
			holder = new ViewHolder();

			holder._address = (TextView) v.findViewById(R.id.Address);
			holder._name = (TextView) v.findViewById(R.id.AddressName);
			holder._tel = (TextView) v.findViewById(R.id.AddressTel);
			holder._del = (LinearLayout) v.findViewById(R.id.AddressEdit);
			holder._edit = (LinearLayout) v.findViewById(R.id.AddressDelete);
			holder._default = (ImageView) v.findViewById(R.id.AddressDefault);
			
			holder._del.setOnClickListener(this);
			holder._edit.setOnClickListener(this);
			
			v.setTag(holder);
		}else{
			v = convertView;
			holder = (ViewHolder) v.getTag();
		}
		
		AddressBean bean = _addresses.get(position);
		
		holder._del.setTag(position);
		holder._edit.setTag(position);
		
		holder._name.setText(bean.getUname());
		holder._tel.setText(bean.getIsdn());
//		StringBuilder builder = new StringBuilder();
		holder._address.setText(String.format("%s%s%s%s", bean.getProvince(), bean.getCity(), bean.getDistrict(), bean.getAddress()));
		if(bean.isDefAdd()){
			holder._default.setImageResource(R.drawable.address_chosen);
		}else{
			holder._default.setImageResource(R.drawable.address_normal);
		}
		
		return v;
	}

	public void swap(ArrayList<AddressBean> _tempAddrss) {
		// TODO Auto-generated method stub
		_addresses.clear();
		if(_tempAddrss!=null){
			_addresses.addAll(_tempAddrss);
		}
		notifyDataSetChanged();
	}
	
	private class ViewHolder{
		public TextView _name;
		public TextView _tel;
		public TextView _address;
		public LinearLayout _edit;
		public LinearLayout _del;
		public ImageView _default;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.AddressDelete:
		{
			final int pos = (Integer) v.getTag();
			AddressBean bean = _addresses.get(pos);
			
			JSONObject param = new JSONObject();
			try {

				param.put("addrid", bean.getId());
				
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			NetworkTask task = new NetworkTask((CApplication) _activity.getApplication(), "user_addr_del",false) {

				@Override
				protected void responseData(String str, boolean isBackground) {
					// TODO Auto-generated method stub
					super.responseData(str, isBackground);
					
					try {
						JSONObject result = new JSONObject(str);
						
						int status = result.getInt("status");
						
						switch(status){
						
						case 0:
							
							_addresses.remove(pos);
							notifyDataSetChanged();
							
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
			break;
			
		case R.id.AddressEdit:
		{	
			int pos = (Integer) v.getTag();
			AddressBean bean = _addresses.get(pos);
			_activity.showAddressEditFragment(bean.getId());
		}	
			break;
		
		}
	}

}
