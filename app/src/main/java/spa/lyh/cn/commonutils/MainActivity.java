package spa.lyh.cn.commonutils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import spa.lyh.cn.commonutils.example.DialogActivity;
import spa.lyh.cn.commonutils.example.TranslucentActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_1,btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TranslucentActivity.class);
                startActivity(intent);
            }
        });
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

    }
}
