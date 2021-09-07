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
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;

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
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale);
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
        boolean hasMenuKey = ViewConfiguration.get(activity).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
        if(hasMenuKey && hasBackKey) {
            //存在物理按键，把高度改为0
            height = 0;
        }else {
            //没有物理按键
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
