package com.example.a13466.jlw.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tuxzx on 2018/3/13.
 */

public class SharedPreferencesUtils {

    //  存取字符串键值对
    public static void putString(Context context, String filename, String key, String value){
        SharedPreferences pref = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getString(Context context,String filename,String value,String defValue){
        SharedPreferences pref = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return pref.getString(value,defValue);
    }


}
