package spa.lyh.cn.commonutils.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import spa.lyh.cn.commonutils.R
import spa.lyh.cn.commonutils.databinding.PopNewBinding
import spa.lyh.cn.lib_utils.dialog.FullDialog

class NewMyDialog(act:FragmentActivity) :FullDialog(act){
    lateinit var b:PopNewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = PopNewBinding.inflate(layoutInflater)
        return b.root
    }

    override fun setStyleId(): Int {
        return R.style.NewDialog
    }

    override fun setStatusBarId(): Int {
        //使用属性完成沉浸，所以没有对应的View
        return 0
    }

    override fun setNavigationBarId(): Int {
        //使用属性完成沉浸，所以没有对应的View
        return 0
    }

    override fun isUIimmerseNavbar(): Boolean {
        //不存在对应的View，本返回值没有任何作用
        return false
    }

    override fun setShowTag(): String {
        return "NewMyDialog"
    }

}