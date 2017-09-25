package com.small.tag.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.EditText;

import com.small.tag.R;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * 作者：jason on 2017/7/24 0024 14:19
 * 邮箱：jianglu@eims.com.cn
 * 说明：
 */
public class PayInputPasswordView extends EditText {

    private Context mContext;
    /**
     * 第一个圆开始绘制的圆心坐标
     */
    private float startX;
    private float startY;

    /**
     * view的高度
     */
    private int height;
    private int width;

    /**
     * 最大输入字符个数
     */
    private int maxCount = 6;
    /**
     * 填充的圆的颜色
     */
    private int circleColor = Color.BLACK;
    /**
     * 底部线的颜色
     */
    private int bottomLineColor=Color.GRAY;
    /**
     * 圆半径
     */
    private int radius;
    /**
     * 分割线的宽度
     */
    private int divideLineWidth = 2;
    /**
     * 分割线的颜色
     */
    private int divideLineColor =Color.GRAY;
    /**
     * 分割线的颜色
     */
    private int borderColor = Color.GRAY;

    private int psdType;
    /**
     * 矩形边框的圆角
     */
    private int rectAngle = 0;

    private RectF rectF = new RectF();
    private int divideLineWStartX;
    private int bottomLineLength;
    private Paint circlePaint;
    private Paint bottomLinePaint;
    private Paint borderPaint;
    private Paint divideLinePaint;


    public PayInputPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PayInputPasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext= context;
        getAtt(attrs);
        initPaint();

        this.setBackgroundColor(Color.TRANSPARENT);
    }

    public PayInputPasswordView(Context context) {
        super(context);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        circlePaint = getPaint(5, Paint.Style.FILL, circleColor);

        bottomLinePaint = getPaint(2, Paint.Style.FILL, bottomLineColor);

        borderPaint = getPaint(3, Paint.Style.STROKE, borderColor);

        divideLinePaint = getPaint(divideLineWidth, Paint.Style.FILL, borderColor);

    }

    /**
     * 设置画笔
     *
     * @param strokeWidth 画笔宽度
     * @param style       画笔风格
     * @param color       画笔颜色
     * @return
     */
    private Paint getPaint(int strokeWidth, Paint.Style style, int color) {
        Paint paint = new Paint(ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(style);
        paint.setColor(color);
        paint.setAntiAlias(true);

        return paint;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w/maxCount;
        height = h;
        divideLineWStartX = w / maxCount;

        startX = w / maxCount / 2; //第一个圆心的x坐标
        startY = h / 2; //第一个圆心的y坐标

        bottomLineLength = w / (maxCount + 2);

        rectF.set(0,0,width,height);

    }


    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.PayPsdInputView);
        maxCount = typedArray.getInt(R.styleable.PayPsdInputView_maxCount, maxCount);
        circleColor = typedArray.getColor(R.styleable.PayPsdInputView_circleColorInput, circleColor);
        bottomLineColor = typedArray.getColor(R.styleable.PayPsdInputView_bottomLineColor, bottomLineColor);
        radius = typedArray.getDimensionPixelOffset(R.styleable.PayPsdInputView_radius, radius);

        divideLineWidth = typedArray.getDimensionPixelSize(R.styleable.PayPsdInputView_divideLineWidth, divideLineWidth);
        divideLineColor = typedArray.getColor(R.styleable.PayPsdInputView_divideLineColor, divideLineColor);
        psdType = typedArray.getInt(R.styleable.PayPsdInputView_psdType, psdType);
        rectAngle = typedArray.getDimensionPixelOffset(R.styleable.PayPsdInputView_rectAngle, rectAngle);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(rectF, rectAngle,rectAngle,borderPaint);
    }
}
