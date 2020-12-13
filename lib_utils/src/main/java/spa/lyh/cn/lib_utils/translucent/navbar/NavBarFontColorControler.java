package spa.lyh.cn.lib_utils.translucent.navbar;

import android.os.Build;
import android.view.View;
import android.view.Window;

import spa.lyh.cn.lib_utils.AppUtils;

public class NavBarFontColorControler {

    public static void setNavBarMode(Window window, boolean darkFont){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            AppUtils.setSystemUiVisibility(window.getDecorView(), View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,darkFont);
        }
    }
}
