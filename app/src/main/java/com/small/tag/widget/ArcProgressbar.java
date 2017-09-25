package com.small.tag.widget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 弧形进度条，主要用投标页面的年化收益显示
 *
 * @author weiyunchao
 */

public class ArcProgressbar extends View {

    private int bgStrokeWidth = 20;
    private int barStrokeWidth = 20;
    private int bgColor = Color.WHITE;
    private float progress = 0;
    private int inStartAngle = 140;
    private int inEndAngle = 260;
    private int outStartAngle = 140;
    private int outEndAngle = 260;
    private Paint mPaintSmallBg = null;
    private Paint mPaintBg = null;
    private Paint mPaintCircle = null;
    private RectF rectBg = null;
    private RectF outRectBg = null;
    private RectF smalloutRectBg = null;
    protected float mCenterX;//圆心X轴坐标
    protected float mCenterY;//圆心Y轴坐标
    protected float mRadius;//半径
    private int diameter;//
    private int angleOfMoveCircle = 140;// 移动小园的起始角度。

    public ArcProgressbar(Context context) {
        super(context);
    }

    public ArcProgressbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        disableHardwareRendering(this);
        init(canvas);
    }

    public static void disableHardwareRendering(View v) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            v.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    private void init(Canvas canvas) {

        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        int height = mDisplayMetrics.heightPixels;

        // 画弧形的矩阵区域。
        canvas.drawColor(Color.TRANSPARENT);
        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setColor(bgColor);
        mPaintCircle.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setStyle(Style.FILL_AND_STROKE);
        mPaintCircle.setSubpixelText(true);
        BlurMaskFilter bmf = new BlurMaskFilter(100f, Blur.SOLID);
        mPaintCircle.setMaskFilter(bmf);

//        int arcRadius = diameter / 2;
//        canvas.drawCircle(
//                (float) ((mCenterX) + (arcRadius+25)
//                        * Math.cos(angleOfMoveCircle * 3.14 / 180)),
//                (float) (mCenterY + (arcRadius+25)
//                        * Math.sin(angleOfMoveCircle * 3.14 / 180)),
//                3, mPaintCircle);// 小圆
        // 外弧形前景。
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Style.STROKE);
        mPaintBg.setStrokeWidth(3);
        mPaintBg.setColor(Color.TRANSPARENT);
        mPaintBg.setStrokeCap(Paint.Cap.ROUND); // 设置圆角

        // 外弧形背景。
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Style.STROKE);
        mPaintBg.setStrokeWidth(3);
        mPaintBg.setColor(Color.WHITE);
        mPaintBg.setAlpha(200);
        mPaintBg.setStrokeJoin(Paint.Join.ROUND);
        mPaintBg.setStrokeCap(Paint.Cap.ROUND); // 设置圆角
        if(progress != 0.00){
            canvas.drawArc(outRectBg, outStartAngle, outEndAngle * progress / 100, false, mPaintBg);
        }


        // 内弧形背景。
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Style.STROKE);
        mPaintBg.setStrokeWidth(bgStrokeWidth);
        mPaintBg.setColor(Color.WHITE);
        mPaintBg.setAlpha(100);
        mPaintBg.setStrokeCap(Paint.Cap.ROUND); // 设置圆角
        canvas.drawArc(rectBg, inStartAngle, inEndAngle, false, mPaintBg);
        // 内弧形前景。
        mPaintSmallBg = new Paint();
        mPaintSmallBg.setAntiAlias(true);
        mPaintSmallBg.setStyle(Style.STROKE);
        mPaintSmallBg.setColor(Color.WHITE);
        mPaintSmallBg.setAlpha(200);
        mPaintSmallBg.setStrokeWidth(barStrokeWidth);
        mPaintSmallBg.setStrokeCap(Paint.Cap.ROUND); // 设置圆角
        if(progress != 0.00){
            canvas.drawArc(rectBg, inStartAngle, inEndAngle * progress / 100, false, mPaintSmallBg);
        }


    }

    public void setProgress(float progress) {
        this.progress = progress;
//		  angleOfMoveCircle += progress;
//		  System.out.println(progress);
//		  if (progress > outEndAngle) {
//			  this.progress = 0;
//			  angleOfMoveCircle = outEndAngle;
//		  }

        invalidate();
    }

    private void updateDimensions(int width, int height) {
        // Update center position
        mCenterX = width / 2.0f;
        mCenterY = height / 2.0f;

        // Find shortest dimension
        diameter = Math.min(width, height) - 56;
        rectBg = new RectF(mCenterX - diameter / 2, 50, mCenterX + diameter / 2, diameter + 50);
        outRectBg = new RectF(mCenterX - diameter / 2 - 25, 25, mCenterX + diameter / 2 + 25, diameter + 75);

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (width > height)
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        else
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        updateDimensions(getWidth(), getHeight());
    }

}
