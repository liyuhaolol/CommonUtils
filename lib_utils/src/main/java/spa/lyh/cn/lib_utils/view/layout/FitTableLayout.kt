package spa.lyh.cn.lib_utils.view.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.TableLayout
import spa.lyh.cn.lib_utils.R

open class FitTableLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TableLayout(context, attrs) {
    var fitLeft:Boolean = false
    var fitRight:Boolean = false
    var fitTop:Boolean = false
    var fitBottom:Boolean = false

    init {
        val ta = if (attrs == null) null else getContext().obtainStyledAttributes(
            attrs,
            R.styleable.FitTableLayout
        )
        if(ta != null){
            fitLeft = ta.getBoolean(R.styleable.FitTableLayout_tlFitLeft, false)
            fitRight = ta.getBoolean(R.styleable.FitTableLayout_tlFitRight, false)
            fitTop = ta.getBoolean(R.styleable.FitTableLayout_tlFitTop, false)
            fitBottom = ta.getBoolean(R.styleable.FitTableLayout_tlFitBottom, false)
        }
    }



    override fun fitSystemWindows(insets: Rect): Boolean {
        if (fitsSystemWindows){
            //系统为true则强制按照系统的要求走
            setPadding(insets.left+paddingLeft, insets.top+paddingTop, insets.right+paddingRight, insets.bottom+paddingBottom)
        }else{
            setPadding(if(fitLeft){insets.left+paddingLeft} else paddingLeft, if(fitTop){insets.top+paddingTop} else paddingTop,if(fitRight){insets.right+paddingRight} else paddingRight, if(fitBottom){insets.bottom+paddingBottom} else paddingBottom)
        }
        //返回false不阻止子布局的fitsSystemWindows属性生效
        return false
    }
}