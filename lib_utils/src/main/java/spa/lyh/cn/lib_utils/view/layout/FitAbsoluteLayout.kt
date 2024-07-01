package spa.lyh.cn.lib_utils.view.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.AbsoluteLayout
import spa.lyh.cn.lib_utils.R

open class FitAbsoluteLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbsoluteLayout(context, attrs, defStyleAttr) {
    var fitLeft:Boolean = false
    var fitRight:Boolean = false
    var fitTop:Boolean = false
    var fitBottom:Boolean = false

    init {
        val ta = if (attrs == null) null else getContext().obtainStyledAttributes(
            attrs,
            R.styleable.FitAbsoluteLayout
        )
        if(ta != null){
            fitLeft = ta.getBoolean(R.styleable.FitAbsoluteLayout_alFitLeft, false)
            fitRight = ta.getBoolean(R.styleable.FitAbsoluteLayout_alFitRight, false)
            fitTop = ta.getBoolean(R.styleable.FitAbsoluteLayout_alFitTop, false)
            fitBottom = ta.getBoolean(R.styleable.FitAbsoluteLayout_alFitBottom, false)
        }
    }



    override fun fitSystemWindows(insets: Rect): Boolean {
        if (fitsSystemWindows){
            //系统为true则强制按照系统的要求走
            setPadding(insets.left, insets.top, insets.right, insets.bottom)
        }else{
            setPadding(if(fitLeft)insets.left else 0, if(fitTop)insets.top else 0,if(fitRight)insets.right else 0, if(fitBottom)insets.bottom else 0)
        }
        //返回false不阻止子布局的fitsSystemWindows属性生效
        return false
    }
}