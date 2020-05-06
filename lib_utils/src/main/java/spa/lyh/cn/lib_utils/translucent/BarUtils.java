package spa.lyh.cn.lib_utils.translucent;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

public class BarUtils {

    public static void autoFitStatusBar(Activity activity, int barId, ViewGroup.LayoutParams layoutParams){
        View statusbar = activity.findViewById(barId);
        statusbar.setLayoutParams(layoutParams);
        TranslucentUtils.setTranslucentTOP(activity);
    }

    public static void autoFitNavigationBar(Activity activity, int barId, ViewGroup.LayoutParams layoutParams){
        View navigationbar = activity.findViewById(barId);
        navigationbar.setLayoutParams(layoutParams);
        TranslucentUtils.setTranslucentBottom(activity);
    }
}
