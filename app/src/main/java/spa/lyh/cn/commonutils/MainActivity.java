package spa.lyh.cn.commonutils;

import static android.view.View.NO_ID;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.listener.NotchListener;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public class MainActivity extends ABC {

    TextView tv_status_bar,tv_nav_bar,tv_status_bar_height,tv_nav_bar_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_status_bar_height = findViewById(R.id.tv_status_bar_height);
        tv_status_bar_height.setText("状态栏高度："+PixelUtils.getStatusBarHeight(this));
        tv_status_bar = findViewById(R.id.tv_status_bar);
        tv_nav_bar = findViewById(R.id.tv_nav_bar);
        tv_nav_bar_height = findViewById(R.id.tv_nav_bar_height);
        BarUtils.NavbarHeightCallback(this, new OnNavHeightListener() {
            @Override
            public void getHeight(int height,int navbarType) {
                tv_nav_bar_height.setText("导航栏高度："+height);
                switch (navbarType){
                    case BarUtils.NO_NAVIGATION:
                        tv_nav_bar.setText("没有导航栏");
                        break;
                    case BarUtils.NORMAL_NAVIGATION:
                        tv_nav_bar.setText("普通导航栏");
                        break;
                    case BarUtils.GESTURE_NAVIGATION:
                        tv_nav_bar.setText("小白条");
                        break;
                }
            }
        });



        BarUtils.autoFitBothBar(this,findViewById(R.id.status_bar),R.id.nav_bar);
        /*StatusBarFontColorControler.setStatusBarMode(this,true);
        NavBarFontColorControler.setNavBarMode(this,true);*/



        //BarUtils.hideNavigationBar(this);

        AppUtils.hasNotch(getWindow(), new NotchListener() {
            @Override
            public void onResult(boolean hasNotch) {
                if (hasNotch){
                    tv_status_bar.setText("存在刘海(Android10以上判断不准确)");
                }else {
                    tv_status_bar.setText("不存在刘海");
                }
            }
        });

/*        getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                Log.e("qwer","onViewAttachedToWindow");
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                Log.e("qwer","onViewDetachedFromWindow");
            }
        });*/

        Log.e("qwer","是否存在导航栏："+isNavigationBarExist(this));

    }

    private static final String NAVIGATION= "navigationBarBackground";

    // 该方法需要在View完全被绘制出来之后调用，否则判断不了
    //在比如 onWindowFocusChanged（）方法中可以得到正确的结果
    public static  boolean isNavigationBarExist(@NonNull Activity activity){
        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
        if (vp != null) {
            for (int i = 0; i < vp.getChildCount(); i++) {
                vp.getChildAt(i).getContext().getPackageName();
                if (vp.getChildAt(i).getId()!= NO_ID && NAVIGATION.equals(activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()))) {
                    View v = vp.getChildAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("qwer","onConfigurationChanged");
        BarUtils.autoFitBothBar(this,findViewById(R.id.status_bar),R.id.nav_bar);
    }
}
