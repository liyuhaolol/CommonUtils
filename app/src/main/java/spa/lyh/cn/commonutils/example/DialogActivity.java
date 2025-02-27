package spa.lyh.cn.commonutils.example;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.commonutils.R;
import spa.lyh.cn.commonutils.dialog.MyDialog;

public class DialogActivity extends AppCompatActivity {

    MyDialog myDialog;

    TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        myDialog = new MyDialog();
        myDialog.setDialogActivity(this);
        content = findViewById(R.id.content);
        content.setText(getContent());
    }


    public void onShow(View v){
        myDialog.show();
    }

    public String getContent(){
        return "    通过开启FragmentDialog的translucent，从而达到可以将dialog的实际View可以显示到全屏，从而最大可以在dialog里进行全屏View的显示。\n" +
                "    用来解决自带的Dialog类默认只能显示到屏幕中间，且有默认的Padding，并且无法显示到状态栏和导航栏里（并不是改不了，只是太麻烦）。用来解决那种从屏幕下方浮起来带有沉浸式的UI设计。\n" +
                "    沉浸逻辑支持最小SDK=21(Android5.0)，适合项目采用手动translucent然后进行占位适配导航栏状态栏的方案。\n\n" +
                "    该适配方案在>=SDK26(Android8.0)以上是完美适配所有情况的，所以此方案推荐设置项目minSDK=26。\n\n" +
                "    低版本的UI变化说明：\n" +
                "    SDK21(Android5.0)---SDK25(Android7.1.1)导航栏会强制添加半透明黑色遮罩。\n" +
                "    SDK21(Android5.0)---SDK22(Android5.1)状态栏文字无法变为深色。";
    }
}
