package spa.lyh.cn.lib_utils.translucent.statusbar.lightmode;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class ColorOSHelper implements ILightModeHelper{

    private final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    @Override
    public boolean setLightMode(Activity activity, boolean isLightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Window window  = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int vis = window.getDecorView().getSystemUiVisibility();
            if (isLightMode) {
                vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            } else {
                vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            }
            window.getDecorView().setSystemUiVisibility(vis);
            return true;
        }else {
            Log.w("LightModeException","Failed to match ColorOS 3.0");
            return false;
        }

    }
}
