package spa.lyh.cn.commonutils.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;
import spa.lyh.cn.lib_utils.translucent.statusbar.StatusBarFontColorControler;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //开启沉浸式
        TranslucentUtils.setTranslucentBoth(getWindow());
        //TranslucentUtils.setTranslucentTOP(getWindow());
        //TranslucentUtils.setTranslucentBottom(getWindow());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置状态栏为深色字体
        StatusBarFontColorControler.setStatusBarMode(getWindow(),true);
        //设置导航栏为深色字体
        NavBarFontColorControler.setNavBarMode(getWindow(),true);
    }
}
