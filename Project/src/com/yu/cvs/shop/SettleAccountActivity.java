/**
 * 
 */
package com.yu.cvs.shop;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yu.cvs.BaseFragmentActivity;
import com.yu.cvs.CApplication;
import com.yu.cvs.R;
import com.yu.cvs.adapters.OrderGoodsListAdapter;
import com.yu.cvs.classes.AddressBean;
import com.yu.cvs.classes.BaseCart;
import com.yu.cvs.classes.CAccount;
import com.yu.cvs.classes.Good;
import com.yu.cvs.classes.BaseCart.TotalPriceCalculateListener;
import com.yu.cvs.task.NetworkTask;

/**
 * @author jason
 *
 */
public class SettleAccountActivity extends BaseFragmentActivity implements OnClickListener {
	
	private CAccount acc ;

	private ArrayList<Good> _goods ;
	
	private ArrayList<AddressBean> _addresses;
	
	private ScrollView mScroller;
	
	private ListView mGoods;
	
	private OrderGoodsListAdapter mAdapter;
	
	private TextView mName, mPhone, mAddress, mNoAddress; // Address
	
	private RelativeLayout mAddressLayout;
	
	private TextView mPayMethod; // Payment
	
	private TextView mInvoiceType, mInvoiceHeader;
	
	private TextView mSettleAccountPrice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settle_account_activity);
		
		ActionBar bar = getSupportActionBar();
		bar.setTitle(R.string.SettleAccount);
		
		_addresses = new ArrayList<AddressBean>();
		
		mName = (TextView) findViewById(R.id.Name);
		mPhone = (TextView) findViewById(R.id.Phone);
		mAddress = (TextView) findViewById(R.id.Address);
		mAddressLayout = (RelativeLayout) findViewById(R.id.AddressLayout);
		mNoAddress = (TextView) findViewById(R.id.NoAddress);
		mSettleAccountPrice = (TextView) findViewById(R.id.SettleAccountPrice);
		mScroller = (ScrollView) findViewById(R.id.SettleScroller);
		
		mPayMethod = (TextView) findViewById(R.id.Method);
		mInvoiceType = (TextView) findViewById(R.id.Type);
		mInvoiceHeader = (TextView) findViewById(R.id.Header);
		
		acc = getAccount();
		
//		initialize goods
		
		BaseCart cart = getAccount().getCart();
		
		_goods = cart.getSelectedGoods();
		
		mGoods = (ListView) findViewById(R.id.OrderGoods);
		
		mAdapter = new OrderGoodsListAdapter(this, _goods);
		
		mGoods.setAdapter(mAdapter);
		
		setListViewHeightBasedOnChildren(mGoods);
		
//		initialize Address
		
		queryAddress();
		
//		initialize invoice
		
		mPayMethod = (TextView) findViewById(R.id.Method);
		
		mPayMethod.setOnClickListener(this);
		
//		initialize price
		
		calculatePriceAndCount();
		
	}
	
	/**
	 * 
	 */
	private void calculatePriceAndCount() {
		// TODO Auto-generated method stub
		int totalPrice = 0;
		int count = 0;
		
		if(_goods != null){
		
			for(Good g : _goods){
				totalPrice += g.getPrice() * g.getCount();
				count += g.getCount();
			}
		}
		
		mSettleAccountPrice.setText(String.format("总金额:%.2f元",(float)totalPrice / 100));
		
		
	}

	private void setListViewHeightBasedOnChildren(ListView listView) {   
        // 获取ListView对应的Adapter   
		OrderGoodsListAdapter listAdapter = (OrderGoodsListAdapter) listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);
        mScroller.smoothScrollTo(0, 0);
        
    }
	
	private void queryAddress() {
		
		JSONObject param = new JSONObject();
		
		try {
			
			param.put("userid", acc.getUserid());
			param.put("addrid", "");

			// "param":[{"area":"0890"},{"name2":"jacke"},{"page":1},{"pagecnt":31}]

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetworkTask task = new NetworkTask((CApplication) getApplication(), "user_addr_qry", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject result = new JSONObject(str);
					
					int status = result.getInt("status");
					
					switch(status){
					
					case 0:
						JSONObject data = result.getJSONObject("result");
						
//						JSONObject pageinfo = data.getJSONObject("pageinfo");
						
						JSONArray array = data.getJSONArray("dataset");
						_addresses.clear();

						for (int i = 0, length = array.length(); i < length; i++) {
							AddressBean bean = new AddressBean();
							if (bean.initialize(array.getJSONObject(i)))
								_addresses.add(bean);
						}
						
						for(AddressBean b : _addresses){
							
							if(b.isDefAdd()){
								mName.setText(b.getUname());
								mPhone.setText(b.getIsdn());
								mAddress.setText(b.getFullAddrss());
							}
							
						}
						
						break;
					case 91:// NoData
						
						mAddressLayout.setVisibility(View.GONE);
						
						mNoAddress.setVisibility(View.VISIBLE);
						
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

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.Method:
			
			
			break;
			
		case R.id.SettleAccount:
			
			generateOrder();
			
			break;
		}
		
	}
	
	private void generateOrder() {
		// TODO Auto-generated method stub
		
//		userid	用户名
//		pid_str	商品id的组合字符串，|分割
//		cnt_str	商品数量组合字串，|分割，与pid_str对应
//		price_str	商品对应的实付款单价，格式  ：价格 邮费|价格2 邮费2|价格N 邮费N
//		invoice	发票信息，为空的时候用默认发票信息
//		message	买家留言，总的留言，可空
//		memos	买家备注，总的备注可空
//		address_id	用户地址id（空表示自提）
//		payway_id	支付方式id
		
		StringBuilder pidBuilder = new StringBuilder();
		StringBuilder cntBuilder = new StringBuilder();
		StringBuilder priceBuilder = new StringBuilder();
		
		for(Good g : _goods){
			
			pidBuilder.append(g.getProductId()).append("|");
			cntBuilder.append(g.getCount()).append("|");
			
		}
		
		JSONObject param = new JSONObject();

		try {
			
			param.put("userid", acc.getUserid());
			param.put("pid_str", pidBuilder.deleteCharAt(pidBuilder.length()-1));
			param.put("cnt_str", cntBuilder.deleteCharAt(cntBuilder.length()-1));
			param.put("price_str", "");
			param.put("invoice", "");
			param.put("message", "");
			param.put("memos", "");
			param.put("address_id", "");
			param.put("payway_id", "");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		NetworkTask task = new NetworkTask((CApplication) getApplication(), "user_addr_qry", false){

			@Override
			protected void responseData(String str, boolean isBackground) {
				// TODO Auto-generated method stub
				super.responseData(str, isBackground);
				
				try {
					JSONObject result = new JSONObject(str);
					
					int status = result.getInt("status");
					
					switch(status){
					
					case 0:
						break;
					case 1:
						break;
				
				
					}
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		};
		
		task.execute(param);
		
	}

}
