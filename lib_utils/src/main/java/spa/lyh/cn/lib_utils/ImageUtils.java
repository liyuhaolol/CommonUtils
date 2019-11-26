package spa.lyh.cn.lib_utils;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageUtils {
    /**
     * 停止gif播放
     * @param imageView
     */
    public static void stopGIF(ImageView imageView){
        if (imageView != null){
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).stop();
            }
        }
    }


    /**
     * 开始gif播放
     * @param imageView
     */
    public static void startGIF(ImageView imageView){
        if (imageView != null){
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof Animatable) {
                ((Animatable) drawable).start();
            }
        }
    }
}
