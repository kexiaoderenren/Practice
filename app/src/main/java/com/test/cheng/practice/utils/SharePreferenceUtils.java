package com.test.cheng.practice.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * 默认SharePreference工具类（推荐使用）
 * Created by kexiaoderenren on 2017/1/19.
 */
public class SharePreferenceUtils {


    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putString(Context context, String key, String values) {
        SharedPreferences.Editor editor =  getSharedPreferences(context).edit();
        editor.putString(key, values);
        editor.commit();
    }

    public static void putInt(Context context, String key, int values) {
        SharedPreferences.Editor editor =  getSharedPreferences(context).edit();
        editor.putInt(key, values);
        editor.commit();
    }

    public static void putBoolean(Context context, String key, boolean values) {
        SharedPreferences.Editor editor =  getSharedPreferences(context).edit();
        editor.putBoolean(key, values);
        editor.commit();
    }

    public static String getString(Context context, String key, String defaultValues) {
        return getSharedPreferences(context).getString(key, defaultValues);
    }

    public static int getInt(Context context, String key, int defaultValues) {
        return getSharedPreferences(context).getInt(key, defaultValues);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValues) {
        return getSharedPreferences(context).getBoolean(key, defaultValues);
    }

    /**
     * 使用SharedPreferences保存对象；
     * @param context
     * @param key
     * @param object
     */
    public static void putObject(Context context, String key, String object) {
        SharedPreferences.Editor editor =  getSharedPreferences(context).edit();
        editor.putString(key, object);
        editor.commit();
    }

    /**
     * 获取SharedPreferences中保存的对象；
     * @param context
     * @param key
     * @param defaultValues
     * @return
     */
    public static String getObject(Context context, String key, String defaultValues)  {
        return getSharedPreferences(context).getString(key, defaultValues);
    }

    /**
     * 获取SharedPreferences中保存的对象；
     * @param context
     * @param key
     * @param cls
     * @return
     */
    public static Object getObject(Context context, String key, Class cls) {
        return new Gson().fromJson(getSharedPreferences(context).getString(key, ""), cls) ;
    }


    /**
     * 清空sharePreference数据
     * @param context
     */
    public static void clearSPFile(Context context) {
        SharedPreferences.Editor editor =  getSharedPreferences(context).edit();
        editor.clear().commit();
    }


}
