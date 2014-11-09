package com.yu.cvs.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CvsDatabaseHelper extends SQLiteOpenHelper {
	static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "cvs.db";

	public CvsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public interface Tables {

		public static final String TOP = CvsProviderConfig.CategoryTop.TABLE_NAME;
		public static final String SECOND = CvsProviderConfig.CategorySecond.TABLE_NAME;
		public static final String THIRD = CvsProviderConfig.CategoryThird.TABLE_NAME;
		public static final String GOODINCART = CvsProviderConfig.GoodInCart.TABLE_NAME;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		db.execSQL("CREATE TABLE IF NOT EXISTS " + Tables.TOP + " ("
				+ CvsProviderConfig.CategoryTop._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ CvsProviderConfig.CategoryTop.TOP_ID + " INT ,"
				+ CvsProviderConfig.CategoryTop.TOP    + " TEXT )");

		db.execSQL("CREATE TABLE IF NOT EXISTS " + Tables.SECOND + " ("
				+ CvsProviderConfig.CategorySecond._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ CvsProviderConfig.CategorySecond.SECOND_ID  + " INT ,"
				+ CvsProviderConfig.CategorySecond.SECOND     + " TEXT ,"
				+ CvsProviderConfig.CategorySecond.FATHERID + " INT )");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS " + Tables.THIRD + " ("
				+ CvsProviderConfig.CategoryThird._ID	    + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ CvsProviderConfig.CategoryThird.THIRD + " TEXT ,"
				+ CvsProviderConfig.CategorySecond.FATHERID + " INT ,"
				+ CvsProviderConfig.CategoryThird.THIRD_ID  + " INT) ");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS " + Tables.GOODINCART + " ("
				+ CvsProviderConfig.GoodInCart._ID	    + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ CvsProviderConfig.GoodInCart.PID + " TEXT ,"
				+ CvsProviderConfig.GoodInCart.NAME + " TEXT ,"
				+ CvsProviderConfig.GoodInCart.UNIT_PRICE + " INT ,"
				+ CvsProviderConfig.GoodInCart.QUANTITY + " INT ,"
				+ CvsProviderConfig.GoodInCart.IMG  + " TEXT) ");
		
		db.beginTransaction();
		try {
			
			Log.d(DATABASE_NAME, "导入数据");
			
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (1, 110000, '食品酒饮');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (2, 120000, '副食粮油');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (3, 130000, '美容洗护');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (4, 140000, '家庭保洁');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (5, 150000, '家居用品');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (6, 160000, '母婴用品');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (7, 170000, '生鲜蔬菜水果');");
			db.execSQL("INSERT INTO category_top ( _id, top_id, top ) VALUES (8, 180000, '特产');");
			
//			
//			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (1,  110100, '牛奶乳品', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (2,  110200, '糖果/巧克力', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (3,  110300, '休闲食品', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (4,  110400, '坚果炒货', 0);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (5,  110500, '肉类/豆干制品', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (6,  110600, '蜜饯干果', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (7,  110700, '酒类', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (8,  110800, '饮料/水', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (9,  110900, '冲调/保健', 110000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (10, 111000, '茶叶', 110000);");
			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (11,  120100, '食用油', 120000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (12,  120200, '厨房调料', 120000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (13,  120300, '菌菇干货', 120000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (14,  120400, '下菜饭', 120000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (15,  120500, '方便面/火腿', 120000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (16,  120600, '主食', 120000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (17,  120700, '品牌', 120000);");
//			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (18,  130100, '个人洗护', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (19,  130200, '美容护肤', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (20,  130300, '口腔护理', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (21,  130400, '男士护理', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (22,  130500, '女性护理', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (23,  130600, '精致彩妆', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (24,  130700, '品牌', 130000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (25,  130800, '品牌活动', 130000);");
//			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (26,  140100, '纸制品/卫生纸', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (27,  140200, '厨房清洁', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (28,  140300, '家私/其他护理', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (29,  140400, '衣物清洗剂', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (30,  140500, '卫生间清洁', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (31,  140600, '驱虫驱蚊', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (32,  140700, '衣物护理', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (33,  140800, '客厅清洁', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (34,  140900, '清洁工具', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (35, 141000, '品牌', 140000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (36, 141100, '品牌活动', 140000);");
//			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (37,  150100, '衣物晾晒', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (38,  150200, '毛巾/内衣/袜子/家居服', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (39,  150300, '整理收纳', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (40,  150400, '床上用品/布艺软垫', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (41,  150500, '卫浴用具/配件', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (42,  150600, '一次性用品', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (43,  150700, '厨具', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (44,  150800, '餐具/水具', 150000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (45,  150900, '生活电器', 150000);");
			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (46, 160100, '婴幼儿奶粉', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (47, 160200, '母婴营养用品', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (48, 160300, '孕婴专区', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (49, 160400, '尿裤/拉拉裤/湿巾', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (50, 160500, '哺育喂养', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (51, 160600, '宝宝家纺', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (52, 160700, '宝宝辅食', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (53, 160800, '洗护清洁', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (54, 160900, '母婴小家电/童车/床', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (55, 161000, '进口母婴', 160000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (56, 161100, '品牌', 160000);");
//			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (57, 170100, '蔬菜', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (58, 170200, '水果', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (59, 170300, '进口水果', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (60, 170400, '进口蔬菜', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (61, 170500, '有机蔬菜', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (62, 170600, '有机水果', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (63, 170700, '鱼', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (64, 170800, '虾', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (65, 170900, '牛肉', 170000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (66, 171000, '家禽', 170000);");
			
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (67, 180100, '干果', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (68, 180200, '红枣', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (69, 180300, '巴旦木', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (70, 180400, '石榴', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (71, 180500, '哈密瓜', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (72, 180600, '哈密瓜干', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (73, 180700, '红薯干', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (74, 180800, '杏干', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (75, 180900, '葡萄干', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (76, 181000, '工艺品', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (77, 181100, '糕点', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (78, 181200, '馕', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (79, 181300, '圣女果', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (80, 181400, '小巴郎烤全羊', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (81, 181500, '薰衣草精油', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (82, 181600, '香薰枕头', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (83, 181700, '雪菊', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (84, 181800, '根雕', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (85, 181900, '新疆羊肉', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (86, 182000, '新疆牛肉', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (87, 182100, '阿克苏红富士苹果', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (88, 182200, '库尔勒香梨', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (89, 182300, '吐鲁番葡萄', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (90, 182400, '伊犁马肠子', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (91, 182500, '塔城风干肉', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (92, 182600, '番红素', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (93, 182700, '玉石', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (94, 182800, '奇石', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (95, 182900, '英吉沙小刀', 180000);");
			db.execSQL("INSERT INTO category_second (_id, second_id, second, father_id) VALUES (96, 183000, '羊绒披肩', 180000);");
//			
//			
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (1, 110101, '常温奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (2, 110102, '儿童奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (3, 110103, '酸奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (4, 110104, '全脂奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (5, 110105, '乳饮奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (6, 110106, '豆奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (7, 110107, '低脂奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (8, 110108, '成人奶粉', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (9, 110109, '香蕉奶', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (10, 110110, '奶酪', 110100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (11, 110111, '纯牛奶', 110100);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (12, 110201, '巧克力', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (13, 110202, '口香糖', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (14, 110203, '棒棒糖', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (15, 110204, '喜糖', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (16, 110205, '软糖', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (17, 110206, '奶糖', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (18, 110207, 'QQ糖', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (19, 110208, '果冻', 110200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (20, 110209, '其它', 110200);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (21, 110201, '饼干', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (22, 110202, '曲奇', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (23, 110203, '糕点', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (24, 110204, '蛋卷', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (25, 110205, '薯片', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (26, 110206, '肉松', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (27, 110207, '面包', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (28, 110208, '麻花', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (29, 110209, '蛋黄派', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (30, 110210, '蛋糕', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (31, 110211, '沙琪玛', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (32, 110212, '月饼', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (33, 110213, '酥', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (34, 110214, '威化', 110300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (35, 110215, '传统糕点', 110300);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (36, 110401, '瓜子', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (37, 110402, '花生', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (38, 110403, '开心果', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (39, 110404, '杏仁', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (40, 110405, '红枣', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (41, 110406, '腰果', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (42, 110407, '野生果', 110400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (43, 110408, '进口干果', 110400);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (44, 110501, '牛肉干', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (45, 110502, '鸡鸭肉', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (46, 110503, '鸭舌鸭脖', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (47, 110504, '泡椒', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (48, 110505, '猪肉类', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (49, 110506, '豆干', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (50, 110507, '海苔', 110500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (51, 110508, '锅巴', 110500);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (52, 110601, '枣类', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (53, 110602, '梅类', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (54, 110603, '葡萄干', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (55, 110604, '山楂片/卷', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (56, 110605, '无花果', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (57, 110606, '陈皮', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (58, 110607, '巴旦木', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (59, 110608, '哈密瓜干', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (60, 110609, '红薯干', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (61, 110610, '杏干', 110600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (62, 110611, '吊死干杏', 110600);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (63, 110701, '白酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (64, 110702, '红葡萄酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (65, 110703, '黄酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (66, 110704, '啤酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (67, 110705, '洋酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (68, 110706, '进口红酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (69, 110707, '气泡酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (70, 110708, '白葡萄酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (71, 110709, '保健酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (72, 110710, '鸡尾酒', 110700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (73, 110711, '酒礼盒', 110700);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (74, 110801, '矿泉水', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (75, 110802, '纯净水', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (76, 110803, '山泉水', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (77, 110804, '矿物水', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (78, 110805, '冰川水', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (79, 110806, '果蔬饮料', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (80, 110807, '可乐', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (81, 110808, '碳酸饮料', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (82, 110809, '功能饮料', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (83, 110810, '即饮咖啡', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (84, 110811, '酸梅汤', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (85, 110812, '红茶/绿茶', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (86, 110813, '苏打水', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (87, 110814, '冷饮', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (88, 110815, '蛋白植物饮料', 110800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (89, 110816, '蒸馏水', 110800);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (90, 110901, '红茶', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (91, 110902, '绿茶', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (92, 110903, '花草茶', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (93, 110904, '谷物麦片', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (94, 110905, '咖啡', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (95, 110906, '奶茶', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (96, 110907, '蜂蜜', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (97, 110908, '维生素', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (98, 110909, '氨基酸', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (99, 110910, '蛋白粉', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (100, 110911, '豆粉', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (101, 110912, '天然粉', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (102, 110913, '芝麻糊', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (103, 110914, '藕粉', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (104, 110915, '果味冲饮', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (105, 110916, '早餐冲饮', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (106, 110917, '核桃粉', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (107, 110918, '中老年冲饮', 110900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (108, 110919, '儿童冲饮', 110900);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (109, 111001, '茶礼盒', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (110, 111002, '铁观音', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (111, 111003, '大红袍', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (112, 111004, '普洱', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (113, 111005, '红茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (114, 111006, '绿茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (115, 111007, '大麦茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (116, 111008, '乌龙茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (117, 111009, '白茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (118, 111010, '土茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (119, 111011, '菊花茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (120, 111012, '花果茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (121, 111013, '茉莉花茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (122, 111014, '玫瑰茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (123, 111015, '龙井', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (124, 111016, '碧螺春', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (125, 111017, '柠檬茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (126, 111018, '柚子茶', 111000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (127, 111019, '春茶', 111000);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (128, 120101, '调和油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (129, 120102, '葵花油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (130, 120103, '玉米油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (131, 120104, '豆油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (132, 120105, '花生油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (133, 120106, '橄榄油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (134, 120107, '山茶油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (135, 120108, '菜油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (136, 120109, '核桃油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (137, 120110, '稻米油', 120100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (138, 120111, '其他', 120100);");
//
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (139, 120201, '酱油', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (140, 120202, '醋', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (141, 120203, '味精/鸡精', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (142, 120204, '红糖', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (143, 120205, '白糖', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (144, 120206, '咖喱', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (145, 120207, '花椒粉', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (146, 120208, '胡椒粉', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (147, 120209, '料酒', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (148, 120210, '老抽', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (149, 120211, '火锅料', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (150, 120212, '八角茴香', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (151, 120213, '芝麻油', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (152, 120214, '香油', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (153, 120215, '孜然', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (154, 120216, '辣椒面', 120200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (155, 120217, '盐巴', 120200);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (156, 120301, '枣类', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (157, 120302, '木耳', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (158, 120303, '食用菌', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (159, 120304, '银耳', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (160, 120305, '干菜', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (161, 120306, '海带', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (162, 120307, '紫菜', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (163, 120308, '桂圆', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (164, 120309, '龙眼', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (165, 120310, '枸杞', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (166, 120311, '虾皮', 120300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (167, 120312, '海产品', 120300);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (168, 120401, '榨菜', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (169, 120402, '萝卜干', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (170, 120403, '酱菜', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (171, 120404, '橄榄菜', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (172, 120405, '笋丝', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (173, 120406, '腐乳', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (174, 120407, '雪菜', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (175, 120408, '辣椒丝', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (176, 120409, '辣椒酱', 120400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (177, 120410, '老干妈', 120400);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (178, 120501, '牛肉味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (179, 120502, '海鲜味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (180, 120503, '麻辣味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (181, 120504, '酸菜味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (182, 120505, '鸡肉味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (183, 120506, '咖喱味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (184, 120507, '卤味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (185, 120508, '番茄味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (186, 120509, '微辣味', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (187, 120510, '肌肉火腿', 120500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (188, 120511, '牛肉火腿', 120500);");
//
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (189, 120601, '大米', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (190, 120602, '小米', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (191, 120603, '面粉', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (192, 120604, '玉米面', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (193, 120605, '绿豆', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (194, 120606, '红豆', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (195, 120607, '糯米', 120600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (196, 120608, '豆类', 120600);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (197, 120701, '金龙鱼', 120700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (198, 120702, '中粮', 120700);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (199, 130101, '洗发水', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (200, 130102, '护发素', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (201, 130103, '染发/造型', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (202, 130104, '淋浴露', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (203, 130105, '香皂', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (204, 130106, '洗手液', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (205, 130107, '私处洗液', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (206, 130108, '浴盐', 130100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (207, 130109, '进口洗护', 130100);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (208, 130201, '洗面奶', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (209, 130202, '化妆水', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (210, 130203, '乳液/面霜', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (211, 130204, '面膜', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (212, 130205, '润唇膏', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (213, 130206, '手霜', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (214, 130207, '身体乳', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (215, 130208, '面部护理套', 130200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (216, 130209, '进口护理产品', 130200);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (217, 130301, '牙膏', 130300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (218, 130302, '牙刷', 130300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (219, 130303, '漱口水', 130300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (220, 130304, '假牙清洁', 130300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (221, 130305, '牙线/牙粉', 130300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (222, 130306, '进口口腔产品', 130300);");
//
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (223, 130401, '男士控油', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (224, 130402, '男士洁面', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (225, 130403, '男士乳液/面霜', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (226, 130404, '男士爽肤水', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (227, 130405, '男士须后液', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (228, 130406, '男士洗发水', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (229, 130407, '男士私处洗液', 130400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (230, 130408, '进口护理', 130400);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (231, 130501, '卫生巾', 130500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (232, 130502, '护垫', 130500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (233, 130503, '成人纸尿裤', 130500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (234, 130504, '私处洗液', 130500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (235, 130505, '卫生湿巾', 130500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (236, 130506, '进口护理', 130500);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (237, 130601, '润唇膏', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (238, 130602, 'BB隔离霜/妆前/打底', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (239, 130603, '粉饼/腮红', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (240, 130604, '眼线/眼影/睫毛膏', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (241, 130605, '唇彩/口红', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (242, 130606, '指甲油', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (243, 130607, '化妆工具', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (244, 130608, '进口彩妆', 130600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (245, 130701, '海飞丝', 130700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (246, 130702, '黑人', 130700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (247, 130801, '摩登家族', 130800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (248, 140101, '软包抽纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (249, 140102, '盒装抽纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (250, 140103, '卷纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (251, 140104, '手帕纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (252, 140105, '厨房纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (253, 140106, '湿巾', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (254, 140107, '卫生纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (255, 140108, '平板纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (256, 140109, '湿厕纸', 140100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (257, 140201, '洗洁精', 140200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (258, 140202, '油污净', 140200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (259, 140203, '蔬果洗涤剂', 140200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (260, 140204, '橘香洗洁精', 140200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (261, 140205, '绿茶洗洁精', 140200);");
//
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (262, 140301, '地板护理', 140300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (263, 140302, '家居护理', 140300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (264, 140303, '防霉防蛀', 140300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (265, 140304, '干燥除湿剂', 140300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (266, 140305, '鞋用品', 140300);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (267, 140401, '洗衣液', 140400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (268, 140402, '洗衣粉', 140400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (269, 140403, '洗衣皂', 140400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (270, 140404, '除菌洗衣液', 140400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (271, 140405, '护色洗衣液', 140400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (272, 140406, '洗内衣', 140400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (273, 140407, '洗毛衣羽绒服', 140400);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (274, 140501, '蓝泡泡', 140500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (275, 140502, '洁厕灵', 140500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (276, 140503, '卫浴清洁', 140500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (277, 140504, '除臭芳香', 140500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (278, 140505, '管道疏通机', 140500);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (279, 140601, '蚊香片', 140600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (280, 140602, '蚊香液', 140600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (281, 140603, '驱虫气雾剂', 140600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (282, 140604, '驱蚊套装', 140600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (283, 140605, '驱蚊', 140600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (284, 140606, '杀蟑', 140600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (285, 140607, '杀螨虫', 140600);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (286, 140701, '衣物柔顺剂', 140700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (287, 140702, '衣物消毒剂', 140700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (288, 140703, '衣物除菌剂', 140700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (289, 140704, '漂白/彩漂', 140700);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (290, 140801, '洗衣机消毒', 140800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (291, 140802, '空调清洁', 140800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (292, 140803, '多用途清洁', 140800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (293, 140804, '玻璃清洁剂', 140800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (294, 140805, '冰箱除味', 140800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (295, 140806, '环境消毒', 140800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (296, 140807, '除甲醛', 140800);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (297, 140901, '抹布', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (298, 140902, '钢丝球/锅刷', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (299, 140903, '拖把', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (300, 140904, '扫把/簸箕', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (301, 140905, '家务手套', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (302, 140906, '脸盆', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (303, 140907, '垃圾桶', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (304, 140908, '玻璃清洁工具', 140900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (305, 140909, '马桶刷', 140900);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (306, 141001, '清风', 141000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (307, 141002, '维达', 141000);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (308, 141101, '抽纸集结令', 141100);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (309, 150101, '衣架/裤架', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (310, 150102, '夹子/绳', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (311, 150103, '护洗袋', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (312, 150104, '晾晒架', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (313, 150105, '衣物除尘/粘毛', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (314, 150106, '熨衣板', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (315, 150107, '晾衣篮', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (316, 150108, '脏衣篮', 150100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (317, 150109, '衣叉', 150100);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (318, 150201, '丝袜', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (319, 150202, '棉袜', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (320, 150203, '男袜', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (321, 150204, '拖鞋', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (322, 150205, '内裤', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (323, 150206, '家居服', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (324, 150207, '保暖内衣', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (325, 150208, '毛巾', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (326, 150209, '方巾', 150200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (327, 150210, '浴巾', 150200);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (328, 150301, '挂钩/粘钩', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (329, 150302, '压缩袋', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (330, 150303, '收纳袋', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (331, 150304, '收纳盒', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (332, 150305, '整理箱', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (333, 150306, '防尘罩', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (334, 150307, '收纳箱', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (335, 150308, '纸巾盒', 150300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (336, 150309, '收纳凳', 150300);");
//
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (337, 150401, '床品套件', 150400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (338, 150402, '四件套', 150400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (339, 150403, '枕头/枕芯', 150400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (340, 150404, '床垫/靠垫/抱枕', 150400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (341, 150405, '枕套枕巾', 150400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (342, 150406, '秋冬被/羊绒被', 150400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (343, 150407, '家居拖鞋', 150400);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (344, 150501, '皂盒', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (345, 150502, '马桶垫子', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (346, 150503, '地垫', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (347, 150504, '马桶刷', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (348, 150505, '浴球/浴花', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (349, 150506, '干发帽/束发带', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (350, 150507, '浴帽', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (351, 150508, '棉签/棉棒', 150500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (352, 150509, '指甲钳/修甲刀', 150500);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (353, 150601, '垃圾袋', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (354, 150602, '保鲜袋', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (355, 150603, '纸杯', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (356, 150604, '塑杯', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (357, 150605, '保鲜膜', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (358, 150606, '烧烤杯', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (359, 150607, '桌布', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (360, 150608, '手套', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (361, 150609, '鞋套', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (362, 150610, '牙签', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (363, 150611, '餐盘', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (364, 150612, '餐具', 150600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (365, 150613, '垫板', 150600);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (366, 150701, '剪刀', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (367, 150702, '菜刀', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (368, 150703, '蔬菜刀', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (369, 150704, '套刀', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (370, 150705, '砂锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (371, 150706, '奶锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (372, 150707, '炖锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (373, 150708, '蒸锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (374, 150709, '汤锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (375, 150710, '煎锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (376, 150711, '陶瓷餐具', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (377, 150712, '平锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (378, 150713, '锅铲子', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (379, 150714, '勺子', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (380, 150715, '压力锅', 150700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (381, 150716, '烘焙工具', 150700);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (382, 150801, '保温杯', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (383, 150802, '玻璃杯', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (384, 150803, '保温杯', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (385, 150804, '茶壶', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (386, 150805, '陶瓷杯', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (387, 150806, '碗碟', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (388, 150807, '筷子', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (389, 150808, '硅胶餐具', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (390, 150809, '筷笼', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (391, 150810, '保鲜盒', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (392, 150811, '密封罐', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (393, 150812, '茶具', 150800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (394, 150813, '料理盒', 150800);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (395, 150901, '剃须刀（含手动）', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (396, 150902, '电吹风', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (397, 150903, '电动牙刷', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (398, 150904, '熨斗', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (399, 150905, '加湿器', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (400, 150906, '电茶壶', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (401, 150907, '电池', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (402, 150908, '插座', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (403, 150909, '防水插板', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (404, 150910, '电话机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (405, 150911, '电饭煲', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (406, 150912, '电蒸锅', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (407, 150913, '榨汁机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (408, 150914, '绞肉机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (409, 150915, '微波炉', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (410, 150916, '电磁壶', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (411, 150917, '豆浆机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (412, 150918, '咖啡机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (413, 150919, '电烤箱', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (414, 150920, '电饼铛', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (415, 150921, '面包机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (416, 150922, '多用锅', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (417, 150923, '净水器', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (418, 150924, '电风扇', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (419, 150925, '吸尘器', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (420, 150926, '取暖器', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (421, 150927, '消毒柜', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (422, 150928, '热水器', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (423, 150929, '抽烟机', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (424, 150930, '壁挂炉', 150900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (425, 150931, '燃气灶', 150900);");
//			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (426, 160101, '1段', 160100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (427, 160102, '2段', 160100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (428, 160103, '3段', 160100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (429, 160104, '4段', 160100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (430, 160105, '荷兰原装', 160100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (431, 160106, '新西兰原装', 160100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (432, 160107, '全进口', 160100);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (433, 160201, '妈妈营养品', 160200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (434, 160202, '钙铁锌', 160200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (435, 160203, '维生素', 160200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (436, 160204, '蛋白粉', 160200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (437, 160205, 'DHA/核桃粉', 160200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (438, 160206, '益生菌', 160200);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (439, 160207, '葡萄糖', 160200);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (440, 160301, '孕产奶粉', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (441, 160302, '乳垫', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (442, 160303, '孕妇洗护', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (443, 160304, '吸奶器', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (444, 160305, '孕妇营养品', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (445, 160306, '孕妇装', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (446, 160307, '护垫卫生巾', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (447, 160308, '防护服', 160300);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (448, 160309, '孕妇裙', 160300);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (449, 160401, '出生NB', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (450, 160402, '小号S', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (451, 160403, '中号M', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (452, 160404, '大号L', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (453, 160405, '加大号XL', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (454, 160406, '加加大号XXL', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (455, 160407, '女宝宝', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (456, 160408, '男宝宝', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (457, 160409, '隔尿布/垫', 160400);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (458, 1604010, '湿巾', 160400);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (459, 160501, '奶瓶', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (460, 160502, '奶嘴/安抚奶嘴', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (461, 160503, '用具清洁', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (462, 160504, '水杯/水壶', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (463, 160505, '餐具/研磨器', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (464, 160506, '坐便器/凳', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (465, 160507, '安全防护', 160500);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (466, 160508, '奶瓶刷', 160500);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (467, 160601, '宝宝毛巾/围嘴', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (468, 160602, '宝宝浴巾', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (469, 160603, '宝宝床品', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (470, 160604, '凉席', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (471, 160605, '床垫', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (472, 160606, '床围', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (473, 160607, '抱枕', 160600);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (474, 160608, '枕头', 160600);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (475, 160701, '清火/开胃/奶伴', 160700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (476, 160702, '米粉/面条', 160700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (477, 160703, '果/蔬/肉泥', 160700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (478, 160704, '肉松/鱼松', 160700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (479, 160705, '磨牙饼干', 160700);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (480, 160706, '婴幼儿零食', 160700);");

			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (481, 160801, '洗发系列', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (482, 160802, '沐浴系列', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (483, 160803, '润肤系列', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (484, 160804, '护臀系列', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (485, 160805, '爽身防痱', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (486, 160806, '花露水/金水', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (487, 160807, '婴儿皂', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (488, 160808, '洗手液', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (489, 160809, '礼盒套装', 160800);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (490, 160810, '湿巾', 160800);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (491, 160901, '吸奶器', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (492, 160902, '宝宝理发器', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (493, 160903, '暖奶器', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (494, 160904, 'BB煲/电粥煲', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (495, 160905, '奶瓶消毒器/消毒锅', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (496, 160906, '安全座椅', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (497, 160907, '学步车', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (498, 160908, '婴儿车', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (499, 160909, '儿童自行车', 160900);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (500, 1609010, '餐椅', 160900);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (501, 161001, '奶粉', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (502, 161002, '营养品', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (503, 161003, '洗护用品', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (504, 161004, '小家电/床/车', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (505, 161005, '奶嘴奶瓶', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (506, 161006, '纸尿裤湿巾', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (507, 161007, '孕妇专区', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (508, 161008, '婴儿服饰', 161000);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (509, 161009, '玩具', 161000);");
			
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (510, 161101, '花王', 161100);");
			db.execSQL("INSERT INTO category_third (_id, third_id, third, father_id) VALUES (511, 161102, '大王', 161100);");
			
			
			db.setTransactionSuccessful();
			
			Log.d(DATABASE_NAME, "完成导入数据");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fail to initialize category's data.");
		} finally {
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (oldVersion < DATABASE_VERSION) {
			db.execSQL("DROP TABLE IF EXISTS " + Tables.TOP + ";");
			db.execSQL("DROP TABLE IF EXISTS " + Tables.SECOND + ";");
			db.execSQL("DROP TABLE IF EXISTS " + Tables.THIRD + ";");
			db.execSQL("DROP TABLE IF EXISTS " + Tables.GOODINCART + ";");
			onCreate(db);
		}
		return;
	}

	private static CvsDatabaseHelper sSingleton = null;

	public static synchronized CvsDatabaseHelper getInstance(Context context) {
		if (sSingleton == null) {
			sSingleton = new CvsDatabaseHelper(context);
		}
		return sSingleton;
	}

}
