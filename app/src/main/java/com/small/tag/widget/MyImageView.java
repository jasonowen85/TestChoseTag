package com.small.tag.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jasonwen on 2017/4/23.
 */
public class MyImageView extends ImageView implements ChangeGridViewSize{
    private ChangeGridViewSize listenSize;
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnChangeGridViewSize(ChangeGridViewSize listenSize){
        this.listenSize = listenSize;
    }

    public ChangeGridViewSize getListenSize(){
        return listenSize;
    }
    @Override
    public void updataSize(float scale) {

    }
}
