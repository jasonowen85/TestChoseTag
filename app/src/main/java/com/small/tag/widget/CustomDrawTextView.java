package com.small.tag.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.small.tag.utils.MiscUtil;

/**
 * 作者：lyx on 2017/5/2 0002 09:40
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class CustomDrawTextView extends View {

//    private Context mContext;
    private Paint mPaint ;
    private Paint.FontMetrics mFontMetrics;// 文本测量对象


    private String text = "英勇青铜5+abcdefg";
    private Paint mPaintLine;

//    private int mDefaultSize = MiscUtil.dipToPx(mContext, 300);

    public CustomDrawTextView(Context context) {
        this(context, null);
//        mContext = context;
    }

    public CustomDrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mContext = context;
        initPaint();
    }

    public CustomDrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomDrawTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        mContext = context;
    }

    /**
     * 初始化画笔设置
     */
    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setTextSize(90f);

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(Color.RED);
        mPaintLine.setStrokeWidth(1);

        mFontMetrics = mPaint.getFontMetrics();

        Log.d("Aige", "ascent：" + mFontMetrics.ascent);
        Log.d("Aige", "top：" + mFontMetrics.top);
        Log.d("Aige", "leading：" + mFontMetrics.leading);
        Log.d("Aige", "descent：" + mFontMetrics.descent);
        Log.d("Aige", "bottom：" + mFontMetrics.bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,300);
        }else  if (wSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(300,hSpecSize);
        }else if (hSpecMode ==  MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize,300);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //这个时候 需要TextView 居中显示  怎么办
        float textWidth = mPaint.measureText(text);
        float x = (getWidth()- textWidth)/2;

        mFontMetrics = mPaint.getFontMetrics();
        float y = getHeight()/2 + (Math.abs(mFontMetrics.ascent)-mFontMetrics.descent)/2;
        Log.d("Aige", "x：" + x   + "   y = " + (Math.abs(mFontMetrics.ascent)-mFontMetrics.descent)/2);
        canvas.drawText(text,x,y, mPaint);
        canvas.drawLine(0,y,getWidth(),y,mPaintLine);

//        canvas.drawli
    }

}
