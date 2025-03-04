package spa.lyh.cn.commonutils.example.second

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spa.lyh.cn.commonutils.databinding.ActivityOnesideBinding
import spa.lyh.cn.lib_utils.translucent.Edge2Edge

class OnesideActivity:AppCompatActivity() {
    lateinit var b:ActivityOnesideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityOnesideBinding.inflate(layoutInflater)
        setContentView(b.root)
        Edge2Edge.enable(this)
    }
}