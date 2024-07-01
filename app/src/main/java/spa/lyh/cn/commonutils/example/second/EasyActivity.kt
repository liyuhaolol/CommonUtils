package spa.lyh.cn.commonutils.example.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spa.lyh.cn.commonutils.databinding.ActivityEasyBinding
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils

class EasyActivity :AppCompatActivity(){
    lateinit var b:ActivityEasyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityEasyBinding.inflate(layoutInflater)
        setContentView(b.root)
        TranslucentUtils.setTranslucentBoth(window)
    }
}