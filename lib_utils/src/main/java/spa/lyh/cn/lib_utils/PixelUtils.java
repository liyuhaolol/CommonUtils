package spa.lyh.cn.lib_utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;

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
    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Point size = new Point();
        Point realSize = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        activity.getWindowManager().getDefaultDisplay().getRealSize(realSize);
        if (realSize.equals(size)) {
           height = 0;
        } else {
            size.y = size.y + height;
            if (realSize.y < size.y){
                height = 0;
            }
        }
        return height;
    }
}
