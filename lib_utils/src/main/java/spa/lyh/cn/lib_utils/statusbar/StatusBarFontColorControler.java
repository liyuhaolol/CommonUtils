package spa.lyh.cn.lib_utils.statusbar;

import android.app.Activity;

import spa.lyh.cn.lib_utils.statusbar.lightmode.AndroidMHelper;
import spa.lyh.cn.lib_utils.statusbar.lightmode.ColorOSHelper;
import spa.lyh.cn.lib_utils.statusbar.lightmode.FlymeHelper;
import spa.lyh.cn.lib_utils.statusbar.lightmode.MIUIHelper;


/**
 * Created by liyuhao on 2017/12/18.
 * 设置状态栏文字颜色的类，传true为黑字
 */

public class StatusBarFontColorControler {

    public static boolean setStatusBarMode(Activity activity, boolean isLightMode){
        boolean flag = false;

        if (new MIUIHelper().setLightMode(activity, isLightMode)) {
            flag = true;
        }else if (new FlymeHelper().setLightMode(activity, isLightMode)) {
            flag = true;
        }else if (new ColorOSHelper().setLightMode(activity, isLightMode)) {
            flag = true;
        }else if (new AndroidMHelper().setLightMode(activity, isLightMode)) {
            flag = true;
        }

        return flag;
    }
}
