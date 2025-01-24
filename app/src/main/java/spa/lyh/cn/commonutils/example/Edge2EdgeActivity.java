package spa.lyh.cn.commonutils.example;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.commonutils.R;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.Edge2Edge;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public class Edge2EdgeActivity extends AppCompatActivity {
    TextView tv_nav_bar,tv_status_bar_height,tv_nav_bar_height,content;
    ImageView status_bar_color,nav_bar_color;
    LinearLayout downarea;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);
        Edge2Edge.enable(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                        tv_nav_bar.setText("类型：手势提示线");
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
        return "    强烈建议建议minSdk>=SDK26，也就是Android8.0进行开发。\n\n"+
                "    通过设置页面edge2edge，使界面可以显示到状态栏和导航栏下方。在通过上下2个View占位将实现显示内容限制回安全区内。\n" +
                "    该适配方案在>=SDK26(Android8.0)以上是完美适配所有情况的，所以此方案推荐设置项目minSDK=26。\n\n" +
                "    低版本的UI变化说明：\n" +
                "    SDK23(Android6.0)---SDK25(Android7.1.1)导航栏因为安卓15的edge2edge api似乎会强制去掉遮罩，导致导航栏三大金刚在浅色界面不可见\n" +
                "    SDK21(Android5.0)---SDK22(Android5.1)状态栏导航栏会加阴影，文字只能为浅色。\n" +
                "    重要提示：本页面只是展示一种纯代码完成对应逻辑的例子，不代表你只能这么做。你完全可以通过fitsSystemWindows属性和themes达到完全一致的效果。\n\n"+
                "    注：安卓15后，GooglePlay上线会检查api，禁止使用themes这种陈旧方式实现沉浸式。续强制使用edge2edge";
    }
}
