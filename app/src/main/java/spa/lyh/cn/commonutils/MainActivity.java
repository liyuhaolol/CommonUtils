package spa.lyh.cn.commonutils;

import static android.view.View.NO_ID;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;

import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

import spa.lyh.cn.languagepack.LanguagesPack;
import spa.lyh.cn.languagepack.model.LanguageInfo;
import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.TimeUtils;
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
        tv_status_bar = findViewById(R.id.tv_status_bar);
        tv_nav_bar = findViewById(R.id.tv_nav_bar);
        tv_nav_bar_height = findViewById(R.id.tv_nav_bar_height);
        LanguageInfo info = new LanguageInfo();
        info.language="en";
        info.country="";
        LanguagesPack.setLanguage(this,info);
        Log.e("qwer", TimeUtils.getShowTime(this,1631771072946L));

    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_status_bar_height.setText("状态栏高度："+PixelUtils.getStatusBarHeight(this));
        BarUtils.autoFitBothBar(this,findViewById(R.id.status_bar),R.id.nav_bar);
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
    }

    /**
     * ！！！！！这方法没用，就这里复制着而已。
     * 获取主流手机设置中的"navigation_gesture_on"值，判断当前系统是使用导航键还是手势导航操作
     * @param context app Context
     * @return
     * false 表示使用的是虚拟导航键(NavigationBar)，
     * true 表示使用的是手势， 默认是false
     */
    private static boolean navigationGestureEnabled(Context context) {
        int val = Settings.Global.getInt(context.getContentResolver(), getDeviceInfo(), 0);
        return val != 0;
    }
    /**
     * 获取设备信息（目前支持几大主流的全面屏手机，亲测华为、小米、oppo、魅族、vivo、三星都可以）
     * 这玩意用来判断是否开启了手势，跟是否隐藏小白条完全无关哦
     * @return
     */
    private static String getDeviceInfo() {
        String brand = Build.BRAND;
        if(TextUtils.isEmpty(brand)) return "navigationbar_is_min";

        if (brand.equalsIgnoreCase("HUAWEI")||"HONOR".equals(brand)) {
            return "navigationbar_is_min";
        } else if (brand.equalsIgnoreCase("XIAOMI")) {
            return "force_fsg_nav_bar";
        } else if (brand.equalsIgnoreCase("VIVO")) {
            return "navigation_gesture_on";
        } else if (brand.equalsIgnoreCase("OPPO")) {
            return "navigation_gesture_on";
        } else if(brand.equalsIgnoreCase("samsung")){
            return "navigationbar_hide_bar_enabled";
        }else {
            return "navigationbar_is_min";
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(newBase));
    }
}
