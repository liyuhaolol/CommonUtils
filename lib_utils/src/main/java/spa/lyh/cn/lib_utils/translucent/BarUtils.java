package spa.lyh.cn.lib_utils.translucent;

import android.app.Activity;
import android.view.View;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;

public class BarUtils {

    public static void autoFitStatusBar(Activity activity, int statusBarId){
        if (statusBarId > 0){
            View statusbar = activity.findViewById(statusBarId);
            statusbar.getLayoutParams().height = PixelUtils.getStatusBarHeight(activity);
        }
    }

    public static void autoFitNavBar(Activity activity, int navigationBarId){
        if (navigationBarId > 0){
            View navigationbar = activity.findViewById(navigationBarId);
            navigationbar.getLayoutParams().height = PixelUtils.getNavigationBarHeight(activity);
        }
    }

    public static void autoFitBothBar(Activity activity, int statusBarId, int navigationBarId){
        autoFitStatusBar(activity,statusBarId);
        autoFitNavBar(activity,navigationBarId);
    }

    public static void hideNavigationBar(Activity activity){
        AppUtils.setSystemUiVisibility(activity.getWindow().getDecorView(),View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
