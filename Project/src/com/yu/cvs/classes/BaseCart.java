/**
 * 
 */
package com.yu.cvs.classes;

import java.util.ArrayList;

import org.json.JSONObject;

import com.yu.cvs.CApplication;
import com.yu.cvs.exception.NotLoginException;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.BaseAdapter;

/**
 * @author jason
 *
 */
public abstract class BaseCart {
	
	protected ArrayList<Good> _goodsList;
	
	protected TotalPriceCalculateListener _listener;
	
	private ArrayList<BaseAdapter> _attachAdapter;
	
	public BaseCart(){
		_goodsList = new ArrayList<Good>();
		_attachAdapter = new ArrayList<BaseAdapter>();
	}
	
	/**
	 * @return
	 */
	public ArrayList<Good> getGoods() {
		// TODO Auto-generated method stub
		return _goodsList;
	}
	
	/**
	 * @param adapter 
	 * 
	 * 记录以此购物车为数据源的适配器
	 * 
	 */
	public void registerAttachedAdapter(BaseAdapter adapter){
		_attachAdapter.add(adapter);
	}
	
	public void unregisterAttachedAdapter(BaseAdapter adapter){
		if(_attachAdapter.contains(adapter))
			_attachAdapter.remove(adapter);
	}
	
	public boolean setSelectProduct(String pid, boolean selected) {

		for (Good g : _goodsList) {
			if (pid.equals(g.getProductId())) {
				g.setSelected(selected);
				notifyGoodsListChanged();
				return true;
			}
		}

		return false;

	}
	
	/**
	 * 通知adapter购物车里的货物有变化；重新计算总价；重新统计货物数量
	 */
	public void notifyGoodsListChanged(){
		
		if(null == _attachAdapter)
			return ;
		
		for(BaseAdapter adapter : _attachAdapter){
			adapter.notifyDataSetChanged();
		}
		
		calculateTotalPrice(); 
		countSelectedGoodsInCart();
		
	}
	
	
	/**
	 * 计算货物总价
	 * @return
	 */
	public long calculateTotalPrice(){
		
		long total = 0;
		
		for(Good g : _goodsList){
			
			if(g.isSelected())
				total += g.calculateGoodPrice();
		}
		
		if(_listener!=null){
			_listener.afterCalculate(total);
		}
		
		return total;
		
	}
	
	/**
	 * 统计购物车里的货物数量
	 * @return
	 */
	public int countSelectedGoodsInCart(){
		
		int sum = 0;
		
		for(Good g : _goodsList){
			
			if(g.isSelected())
				sum += g.getCount();
			
		}
		
		if(_listener!=null){
			_listener.afterAdjustCountOfGoods(sum);
		}
		
		return sum;
		
	}

	/**
	 * 
	 */
	public void clear() {
		// TODO Auto-generated method stub
		this._goodsList.clear();
		this._attachAdapter.clear();
		this._listener = null;
	}
	
	public void setOnTotalPriceCalculateListener(TotalPriceCalculateListener listener){
		this._listener = listener;
	}
	
	public interface TotalPriceCalculateListener{
		void afterCalculate(long totalPrice);
		void afterAdjustCountOfGoods(int num);
	}
	
	
	abstract public void generateOrder() throws NotLoginException;
	abstract public AsyncTask<JSONObject, Integer, JSONObject> initializeCart(Fragment fragment);
	abstract public AsyncTask<JSONObject, Integer, JSONObject> adjustGoodQuantity(Fragment context, String pid, int quantity);
	abstract public AsyncTask<JSONObject, Integer, JSONObject> removeGoodFromCart(Fragment context, String pid);
	abstract public AsyncTask<JSONObject, Integer, JSONObject> addGoodToCart(Fragment context, Good newGood);

	/**
	 * @return
	 */
	public ArrayList<Good> getSelectedGoods() {
		// TODO Auto-generated method stub
		ArrayList<Good> selected = new ArrayList<Good>();
		
		try {
			for (Good g : _goodsList) {
				selected.add((Good) g.clone());
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return selected;
	}

//	/**
//	 * @return 
//	 * 
//	 */
//	public int countOfSelectedGoods() {
//		// TODO Auto-generated method stub
//		int sum = 0;
//		
//		for(Good g : _goodsList){
//			
//			if(g.isSelected()){
//				sum = g.getCount();
//			}
//			
//		}
//		
//		return sum;
//	}

	
}
