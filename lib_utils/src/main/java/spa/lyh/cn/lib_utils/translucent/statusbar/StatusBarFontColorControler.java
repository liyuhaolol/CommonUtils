package spa.lyh.cn.lib_utils.translucent.statusbar;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;


/**
 * Created by liyuhao on 2017/12/18.
 * 设置状态栏文字颜色的类，传true为黑字
 */

public class StatusBarFontColorControler {

    public static boolean setStatusBarMode(Window window, boolean darkFont){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//6.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){//11
                if (window.getInsetsController() != null){
                    window.getInsetsController().setSystemBarsAppearance(
                            darkFont? WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS:0,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    );
                    return true;
                }else {
                    Log.w("LightModeException","InsetsController is NULL");
                    return false;
                }
            }else {
                View view = window.getDecorView();
                int oldVis = view.getSystemUiVisibility();
                int newVis = oldVis;
                if (darkFont){
                    newVis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }else {
                    newVis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                if (newVis != oldVis) {
                    view.setSystemUiVisibility(newVis);
                }
                return true;
            }
        }
        Log.w("LightModeException","Failed to match above Android 6.0");
        return false;
    }
}
