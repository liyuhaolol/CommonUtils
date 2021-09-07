package spa.lyh.cn.commonutils;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;

public abstract class ASD extends AppCompatActivity {
    private int navbarHeight;//这个字段仅仅用来保存曾经的导航栏高度，仅仅用来兼容国内系统UI不按照原生逻辑走的问题，可能10年后会删除这个无脑的逻辑吧

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TranslucentUtils.setTranslucentBoth(getWindow());
        //TranslucentUtils.setTranslucentBottom(getWindow());
        /*Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            window.setNavigationBarColor(0x01000000);
        }else {
            window.setNavigationBarColor(Color.TRANSPARENT);
        }*/
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        PixelUtils.getNavigationBarHeight(this, new OnNavHeightListener() {
            @Override
            public void getHeight(int height, int navbarType) {
                Log.e("qwer","");
            }
        });
    }
}
