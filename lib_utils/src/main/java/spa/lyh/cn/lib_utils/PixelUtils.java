package spa.lyh.cn.lib_utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import java.lang.reflect.Method;

import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public class PixelUtils {
    /**
     * dp转px
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }

    /**
     * px转dp
     * @param context 上下文
     * @param pxValue px值
     * @return dp值
     */
    public static float px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (float) pxValue / scale;
    }

    /**
     * 用于获取状态栏的高度。 使用Resource对象获取
     *
     * @param activity 上下文
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 用于获取导航栏的高度。 使用Resource对象获取
     *
     * @param activity 上下文
     * @return 返回状态栏高度的像素值。
     */
    public static void getNavigationBarHeight(final Activity activity, final OnNavHeightListener listener) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        boolean canDo = true;
        boolean hasMenuKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_MENU);
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        boolean hasHomeKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_HOME);

        boolean doSecondPart = false;//是否执行第二种判断逻辑
        if(hasMenuKey || hasBackKey || hasHomeKey) {
            //存在物理按键
            boolean hasNavigationBar = false;//初始不存在导航栏
            //这里要再次判断是否存在虚拟按键，因为有的手机，没有物理按键，他也会判断存在物理按键
            int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = resources.getBoolean(id);
            }
            if (!hasNavigationBar){
                //第一种方式判断没有导航栏，继续判断
                try {
                    Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
                    Method m = systemPropertiesClass.getMethod("get", String.class);
                    String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
                    if ("1".equals(navBarOverride)) {
                        hasNavigationBar = false;
                    } else{
                        hasNavigationBar = true;
                    }
                } catch (Exception e) {
                }
            }

            if (hasNavigationBar){
                //判断存在导航栏，与最初的判断冲突
                doSecondPart = true;
            }else {
                //判断确实没有导航栏，将高度改为0
                height = 0;
            }
        }else {
            //没有物理按键
            doSecondPart = true;
        }

        if (doSecondPart){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //Android8.0以上才存在所谓全面屏手势
                canDo = false;
                activity.getWindow().getDecorView().post(new Runnable() {
                    @Override
                    public void run() {
                        if (activity.getWindow().getDecorView().getRootWindowInsets() != null){
                            if (listener != null){
                                int height = activity.getWindow().getDecorView().getRootWindowInsets().getStableInsetBottom();
                                listener.getHeight(height,getNavBarType(activity,height));
                            }
                        }
                    }
                });
            }else {
                //进到这里，说明，判断没有物理key，还不是android8.0
                //进行可用区域判断，辅助判断，并不是太靠谱
                Point size = new Point();
                Point realSize = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(size);
                activity.getWindowManager().getDefaultDisplay().getRealSize(realSize);
                if (realSize.equals(size)) {
                    //两个尺寸相等，说明没有导航栏
                    height = 0;
                }
            }
        }


        if (canDo && listener != null){
            listener.getHeight(height,getNavBarType(activity,height));
        }
    }

    private static int getNavBarType(Activity activity,float navHeight){
        //获取屏幕高像素
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        float deviceHeight = metrics.heightPixels;
        float result = navHeight / deviceHeight;
        if (result == 0){
            return BarUtils.NO_NAVIGATION;
        }else if (result > 0 && result <= 0.03){
            return BarUtils.GESTURE_NAVIGATION;
        }else {
            return BarUtils.NORMAL_NAVIGATION;
        }
    }
}
