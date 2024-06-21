package spa.lyh.cn.commonutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import spa.lyh.cn.commonutils.dialog.MyDialog;
import spa.lyh.cn.languagepack.LanguagesPack;
import spa.lyh.cn.languagepack.model.LanguageInfo;
import spa.lyh.cn.lib_utils.TextDetailUtils;
import spa.lyh.cn.lib_utils.TimeUtils;
import spa.lyh.cn.lib_utils.ntp.SntpClient;

public class MainActivity extends AppCompatActivity {

    EditText et;

    MyDialog myDialog;

    Button btn_1,btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
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
