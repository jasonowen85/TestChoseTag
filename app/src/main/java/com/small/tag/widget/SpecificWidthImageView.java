package com.small.tag.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by jasonwen on 2017/4/23.
 */
public class SpecificWidthImageView extends ImageView implements ChangeGridViewSize{
    private ChangeGridViewSize listenSize;

    private String Tag = getClass().getSimpleName();
    public SpecificWidthImageView(Context context) {
        super(context);
    }

    public SpecificWidthImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpecificWidthImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public void setOnChangeGridViewSize(ChangeGridViewSize listenSize){
//        this.listenSize = listenSize;
//    }
//
//    public ChangeGridViewSize getListenSize(){
//        return listenSize;
//    }
    @Override
    public void updataSize(float scale) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureHeight(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int result=0; //结果
        int specMode=MeasureSpec.getMode(heightMeasureSpec);
        int specSize=MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:  // 子容器可以是声明大小内的任意大小
                Log.e(Tag, "子容器可以是声明大小内的任意大小");
                Log.e(Tag, "大小为:"+specSize);
                result=specSize;
                break;
            case MeasureSpec.EXACTLY: //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.  比如EditTextView中的DrawLeft
                Log.e(Tag, "父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间");
                Log.e(Tag, "大小为:"+specSize);
                result=specSize;
                break;
            case MeasureSpec.UNSPECIFIED:  //父容器对于子容器没有任何限制,子容器想要多大就多大. 所以完全取决于子view的大小
                Log.e(Tag, "父容器对于子容器没有任何限制,子容器想要多大就多大");
                Log.e(Tag, "大小为:"+specSize);
                result=1500;
                break;
            default:
                break;
        }
        return result;
    }
}
