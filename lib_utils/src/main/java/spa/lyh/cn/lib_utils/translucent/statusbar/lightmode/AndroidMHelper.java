package spa.lyh.cn.lib_utils.translucent.statusbar.lightmode;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;

/**
 * Created by liyuhao on 2017/3/17.
 */

public class AndroidMHelper implements ILightModeHelper {

    @Override
    public boolean setLightMode(Window window, boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            View view = window.getDecorView();
            int oldVis = view.getSystemUiVisibility();
            int newVis = oldVis;
            if (isLightMode){
                newVis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }else {
                newVis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            if (newVis != oldVis) {
                view.setSystemUiVisibility(newVis);
            }

            return true;
        }
        Log.w("LightModeException","Failed to match above Android 6.0");
        return false;
    }
}
