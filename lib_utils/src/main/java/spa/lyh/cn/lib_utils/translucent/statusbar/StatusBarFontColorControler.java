package spa.lyh.cn.lib_utils.translucent.statusbar;

import android.app.Activity;

import spa.lyh.cn.lib_utils.translucent.statusbar.lightmode.AndroidMHelper;
import spa.lyh.cn.lib_utils.translucent.statusbar.lightmode.ColorOSHelper;
import spa.lyh.cn.lib_utils.translucent.statusbar.lightmode.FlymeHelper;
import spa.lyh.cn.lib_utils.translucent.statusbar.lightmode.MIUIHelper;


/**
 * Created by liyuhao on 2017/12/18.
 * 设置状态栏文字颜色的类，传true为黑字
 */

public class StatusBarFontColorControler {

    public static boolean setStatusBarMode(Activity activity, boolean darkFont){
        boolean flag = false;

        if (new MIUIHelper().setLightMode(activity, darkFont)) {
            flag = true;
        }else if (new FlymeHelper().setLightMode(activity, darkFont)) {
            flag = true;
        }else if (new ColorOSHelper().setLightMode(activity, darkFont)) {
            flag = true;
        }else if (new AndroidMHelper().setLightMode(activity, darkFont)) {
            flag = true;
        }

        return flag;
    }
}