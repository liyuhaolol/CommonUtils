package spa.lyh.cn.commonutils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;

public abstract class ASD extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TranslucentUtils.setTranslucentBoth(getWindow());
        //TranslucentUtils.setTranslucentBottom(this);
    }

}
