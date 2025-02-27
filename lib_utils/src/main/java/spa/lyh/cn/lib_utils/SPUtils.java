package spa.lyh.cn.lib_utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {
    private static String FILLNAME = "config";// 文件名称
    private static SharedPreferences mSharedPreferences = null;

    /**
     * 单例模式
     */
    public static synchronized SharedPreferences getInstance(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getApplicationContext().getSharedPreferences(FILLNAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    /**
     * SharedPreferences常用的10个操作方法
     */
    public static void putBoolean(String key, boolean value, Context context) {
        SPUtils.getInstance(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue, Context context) {
        return SPUtils.getInstance(context).getBoolean(key, defValue);
    }

    public static void putString(String key, String value, Context context) {
        SPUtils.getInstance(context).edit().putString(key, value).apply();
    }

    public static String getString(String key, String defValue, Context context) {
        return SPUtils.getInstance(context).getString(key, defValue);
    }

    public static void putInt(String key, int value, Context context) {
        SPUtils.getInstance(context).edit().putInt(key, value).apply();
    }

    public static int getInt(String key, int defValue, Context context) {
        return SPUtils.getInstance(context).getInt(key, defValue);
    }

    public static void putLong(String key, long value, Context context) {
        SPUtils.getInstance(context).edit().putLong(key, value).apply();
    }

    public static long getLong(String key, long defValue, Context context) {
        return SPUtils.getInstance(context).getLong(key, defValue);
    }

    public static void putFloat(String key, float value, Context context) {
        SPUtils.getInstance(context).edit().putFloat(key, value).apply();
    }

    public static float getFloat(String key, float defValue, Context context) {
        return SPUtils.getInstance(context).getFloat(key, defValue);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(String key, Context context) {
        SPUtils.getInstance(context).edit().remove(key).apply();
    }

    /**
     * 清除所有内容
     */
    public static void clear(Context context) {
        SPUtils.getInstance(context).edit().clear().apply();
    }
}
