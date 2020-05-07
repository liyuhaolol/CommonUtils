package spa.lyh.cn.lib_utils.translucent.navbar;

import android.app.Activity;
import android.os.Build;
import android.view.View;

import spa.lyh.cn.lib_utils.AppUtils;

public class NavBarFontColorControler {

    public static void setNavBarMode(Activity activity,boolean darkFont){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            AppUtils.setSystemUiVisibility(activity.getWindow().getDecorView(), View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,darkFont);
        }
    }
}
