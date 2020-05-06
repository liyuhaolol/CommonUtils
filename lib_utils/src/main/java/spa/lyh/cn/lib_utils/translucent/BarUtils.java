package spa.lyh.cn.lib_utils.translucent;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import spa.lyh.cn.lib_utils.PixelUtils;

public class BarUtils {

    public static void autoFitStatusBar(Activity activity, int barId){
        View statusbar = activity.findViewById(barId);
        statusbar.getLayoutParams().height = PixelUtils.getStatusBarHeight(activity);
        TranslucentUtils.setTranslucentTOP(activity);
    }

    public static void autoFitNavigationBar(Activity activity, int barId){
        View navigationbar = activity.findViewById(barId);
        navigationbar.getLayoutParams().height = PixelUtils.getNavigationBarHeight(activity);
        TranslucentUtils.setTranslucentBoth(activity);
    }
}
