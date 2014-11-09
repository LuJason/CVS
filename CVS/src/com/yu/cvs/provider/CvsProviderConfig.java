package com.yu.cvs.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class CvsProviderConfig {
	
	public static final String AUTHORITY = "com.yu.cvs.provider";
	
	public static final class GoodInCart implements BaseColumns{
		private GoodInCart(){
			
		}
		
		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/goodincart");

		/**
		 * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.cvs.goodincart";

		/**
		 * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
		 * note.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cvs.goodincart";
		
		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = "";

		// Table name
		public static final String TABLE_NAME = "GoodInCart";
		
		public static final String PID   = "pid";
		public static final String NAME   = "name";
		public static final String UNIT_PRICE   = "unit_price";
		public static final String QUANTITY   = "quantity";
		public static final String IMG   = "img";
		
		
		
		
	}
	
	
	
	
	public static final class CategoryTop implements BaseColumns {
		private CategoryTop(){
			
		}
		
		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/category_top");

		/**
		 * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.cvs.category_top";

		/**
		 * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
		 * note.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cvs.category_top";
		
		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = "";

		// Table name
		public static final String TABLE_NAME = "category_top";

		
		// table column
		public static final String TOP_ID   = "top_id";
		public static final String TOP      = "top";
		public static final String FATHERID  = "father_id";
		
	}
	
	public static final class CategorySecond implements BaseColumns {
		private CategorySecond(){
			
		}
		
		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/category_second");

		/**
		 * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.cvs.category_second";

		/**
		 * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
		 * note.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cvs.category_second";
		
		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = "";

		// Table name
		public static final String TABLE_NAME = "category_second";

		
		// table column
		public static final String SECOND_ID   = "second_id";
		public static final String SECOND      = "second";
		public static final String FATHERID  = "father_id";
		
	}
	
	public static final class CategoryThird implements BaseColumns {
		private CategoryThird(){
			
		}
		
		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/category_third");

		/**
		 * The MIME type of {@link #CONTENT_URI} providing a directory of notes.
		 */
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.cvs.category_third";

		/**
		 * The MIME type of a {@link #CONTENT_URI} sub-directory of a single
		 * note.
		 */
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.cvs.category_third";
		
		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = "";

		// Table name
		public static final String TABLE_NAME = "category_third";

		
		// table column
		public static final String THIRD_ID   = "third_id";
		public static final String THIRD      = "third";
		public static final String FATHERID  = "father_id";
		
	}

}
