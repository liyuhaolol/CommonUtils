package spa.lyh.cn.commonutils

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spa.lyh.cn.commonutils.databinding.ActivitySecondBinding
import spa.lyh.cn.commonutils.example.second.EasyActivity
import spa.lyh.cn.commonutils.example.second.OnesideActivity

class SecondActivity:AppCompatActivity() {
    lateinit var b:ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btn1.setOnClickListener {
            val intent = Intent(this@SecondActivity,EasyActivity::class.java)
            startActivity(intent)
        }
        b.btn2.setOnClickListener {
            val intent = Intent(this@SecondActivity,OnesideActivity::class.java)
            startActivity(intent)
        }
    }
}