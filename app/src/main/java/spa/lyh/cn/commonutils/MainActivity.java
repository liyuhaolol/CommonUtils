package spa.lyh.cn.commonutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.PixelUtils;
import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.view.EmptyItemAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, AppUtils.checkDeviceHasNavigationBar(this)+"",Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, AppUtils.isNetworkAvailable(this)+"",Toast.LENGTH_SHORT).show();
        //EmptyItemAnimator myItemAnimator = new EmptyItemAnimator();
        //Log.e("qwer","高度："+PixelUtils.getNavigationBarHeight(this));
        BarUtils.autoFitStatusBar(this,R.id.status_bar,new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0));
        //BarUtils.autoFitNavigationBar(this,R.id.nav_bar,new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,PixelUtils.getNavigationBarHeight(this)));
    }
}
