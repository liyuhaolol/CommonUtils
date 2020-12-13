package spa.lyh.cn.lib_utils.translucent;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public class BarUtils {

    public static void autoFitStatusBar(Activity activity, int statusBarId){
        if (statusBarId > 0){
            View statusbar = activity.findViewById(statusBarId);
            statusbar.getLayoutParams().height = PixelUtils.getStatusBarHeight(activity);
        }
    }

    public static void autoFitNavBar(final Activity activity, int navigationBarId){
        if (navigationBarId > 0){
            final View navigationbar = activity.findViewById(navigationBarId);
            PixelUtils.getNavigationBarHeight(activity, new OnNavHeightListener() {
                @Override
                public void getHeight(int height) {
                    ViewGroup.LayoutParams layoutParams = navigationbar.getLayoutParams();
                    layoutParams.height = height;
                    navigationbar.setLayoutParams(layoutParams);
                }
            });
        }
    }

    public static void autoFitBothBar(Activity activity, int statusBarId, int navigationBarId){
        autoFitStatusBar(activity,statusBarId);
        autoFitNavBar(activity,navigationBarId);
    }

    public static void hideNavigationBar(Window window){
        AppUtils.setSystemUiVisibility(window.getDecorView(),View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public static void showStatusBar(Window window){
        AppUtils.setSystemUiVisibility(window.getDecorView(),View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN,false);
    }

    public static void hideStatusBar(Window window){
        AppUtils.setSystemUiVisibility(window.getDecorView(),View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
