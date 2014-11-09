package com.yu.cvs.provider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.util.Log;

public class CvsProvider extends ContentProvider {
	
	private static final int TOP = 1;
	private static final int SECOND = 2;
	private static final int THIRD = 3;
	private static final int GOODINCART= 4;
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final HashMap<String, String> sCategoryTopProjectionMap;
	private static final HashMap<String, String> sCategorySecondProjectionMap;
	private static final HashMap<String, String> sCateforyThirdProjectionMap;
	
	private static final HashMap<String, String> sGoodInCartProjectionMap;
	
	static {
		
		sURIMatcher.addURI(CvsProviderConfig.AUTHORITY, CvsProviderConfig.CategoryTop.TABLE_NAME, TOP);
		sCategoryTopProjectionMap = new HashMap<String, String>();
		sCategoryTopProjectionMap.put(CvsProviderConfig.CategoryTop._ID,         CvsProviderConfig.CategoryTop._ID);
		sCategoryTopProjectionMap.put(CvsProviderConfig.CategoryTop.TOP_ID, CvsProviderConfig.CategoryTop.TOP_ID);
		sCategoryTopProjectionMap.put(CvsProviderConfig.CategoryTop.TOP,    CvsProviderConfig.CategoryTop.TOP);
		
		sURIMatcher.addURI(CvsProviderConfig.AUTHORITY, CvsProviderConfig.CategorySecond.TABLE_NAME, SECOND );
		sCategorySecondProjectionMap = new HashMap<String, String>();
		sCategorySecondProjectionMap.put(CvsProviderConfig.CategorySecond._ID,      CvsProviderConfig.CategorySecond._ID);
		sCategorySecondProjectionMap.put(CvsProviderConfig.CategorySecond.SECOND_ID,  CvsProviderConfig.CategorySecond.SECOND_ID);
		sCategorySecondProjectionMap.put(CvsProviderConfig.CategorySecond.SECOND,     CvsProviderConfig.CategorySecond.SECOND);
		sCategorySecondProjectionMap.put(CvsProviderConfig.CategorySecond.FATHERID, CvsProviderConfig.CategorySecond.FATHERID);
		
		sURIMatcher.addURI(CvsProviderConfig.AUTHORITY, CvsProviderConfig.CategoryThird.TABLE_NAME, THIRD );
		sCateforyThirdProjectionMap = new HashMap<String, String>();
		sCateforyThirdProjectionMap.put(CvsProviderConfig.CategoryThird._ID,      CvsProviderConfig.CategoryThird._ID);
		sCateforyThirdProjectionMap.put(CvsProviderConfig.CategoryThird.THIRD_ID,  CvsProviderConfig.CategoryThird.THIRD_ID);
		sCateforyThirdProjectionMap.put(CvsProviderConfig.CategoryThird.THIRD,     CvsProviderConfig.CategoryThird.THIRD);
		sCateforyThirdProjectionMap.put(CvsProviderConfig.CategoryThird.FATHERID, CvsProviderConfig.CategoryThird.FATHERID);
		
		sURIMatcher.addURI(CvsProviderConfig.AUTHORITY, CvsProviderConfig.GoodInCart.TABLE_NAME, GOODINCART );
		sGoodInCartProjectionMap = new HashMap<String, String>();
		sGoodInCartProjectionMap.put(CvsProviderConfig.GoodInCart._ID, CvsProviderConfig.GoodInCart._ID);
		sGoodInCartProjectionMap.put(CvsProviderConfig.GoodInCart.NAME, CvsProviderConfig.GoodInCart.NAME);
		sGoodInCartProjectionMap.put(CvsProviderConfig.GoodInCart.QUANTITY, CvsProviderConfig.GoodInCart.QUANTITY);
		sGoodInCartProjectionMap.put(CvsProviderConfig.GoodInCart.UNIT_PRICE, CvsProviderConfig.GoodInCart.UNIT_PRICE);
		sGoodInCartProjectionMap.put(CvsProviderConfig.GoodInCart.IMG, CvsProviderConfig.GoodInCart.IMG);
		
	}
	
