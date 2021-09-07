package spa.lyh.cn.commonutils;


import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;
import spa.lyh.cn.lib_utils.translucent.statusbar.StatusBarFontColorControler;

public abstract class ABC extends ASD{

    @Override
    protected void onResume() {
        super.onResume();
        StatusBarFontColorControler.setStatusBarMode(getWindow(),true);
        NavBarFontColorControler.setNavBarMode(getWindow(),true);
    }
}
