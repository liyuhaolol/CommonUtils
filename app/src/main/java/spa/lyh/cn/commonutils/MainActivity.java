package spa.lyh.cn.commonutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.view.EmptyItemAnimator;

public class MainActivity extends AppCompatActivity {

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
        BarUtils.autoFitStatusBar(this,R.id.status_bar);
        BarUtils.autoFitNavigationBar(this,R.id.nav_bar);
        //AppUtils.setSystemUiVisibility(getWindow().getDecorView(), View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,false);
        /*new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                AppUtils.setSystemUiVisibility(getWindow().getDecorView(), View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,false);
            }
        },1000);*/


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("qwer","点击");
                vv.setBackgroundColor(getResources().getColor(R.color.black));
                //AppUtils.setSystemUiVisibility(getWindow().getDecorView(), View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR,true);
            }
        });
    }
}
