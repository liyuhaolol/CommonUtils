package spa.lyh.cn.commonutils.example;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.commonutils.R;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;
import spa.lyh.cn.lib_utils.translucent.statusbar.StatusBarFontColorControler;

public class TranslucentActivity extends AppCompatActivity {
    TextView tv_nav_bar,tv_status_bar_height,tv_nav_bar_height,content;
    ImageView status_bar_color,nav_bar_color;
    LinearLayout downarea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);
        //开启沉浸式
        TranslucentUtils.setTranslucentBoth(getWindow());
        //TranslucentUtils.setTranslucentTOP(getWindow());
        //TranslucentUtils.setTranslucentBottom(getWindow());
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置状态栏为深色字体
        StatusBarFontColorControler.setStatusBarMode(getWindow(),true);
        //设置导航栏为深色字体
        NavBarFontColorControler.setNavBarMode(getWindow(),true);

        tv_status_bar_height.setText("高度："+ PixelUtils.getStatusBarHeight(this)+"px");
        BarUtils.autoFitBothBar(this,findViewById(R.id.status_bar),R.id.nav_bar);
        BarUtils.NavbarHeightCallback(this, new OnNavHeightListener() {
            @Override
            public void getHeight(int height,int navbarType) {
                tv_nav_bar_height.setText("高度："+height+"px");
                switch (navbarType){
                    case BarUtils.NO_NAVIGATION:
                        tv_nav_bar.setText("类型：没有导航栏");
                        break;
                    case BarUtils.NORMAL_NAVIGATION:
                        tv_nav_bar.setText("类型：普通导航栏");
                        break;
                    case BarUtils.GESTURE_NAVIGATION:
                        tv_nav_bar.setText("类型：手势控制条");
                        break;
                }

                if (height == 0){
                    downarea.setVisibility(View.GONE);
                }else{
                    downarea.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initView(){
        tv_status_bar_height = findViewById(R.id.tv_status_bar_height);
        tv_nav_bar = findViewById(R.id.tv_nav_bar);
        tv_nav_bar_height = findViewById(R.id.tv_nav_bar_height);
        status_bar_color = findViewById(R.id.status_bar_color);
        status_bar_color.setBackground(getResources().getDrawable(R.color.yellow));
        nav_bar_color = findViewById(R.id.nav_bar_color);
        nav_bar_color.setBackground(getResources().getDrawable(R.color.yellow));
        downarea = findViewById(R.id.downarea);
        content = findViewById(R.id.content);
        content.setText(getContent());
    }

    public String getContent(){
        return "    通过设置页面translucent，使界面可以显示到状态栏和导航栏下方。在通过上下2个View占位将实现显示内容限制回安全区内。\n" +
               "    沉浸逻辑支持最小SDK=19，适合项目采用手动translucent然后进行占位适配导航栏状态栏的方案。";
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
}
