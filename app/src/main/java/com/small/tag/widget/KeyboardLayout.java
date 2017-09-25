package com.small.tag.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 作者：jason on 2017/9/18 0018 15:12
 * 邮箱：jianglu@eims.com.cn
 * 说明：解决web view 滑动冲突问题
 */
public class KeyboardLayout extends RelativeLayout {

    private onSizeChangedListener mChangedListener;
    private static final String TAG = "KeyboardLayoutTAG";
    private boolean mShowKeyboard = false;

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public KeyboardLayout(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        if (null != mChangedListener && 0 != oldw && 0 != oldh) {
            if (h < oldh) {
                mShowKeyboard = true;
            } else {
                mShowKeyboard = false;
            }
            mChangedListener.onChanged(mShowKeyboard);
        }
    }

    public void setOnSizeChangedListener(onSizeChangedListener listener) {
        mChangedListener = listener;
    }

    public interface onSizeChangedListener {

        void onChanged(boolean showKeyboard);
    }
}