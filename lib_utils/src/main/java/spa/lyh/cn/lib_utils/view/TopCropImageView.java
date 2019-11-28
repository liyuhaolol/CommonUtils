package spa.lyh.cn.lib_utils.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;

public class TopCropImageView extends androidx.appcompat.widget.AppCompatImageView {
    public TopCropImageView(Context context) {
        this(context,null);
    }

    public TopCropImageView(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }

    public TopCropImageView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        setup();
    }

    private void setup() {
        setScaleType(ScaleType.CENTER_CROP);
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    protected boolean setFrame(int frameLeft, int frameTop, int frameRight, int frameBottom) {
        float frameWidth = frameRight - frameLeft;
        float frameHeight = frameBottom - frameTop;
        if (getDrawable() != null){
            float originalImageWidth = (float) getDrawable().getIntrinsicWidth();
            float originalImageHeight = (float) getDrawable().getIntrinsicHeight();

            float usedScaleFactor = 1;

            if ((frameWidth > originalImageWidth) || (frameHeight > originalImageHeight)) {
                // If frame is bigger than image
                // => Crop it, keep aspect ratio and position it at the bottom and center horizontally

                float fitHorizontallyScaleFactor = frameWidth / originalImageWidth;
                float fitVerticallyScaleFactor = frameHeight / originalImageHeight;
                usedScaleFactor = Math.max(fitHorizontallyScaleFactor, fitVerticallyScaleFactor);
            }

            float newImageWidth = originalImageWidth * usedScaleFactor;
            float newImageHeight = originalImageHeight * usedScaleFactor;

            Matrix matrix = getImageMatrix();
            matrix.setScale(usedScaleFactor, usedScaleFactor, 0, 0); // Replaces the old matrix completly

            // matrix.postTranslate((frameWidth - newImageWidth) / 2, frameHeight - newImageHeight);//BottomCrop
            matrix.postTranslate((frameWidth - newImageWidth) / 2, 0);//Top Crop
            setImageMatrix(matrix);
        }
        return super.setFrame(frameLeft, frameTop, frameRight, frameBottom);
    }
}
