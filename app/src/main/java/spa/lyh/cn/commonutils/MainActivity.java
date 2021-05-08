package spa.lyh.cn.commonutils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.listener.NotchListener;
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
        BarUtils.NavbarHeightCallback(this, new OnNavHeightListener() {
            @Override
            public void getHeight(int height,int navbarType) {
                Log.e("qwer","导航栏高度："+height);
                switch (navbarType){
                    case BarUtils.NO_NAVIGATION:
                        Toast.makeText(MainActivity.this,"没有导航栏",Toast.LENGTH_LONG).show();
                        break;
                    case BarUtils.NORMAL_NAVIGATION:
                        Toast.makeText(MainActivity.this,"普通导航栏",Toast.LENGTH_LONG).show();
                        break;
                    case BarUtils.GESTURE_NAVIGATION:
                        Toast.makeText(MainActivity.this,"小白条",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });



        BarUtils.autoFitBothBar(this,findViewById(R.id.status_bar),R.id.nav_bar);
        /*StatusBarFontColorControler.setStatusBarMode(this,true);
        NavBarFontColorControler.setNavBarMode(this,true);*/



        //BarUtils.hideNavigationBar(this);

        /*AppUtils.hasNotch(getWindow(), new NotchListener() {
            @Override
            public void onResult(boolean hasNotch) {
                if (hasNotch){
                    Toast.makeText(MainActivity.this,"存在刘海",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"不存在刘海",Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }

}
