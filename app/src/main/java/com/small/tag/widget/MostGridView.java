package com.small.tag.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.small.tag.adapter.ImageGridViewAdapter;
import com.small.tag.utils.LogUtil;

/**
 * 作者：lyx on 2017/4/22 0022 09:21
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class MostGridView extends GridView {
    public MostGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MostGridView(Context context) {
        super(context);
    }

    public MostGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int height = getLayoutParams().height;
        int mHeight = getMeasuredHeight();
        int expandSpec = 0;
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
        LogUtil.d("jiang", "总高度 onMeasure=   getHeight = " + getHeight() + "   params.height =  " + height);
        if (height > 0){
            LogUtil.d("jiang", "改变高度=  true = " + (getHeight() == getMeasuredHeight()) + "====   "+ mHeight);
            expandSpec  =  MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
        } else {
            expandSpec  =  MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, expandSpec);
//        getLayoutParams().height = getHeight();
    }
}
