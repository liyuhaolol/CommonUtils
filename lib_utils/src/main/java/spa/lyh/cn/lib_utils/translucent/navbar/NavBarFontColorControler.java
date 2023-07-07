package spa.lyh.cn.lib_utils.translucent.navbar;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

import spa.lyh.cn.lib_utils.AppUtils;

public class NavBarFontColorControler {


    public static boolean setNavBarMode(Window window, boolean darkFont){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){//8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){//11'
                try{
                    window.getDecorView().getWindowInsetsController().setSystemBarsAppearance(
                            darkFont? WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS:0,
                            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                    );
                    return true;
                }catch (Exception e){
                    Log.w("LightModeException","WindowInsetsController is NULL");
                    return false;
                }
            }else {
                AppUtils.setSystemUiVisibility(window.getDecorView(), View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,darkFont);
                return true;
            }
        }
        Log.w("LightModeException","Failed to match above Android 8.0");
        return false;
    }
}
