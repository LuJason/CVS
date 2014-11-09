package com.yu.cvs;

import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.yu.cvs.classes.CAccount;

import android.app.Application;

public class CApplication extends Application{
	
	private CAccount _account;
	
	
	public CAccount getAccount(){
		
		if(null == _account)
			_account = new CAccount();
		
		return _account;
	}
	
	
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	

	@Override
	public void onCreate() {
		super.onCreate();
		mLocationClient = new LocationClient(this);
		mGeofenceClient = new GeofenceClient(this);
		
	}
//	
//	public class MyLocationListener implements BDLocationListener {
//
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//			//Receive Location 
//			StringBuffer sb = new StringBuffer(256);
//			sb.append("time : ");
//			sb.append(location.getTime());
//			sb.append("\nerror code : ");
//			sb.append(location.getLocType());
//			sb.append("\nlatitude : ");
//			sb.append(location.getLatitude());
//			sb.append("\nlontitude : ");
//			sb.append(location.getLongitude());
//			sb.append("\nradius : ");
//			sb.append(location.getRadius());
//			if (location.getLocType() == BDLocation.TypeGpsLocation){
//				sb.append("\nspeed : ");
//				sb.append(location.getSpeed());
//				sb.append("\nsatellite : ");
//				sb.append(location.getSatelliteNumber());
//				sb.append("\ndirection : ");
//				sb.append(location.getDirection());
//			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//				sb.append("\naddr : ");
//				sb.append(location.getAddrStr());
//				//ÔËÓªÉÌÐÅÏ¢
//				sb.append("\noperationers : ");
//				sb.append(location.getOperators());
//			}
//			
//			Log.i("BaiduLocationApiDem", sb.toString());
//		}
//
//
//		@Override
//		public void onReceivePoi(BDLocation arg0) {
//			// TODO Auto-generated method stub
//			
//		}
//	}
	
}
