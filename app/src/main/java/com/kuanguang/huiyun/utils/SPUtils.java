package com.kuanguang.huiyun.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/*----------------------------------------------------------------
// 文件名：SharedPreferencesUtils
// 文件功能描述： 读写SharedPreferences 文件的工具类
//
//
// 创建标识：wankun20141017
//
// 修改标识：
// 修改描述：
//
// 修改标识：
// 修改描述：
//----------------------------------------------------------------*/

public class SPUtils {

	public static String SP_NAME = "config";
	private static SharedPreferences sp;

	public static void saveBoolean(Context context, String key, boolean value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getBoolean(key, defValue);
	}
	
	public static void saveInt(Context context, String key, int value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(Context context, String key,
			int defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getInt(key, defValue);
	}

	public static void saveString(Context context, String key, String value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context context, String key, String defValue) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);

		}
		return sp.getString(key, defValue);
	}
	
	/**
	 * 将类对象转为json存在SharedPreferences中
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBean(Context context, String key, Object value) {
		if (sp == null)
			sp = context.getSharedPreferences(SP_NAME, 0);
		Gson gson = new Gson();
		String json =	gson.toJson(value);
		sp.edit().putString(key, json).commit();
	}

	/**
	 * 将SharedPreferences中的String装换为类对象取出来
	 * （注意：有可能报错,返回的对象是null）
	 * @param context
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static Object getBean(Context context, String key, Class clazz) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, 0);
		}
		Gson gson = new Gson();
		Object obj=null;
		try {
			obj = gson.fromJson(sp.getString(key, ""), clazz);
		}catch(Exception e){
			LogUtil.E("json转换失败"+e.toString());
		};
		return obj;
	}


}
