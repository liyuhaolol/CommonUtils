package spa.lyh.cn.lib_utils.translucent.statusbar;

import android.view.Window;

import spa.lyh.cn.lib_utils.translucent.statusbar.lightmode.AndroidMHelper;
import spa.lyh.cn.lib_utils.translucent.statusbar.lightmode.MIUIHelper;


/**
 * Created by liyuhao on 2017/12/18.
 * 设置状态栏文字颜色的类，传true为黑字
 */

public class StatusBarFontColorControler {

    public static boolean setStatusBarMode(Window window, boolean darkFont){
        boolean flag = false;

        if (new MIUIHelper().setLightMode(window, darkFont)) {
            flag = true;
        }else if (new AndroidMHelper().setLightMode(window, darkFont)) {
            flag = true;
        }

        return flag;
    }
}