	private static final String INSERT_SQL_FOR_GOODINCART = "INSERT INTO "
			+ CvsDatabaseHelper.Tables.GOODINCART + " ( "
			+ CvsProviderConfig.GoodInCart.PID + " , "
			+ CvsProviderConfig.GoodInCart.NAME + " , "
			+ CvsProviderConfig.GoodInCart.UNIT_PRICE + " , "
			+ CvsProviderConfig.GoodInCart.QUANTITY + " , "
			+ CvsProviderConfig.GoodInCart.IMG + " ) "
			+ "VALUES (?,?,?,?,?)";
	
	
	private CvsDatabaseHelper mDbHelper;
	private SQLiteStatement mGoodInCartInsertHelper;
	
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		final Context context = getContext();
		mDbHelper = CvsDatabaseHelper.getInstance(context);
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		mGoodInCartInsertHelper = db.compileStatement(INSERT_SQL_FOR_GOODINCART);
		return true;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		int count = 0;
		switch(sURIMatcher.match(uri)){
		
		
		
		
		
		}
		if(count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		
		return count;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		int index = 1;
		long rowId = -1;
		switch (sURIMatcher.match(uri)) {
		case GOODINCART:
			
			mGoodInCartInsertHelper.bindString(index++, values.getAsString(CvsProviderConfig.GoodInCart.PID));
			mGoodInCartInsertHelper.bindString(index++, values.getAsString(CvsProviderConfig.GoodInCart.NAME));
			mGoodInCartInsertHelper.bindLong(index++, values.getAsLong(CvsProviderConfig.GoodInCart.UNIT_PRICE));
			mGoodInCartInsertHelper.bindLong(index++, values.getAsLong(CvsProviderConfig.GoodInCart.QUANTITY));
			mGoodInCartInsertHelper.bindString(index++, values.getAsString(CvsProviderConfig.GoodInCart.IMG));
			rowId = mGoodInCartInsertHelper.executeInsert();
			
			break;
//		case TOP:
//			mPhotoInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategoryTop._ID));
//			mPhotoInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategoryTop.ORIGINAL_URL));
//			mPhotoInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategoryTop.BELONG));
//			mPhotoInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategoryTop.FATHERID));
//			rowId = mPhotoInsertHelper.executeInsert();
//			break;
//		
//		case SECOND:
//			mCourtInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategorySecond.ADDRESS));
//			mCourtInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategorySecond.CITY));
//			mCourtInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategorySecond.COURT_ID));
//			mCourtInsertHelper.bindString(index++, values.getAsString(GolfProviderConfig.CategorySecond.CREATEYEAR));
//			rowId = mCourtInsertHelper.executeInsert();
//			break;
//		case THIRD:
//			
//			
//			break;
//			
		}

		db.close();
		
		if (rowId >= 0) {
			getContext().getContentResolver().notifyChange(uri, null);
			return ContentUris.withAppendedId(uri, rowId);
		} else {
			return null;
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,	String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		Cursor c = null;
		System.out.println(uri.toString() + "|| "+ sURIMatcher.match(uri));
		switch (sURIMatcher.match(uri)) {
		case TOP:
			qb.setTables(CvsDatabaseHelper.Tables.TOP);
			qb.setProjectionMap(sCategoryTopProjectionMap);
			c = qb.query(db, projection, selection, selectionArgs, null, null,	sortOrder);
			c.setNotificationUri(getContext().getContentResolver(), CvsProviderConfig.CategoryTop.CONTENT_URI);
			break;
			
		case SECOND:
			qb.setTables(CvsDatabaseHelper.Tables.SECOND);
			qb.setProjectionMap(sCategorySecondProjectionMap);
			c = qb.query(db, projection, selection, selectionArgs, null, null,	sortOrder);
			c.setNotificationUri(getContext().getContentResolver(), CvsProviderConfig.CategorySecond.CONTENT_URI);
			break;
			
		case THIRD:
			qb.setTables(CvsDatabaseHelper.Tables.THIRD);
			qb.setProjectionMap(sCateforyThirdProjectionMap);
			c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			c.setNotificationUri(getContext().getContentResolver(), CvsProviderConfig.CategoryThird.CONTENT_URI);
			break;
			
		case GOODINCART:
			qb.setTables(CvsDatabaseHelper.Tables.GOODINCART);
			qb.setProjectionMap(sGoodInCartProjectionMap);
			c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			c.setNotificationUri(getContext().getContentResolver(), CvsProviderConfig.CategoryThird.CONTENT_URI);
			break;
			
		}

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,	String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		int count = 0 ;
		
		switch (sURIMatcher.match(uri)) {
		}
		
		if(count > 0){
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return count;
	}

}
