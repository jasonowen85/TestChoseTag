package com.small.tag.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.small.tag.App;

/**
 * Created by jasonwen on 2017/3/29.
 */
public class SharePreferenceUtils {
    private final static String  FILENAME ="test_app";

    public static void putString(String key, String vaule){
        SharedPreferences sp =App.getContext().
                getSharedPreferences(SharePreferenceUtils.FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, vaule);
        edit.apply();
    }

    public static String getString(String key, String defaultVaule){
        Context context = App.getContext();
        SharedPreferences sp = App.getContext().
                getSharedPreferences(SharePreferenceUtils.FILENAME, Context.MODE_PRIVATE);
        return sp.getString(key,defaultVaule);
    }


}
