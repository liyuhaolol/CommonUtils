package spa.lyh.cn.commonutils.example.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spa.lyh.cn.commonutils.databinding.ActivityEasyBinding
import spa.lyh.cn.lib_utils.translucent.Edge2Edge

class EasyActivity :AppCompatActivity(){
    lateinit var b:ActivityEasyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityEasyBinding.inflate(layoutInflater)
        setContentView(b.root)
        Edge2Edge.enable(this)
    }
}