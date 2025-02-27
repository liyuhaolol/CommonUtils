package spa.lyh.cn.lib_utils.view;

import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by liyuhao on 2017/6/16.
 */

public class PopupWindowAndroidN extends PopupWindow{


    public PopupWindowAndroidN(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int []a = new int[2];
            anchor.getLocationInWindow(a);
            showAtLocation(anchor, Gravity.NO_GRAVITY, xoff, a[1] + anchor.getHeight() + yoff);
        } else {
            super.showAsDropDown(anchor, xoff, yoff);
        }

    }

    @Override
    public void showAsDropDown(View anchor) {

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            int []a = new int[2];
            anchor.getLocationInWindow(a);
            showAtLocation(anchor, Gravity.NO_GRAVITY, 0, a[1] + anchor.getHeight() + 0);
        } else {
            super.showAsDropDown(anchor);
        }

    }
}
