package spa.lyh.cn.commonutils.example;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.commonutils.R;

public class Edge2EdgeActivity extends AppCompatActivity {
    TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView(){
        content = findViewById(R.id.content);
        content.setText(getContent());
    }

    public String getContent(){
        return "    sdk26开始导航栏文字无法变为深色，sdk22开始状态栏文字无法变为深色，sdk19开始彻底失效";
    }
}
