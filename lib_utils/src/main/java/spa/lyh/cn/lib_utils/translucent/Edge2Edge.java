package spa.lyh.cn.lib_utils.translucent;

import android.graphics.Color;
import android.os.Build;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.activity.SystemBarStyle;
import androidx.activity.ComponentActivity;

public class Edge2Edge {

    public static void enable(ComponentActivity activity){
        EdgeToEdge.enable(
                activity,
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
                SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Window window = activity.getWindow();
            if (window != null){
                window.setStatusBarContrastEnforced(false);
                window.setNavigationBarContrastEnforced(false);
            }
        }
    }
}
