package spa.lyh.cn.commonutils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import spa.lyh.cn.lib_utils.AppUtils;
import spa.lyh.cn.lib_utils.view.EmptyItemAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, AppUtils.checkDeviceHasNavigationBar(this)+"",Toast.LENGTH_SHORT).show();
        Toast.makeText(this, AppUtils.isNetworkAvailable(this)+"",Toast.LENGTH_SHORT).show();
        EmptyItemAnimator myItemAnimator = new EmptyItemAnimator();

    }
}
