package com.small.tag.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 作者：jason on 2017/9/18 0018 15:12
 * 邮箱：jianglu@eims.com.cn
 * 说明：解决web view 滑动冲突问题
 */
public class MyScrollView extends ScrollView {
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            boolean b = super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
            return b;
    }

    // Return false if we're scrolling in the x direction
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}