package com.himawari.permissionUtils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


public class SharePerferenceUtils{

    /**
     * 删除name表格保存的所有数据
     * @param context
     * @param name
     */
    public static void clearAll(Context context,String name,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,mode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
    }

    /**
     * 保存数据objs到name
     * @param context
     * @param name
     * @param objs
     */
    public static void keepObject(Context context,String name, Map<String,Object> objs,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,mode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(String keyStr:objs.keySet()){
            Object value = objs.get(keyStr);
            if(value instanceof String){
                editor.putString(keyStr,(String)value);
            }else if(value instanceof Float){
                editor.putFloat(keyStr,(Float)value);
            }else if(value instanceof Integer){
                editor.putInt(keyStr,(Integer)value);
            }else if(value instanceof Long){
                editor.putLong(keyStr,(Long)value);
            }else if(value instanceof Boolean){
                editor.putBoolean(keyStr,(Boolean)value);
            }
        }
        editor.commit();
    }


    /**
     * 从name移除指定keys数据
     * @param context
     * @param name
     * @param keys
     */
    public static void removeObject(Context context, String name,int mode, String... keys){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String keyStr:keys)editor.remove(keyStr);
        editor.commit();
    }

    /**
     * 获取name所有key值和key值对应value
     * @param context
     * @param name
     * @return
     */
    public static String getAllKeyValues(Context context,String name,int mode){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,mode);
        Map<String,Object> maps = (Map<String, Object>) sharedPreferences.getAll();
        String str = "";
        for(String keyStr:maps.keySet())
            str = str + " key:"+keyStr+" value:"+maps.get(keyStr)+"\n";

        return str;
    }


    /**
     * 根据key获取value
     * @param context
     * @param name
     * @param mode
     * @param key
     * @return
     */
    public static String getValue(Context context,String name,int mode,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,mode);
        Map<String,Object> maps = (Map<String, Object>) sharedPreferences.getAll();
        return ""+maps.get(key);
    }
}
