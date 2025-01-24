package spa.lyh.cn.commonutils

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.xiandan.countdowntimer.CountDownTimerX
import `in`.xiandan.countdowntimer.OnCountDownTimerListener
import spa.lyh.cn.lib_utils.translucent.Edge2Edge

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    var time = 1

    lateinit var timer:CountDownTimerX

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Edge2Edge.enable(this)
        timer = CountDownTimerX((time*1000).toLong(),1000)
        timer.setOnCountDownTimerListener(object: OnCountDownTimerListener{
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onCancel() {

            }
        })
        timer.start()
    }

    override fun onResume() {
        super.onResume()
    }
}