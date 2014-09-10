/**
 * 
 */
package com.yu.cvs.shop;

import com.yu.cvs.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * @author jason
 * 
 * 用户已经登录
 *
 */
public class CartEmptyFragment extends Fragment {
	
	private Button mGoShopping;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,	@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.cart_empt_fragment, null);
		
		mGoShopping = (Button) root.findViewById(R.id.GoShopping);
		
		return root;
	}
	

}
