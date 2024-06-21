package spa.lyh.cn.commonutils.example;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.commonutils.R;
import spa.lyh.cn.commonutils.dialog.MyDialog;

public class DialogActivity extends AppCompatActivity {

    MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        myDialog = new MyDialog();
        myDialog.setDialogActivity(this);
    }


    public void onShow(View v){
        myDialog.show();
    }
}
