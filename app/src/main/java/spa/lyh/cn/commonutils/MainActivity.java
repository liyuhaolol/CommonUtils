package spa.lyh.cn.commonutils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;
import spa.lyh.cn.lib_utils.translucent.statusbar.StatusBarFontColorControler;

public class MainActivity extends ABC {

    TextView tv;
    View vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        vv = findViewById(R.id.nav_bar);
        //Toast.makeText(this, AppUtils.checkDeviceHasNavigationBar(this)+"",Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, AppUtils.isNetworkAvailable(this)+"",Toast.LENGTH_SHORT).show();
        //EmptyItemAnimator myItemAnimator = new EmptyItemAnimator();
        Log.e("qwer","高度："+PixelUtils.getNavigationBarHeight(this));

        BarUtils.autoFitBothBar(this,R.id.status_bar,R.id.nav_bar);
        StatusBarFontColorControler.setStatusBarMode(this,true);
        NavBarFontColorControler.setNavBarMode(this,true);



        //BarUtils.hideNavigationBar(this);

    }


}
