package spa.lyh.cn.commonutils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public class MainActivity extends ABC {

    TextView tv;
    View vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        vv = findViewById(R.id.nav_bar);
        PixelUtils.getNavigationBarHeight(this, new OnNavHeightListener() {
            @Override
            public void getHeight(int height) {
                Log.e("qwer","导航栏高度："+height);
            }
        });



        BarUtils.autoFitBothBar(this,R.id.status_bar,R.id.nav_bar);
        /*StatusBarFontColorControler.setStatusBarMode(this,true);
        NavBarFontColorControler.setNavBarMode(this,true);*/



        //BarUtils.hideNavigationBar(this);

    }

}
