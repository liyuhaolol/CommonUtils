package spa.lyh.cn.lib_utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StringRes;


/**
 * 小米手机的Toast一律显示app名称
 * 如果觉得这是个很恼人的问题，就使用这个Toast解决。
 */
public class Toast {
    public @interface Duration {
    }

    /**
     * Show the view or text notification for a short period of time.  This time
     * could be user-definable.  This is the default.
     */
    public static final int LENGTH_SHORT = 0;

    /**
     * Show the view or text notification for a long period of time.  This time
     * could be user-definable.
     */
    public static final int LENGTH_LONG = 1;

    private Toast() {
    }

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param text     The text to show.  Can be formatted text.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     */
    public static android.widget.Toast makeText(Context context, CharSequence text, @Duration int duration) {
        android.widget.Toast result = new android.widget.Toast(context);
        //创建一个系统默认toast，用来取属性
        android.widget.Toast textT = android.widget.Toast.makeText(context,"",android.widget.Toast.LENGTH_LONG);
        LinearLayout linT = (LinearLayout) textT.getView();
        TextView tvT = (TextView) linT.getChildAt(0);
        tvT.setText(text);
        result.setView(linT);
        result.setDuration(duration);
        return result;
    }

    /**
     * Make a standard toast that just contains a text view with the text from a resource.
     *
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param resId    The resource id of the string resource to use.  Can be formatted text.
     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
     *                 {@link #LENGTH_LONG}
     * @throws Resources.NotFoundException if the resource can't be found.
     */
    public static android.widget.Toast makeText(Context context, @StringRes int resId, @Duration int duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }
}
