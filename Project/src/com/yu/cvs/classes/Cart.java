package com.yu.cvs.classes;

import java.util.ArrayList;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.BaseAdapter;

import com.yu.cvs.BaseFragmentActivity;

public abstract class Cart {
	
//	protected ArrayList<Good> _goodsList;
//	
//	protected TotalPriceCalculateListener _listener;
//	
//	private ArrayList<BaseAdapter> _attachAdapter;
//	
//	public Cart(){
//		_goodsList = new ArrayList<Good>();
//	}
//	
//	public boolean setSelectProduct(String pid, boolean selected){
//		
//		for(Good g: _goodsList){
//			if(pid.equals(g.getProductId())){
//				g.setSelected(selected);
//				notifyGoodsListChanged();
//				return true;
//			}
//		}
//
//		return false;
//		
//	}
//	
//	/**
//	 * 获取货物列表
//	 * 
//	 * @return goodList；
//	 */
//	public ArrayList<Good> getGoods(){
//		return _goodsList;
//	}
//	
//	/**
//	 * @param adapter 
//	 * 
//	 * 记录以此购物车为数据源的适配器
//	 * 
//	 */
//	public void setAttachedAdapter(BaseAdapter adapter){
//		
//		if( null == _attachAdapter){
//			_attachAdapter = new ArrayList<BaseAdapter>();
//		}
//		
//		_attachAdapter.add(adapter);
//		
//	}
//	
//	/**
//	 * 通知adapter购物车里的货物有变化；重新计算总价；重新统计货物数量
//	 */
//	protected void notifyGoodsListChanged(){
//		
//		if(null == _attachAdapter)
//			return ;
//		
//		for(BaseAdapter adapter : _attachAdapter){
//			adapter.notifyDataSetChanged();
//		}
//		
//		calculateTotalPrice(); 
//		countGoodsInCart();
//		
//	}
//	
//	
//	/**
//	 * 计算货物总价
//	 * @return
//	 */
//	public long calculateTotalPrice(){
//		
//		long total = 0;
//		
//		for(Good g : _goodsList){
//			
//			if(g.isSelected())
//				total += g.calculateGoodPrice();
//		}
//		
//		if(_listener!=null){
//			_listener.afterCalculate(total);
//		}
//		
//		return total;
//		
//	}
//	
//	/**
//	 * 统计购物车里的货物数量
//	 * @return
//	 */
//	public int countGoodsInCart(){
//		
//		int sum = 0;
//		
//		for(Good g : _goodsList){
//			
//			if(g.isSelected())
//				sum += g.getCount();
//			
//		}
//		
//		if(_listener!=null){
//			_listener.afterAdjustCountOfGoods(sum);
//		}
//		
//		return sum;
//		
//	}
//	
//	public void setOnTotalPriceCalculateListener(TotalPriceCalculateListener listener){
//		this._listener = listener;
//	}
//	
//	public void addGood(Good newGood){
//		
//		String pid = newGood.getProductId();
//		
//		boolean findGood = false;
//		
//		for(Good g: _goodsList){
//			
//			if(pid.equals(g.getProductId())){
//				findGood = true;
//				g.setCount(g.getCount() + newGood.getCount());
//				break;
//			}
//			
//		}
//		
//		if(!findGood)
//			_goodsList.add(newGood);
//		
////		notifyGoodsListChanged();
//		
//	}
//	
//	
//	/**
//	 * @param position
//	 */
//	public boolean removeGood(String pid) {
//		// TODO Auto-generated method stub
//		Good toDel = null;
//		
//		for(Good g: _goodsList){
//			if(pid.equals(g.getProductId())){
//				toDel = g;
//				break;
//			}
//		}
//		
//		if(toDel == null){
//			return false;
//		}else{
//			_goodsList.remove(toDel);
//			notifyGoodsListChanged();// 通知购物车里的货物已经发生变化
//			return true;
//		}
//		
//	}
//
//	/**
//	 * 
//	 */
//	public void clear() {
//		// TODO Auto-generated method stub
//		this._goodsList.clear();
//		this._listener = null;
//	}
//	
//	public interface TotalPriceCalculateListener{
//		void afterCalculate(long totalPrice);
//		void afterAdjustCountOfGoods(int num);
//	}
//	
//	abstract public void generateOrder();
//	abstract public AsyncTask<JSONObject, Integer, JSONObject> initializeCart(BaseFragmentActivity context);
//	abstract public AsyncTask<JSONObject, Integer, JSONObject> adjustGoodQuantity(BaseFragmentActivity context, String pid, int quantity);
	
}
