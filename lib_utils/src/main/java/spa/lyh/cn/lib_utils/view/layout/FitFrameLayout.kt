package spa.lyh.cn.lib_utils.view.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.FrameLayout
import spa.lyh.cn.lib_utils.R

open class FitFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    var fitLeft:Boolean = false
    var fitRight:Boolean = false
    var fitTop:Boolean = false
    var fitBottom:Boolean = false
    var mPaddingTop:Int = 0
    var mPaddingBottom:Int = 0
    var mPaddingLeft:Int = 0
    var mPaddingRight:Int = 0

    init {
        val ta = if (attrs == null) null else getContext().obtainStyledAttributes(
            attrs,
            R.styleable.FitFrameLayout
        )
        if(ta != null){
            fitLeft = ta.getBoolean(R.styleable.FitFrameLayout_fitLeft, false)
            fitRight = ta.getBoolean(R.styleable.FitFrameLayout_fitRight, false)
            fitTop = ta.getBoolean(R.styleable.FitFrameLayout_fitTop, false)
            fitBottom = ta.getBoolean(R.styleable.FitFrameLayout_fitBottom, false)
            mPaddingTop = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingTop,0)
            mPaddingBottom = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingBottom,0)
            mPaddingLeft = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingLeft,0)
            mPaddingRight = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingRight,0)
            val mPaddingStart = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingStart,0)
            val mPaddingEnd = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingEnd,0)
            if (layoutDirection == LAYOUT_DIRECTION_RTL){
                if (mPaddingStart > 0){
                    mPaddingRight = mPaddingStart
                }
                if (mPaddingEnd > 0){
                    mPaddingLeft = mPaddingEnd
                }
            }else{
                if (mPaddingStart > 0){
                    mPaddingLeft = mPaddingStart
                }
                if (mPaddingEnd > 0){
                    mPaddingRight = mPaddingEnd
                }
            }
            val mPaddingHorizontal = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingHorizontal,0)
            if (mPaddingHorizontal > 0){
                mPaddingLeft = mPaddingHorizontal
                mPaddingRight = mPaddingHorizontal
            }
            val mPaddingVertical = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_paddingVertical,0)
            if (mPaddingVertical > 0){
                mPaddingTop = mPaddingVertical
                mPaddingBottom = mPaddingVertical
            }
            val mPadding = ta.getDimensionPixelSize(R.styleable.FitFrameLayout_padding,0)
            if (mPadding > 0){
                mPaddingTop = mPadding
                mPaddingBottom = mPadding
                mPaddingLeft = mPadding
                mPaddingRight = mPadding
            }
            setPadding(mPaddingLeft,mPaddingTop,mPaddingRight,mPaddingBottom)
        }
    }



    override fun fitSystemWindows(insets: Rect): Boolean {
        if (fitsSystemWindows){
            //系统为true则强制按照系统的要求走
            setPadding(insets.left+mPaddingLeft, insets.top+mPaddingTop, insets.right+mPaddingRight, insets.bottom+mPaddingBottom)
        }else{
            setPadding(if(fitLeft){insets.left+mPaddingLeft} else mPaddingLeft, if(fitTop){insets.top+mPaddingTop} else mPaddingTop,if(fitRight){insets.right+mPaddingRight} else mPaddingRight, if(fitBottom){insets.bottom+mPaddingBottom} else mPaddingBottom)
        }
        //返回false不阻止子布局的fitsSystemWindows属性生效
        return false
    }
}