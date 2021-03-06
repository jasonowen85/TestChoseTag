package com.small.tag.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/**
 * 作者：lyx on 2017/5/11 0011 15:49
 * 邮箱：liyouxun@eims.com.cn
 * 说明：高度固定 宽度自适应 按比例缩放
 * 实际开发中 可以使用adjust  max height scaleTyep = fixcenter  centerInside
 */
public class AdaptiveImageViewHeight extends ImageView{
    // 控件默认长、宽
    private int defaultWidth = 0;
    private int defaultHeight = 0;
    // 比例
    private float scale = 0;

    public AdaptiveImageViewHeight(Context context) {
        super(context);
    }

    public AdaptiveImageViewHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveImageViewHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdaptiveImageViewHeight(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        if (getWidth() == 0 || getHeight() == 0) return;

        this.measure(0, 0);
        if (drawable.getClass() == NinePatchDrawable.class) return;

        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
        if (bitmap.getWidth() == 0 || bitmap.getHeight() == 0)  return;

        if (defaultWidth == 0) {
            defaultWidth = getWidth();
        }
        if (defaultHeight == 0) {
            defaultHeight = getHeight();
        }
        scale = (float) defaultHeight / (float) bitmap.getHeight();
        defaultWidth = Math.round(bitmap.getWidth() * scale);
        LayoutParams params = this.getLayoutParams();
        params.width = defaultWidth;
        params.height = defaultHeight;
        this.setLayoutParams(params);
        super.onDraw(canvas);
    }

}
