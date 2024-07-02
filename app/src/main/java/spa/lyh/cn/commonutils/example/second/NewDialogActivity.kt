package spa.lyh.cn.commonutils.example.second

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import spa.lyh.cn.commonutils.databinding.ActivityDialogBinding
import spa.lyh.cn.commonutils.dialog.NewMyDialog

class NewDialogActivity:AppCompatActivity() {
    lateinit var b:ActivityDialogBinding
    private lateinit var newMyDialog:NewMyDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(b.root)
        newMyDialog = NewMyDialog(this)
    }

    fun onShow(v:View){
        newMyDialog.show()
    }
}