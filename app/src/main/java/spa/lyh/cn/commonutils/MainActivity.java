package spa.lyh.cn.commonutils;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import spa.lyh.cn.commonutils.base.BaseActivity;
import spa.lyh.cn.commonutils.dialog.MyDialog;
import spa.lyh.cn.languagepack.LanguagesPack;
import spa.lyh.cn.languagepack.model.LanguageInfo;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.TextDetailUtils;
import spa.lyh.cn.lib_utils.TimeUtils;
import spa.lyh.cn.lib_utils.ntp.SntpClient;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public class MainActivity extends BaseActivity {

    TextView tv_nav_bar,tv_status_bar_height,tv_nav_bar_height,tv_android_version;
    EditText et;

    MyDialog myDialog;

    Button test_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_status_bar_height = findViewById(R.id.tv_status_bar_height);
        tv_nav_bar = findViewById(R.id.tv_nav_bar);
        tv_nav_bar_height = findViewById(R.id.tv_nav_bar_height);
        tv_android_version = findViewById(R.id.tv_android_version);
        tv_android_version.setText("安卓版本号："+Build.VERSION.SDK_INT);
        test_btn = findViewById(R.id.test_btn);
        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
        et = findViewById(R.id.et);
        LanguageInfo info = new LanguageInfo();
        info.language="en";
        info.country="";
        LanguagesPack.setLanguage(this,info);
        Log.e("qwer", TimeUtils.getShowTime(this,1631771072946L));

        SntpClient.requestNtpTime();

        myDialog = new MyDialog();
        myDialog.setDialogActivity(this);

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
                        tv_nav_bar.setText("类型：没有导航栏");
                        break;
                    case BarUtils.NORMAL_NAVIGATION:
                        tv_nav_bar.setText("类型：普通导航栏");
                        break;
                    case BarUtils.GESTURE_NAVIGATION:
                        tv_nav_bar.setText("类型：小白条");
                        break;
                }
            }
        });
    }


    /**
     * ！！！！！这方法没用了已经。
     * 本库已经通过Android默认的Api方法判断是否为小白条手势操作等方法。不再需要根据手机具体厂商进行判断
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

    public void onOpen(View v){
        et.requestFocus();
        TextDetailUtils.openKeybord(et);
    }

    public void onClose(View v){
        TextDetailUtils.closeKeybord(et);
    }

    public void onShow(View v){
        myDialog.show();
    }
}
