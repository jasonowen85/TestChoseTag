

package com.small.tag.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


import com.small.tag.utils.LogUtil;
import com.small.tag.utils.PixToDipUtil;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by jasonwen on 2017/4/22.
 */
public class AutoNextLinearLayout extends LinearLayout {
    int mLeft, mRight, mTop, mBottom;
    Hashtable map = new Hashtable();

//    private TreeSet<Integer> maxHeight = new TreeSet<>() ;

    private List<Integer> mLineHeight = new ArrayList<>() ;
    private List<List<View>> mAllViews =new ArrayList<>() ;

    //每一行 展现的列数
    private int colunmNum = 2;
    //子view 之间的横向间隔 距离 dx;
    private final int widthSpaceSize = PixToDipUtil.dip2px(getContext(), 15);
    //子view  topMargin  dp;
    private final int heightSpaceTop = PixToDipUtil.dip2px(getContext(), 15);
    //子view  leftMargin  dp;
    private final int leftSpaceMargin = PixToDipUtil.dip2px(getContext(), 15);

    public AutoNextLinearLayout(Context context) {
        super(context);
    }

    public AutoNextLinearLayout(Context context, int horizontalSpacing, int verticalSpacing) {
        super(context);
    }

    public AutoNextLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mCount = getChildCount();
        int mX = 0;
        int mY = 0;
        mLeft = leftSpaceMargin;
        mRight = leftSpaceMargin;
        mTop = 0;  // heightSpaceTop;
        mBottom = 0;

        int j = 0;
        //用于记录每行的最大高度
        View lastview = null;

        int lineMaxHeight = 0;
        //用于记录行高的 key;
        int lineNum =0;
        mLineHeight.clear();
        for (int i = 0; i < mCount; i++) {
            final View child = getChildAt(i);
//            child.measure(MeasureSpec.EXACTLY, MeasureSpec.AT_MOST);
            int childSpecW = MeasureSpec.makeMeasureSpec(child.getLayoutParams().width,MeasureSpec.EXACTLY);

            measureChild(child, childSpecW,0);
            Log.i("jiang" , " heightMeasureSpec "   +MeasureSpec.getSize(heightMeasureSpec));
            LayoutParams lp = (LayoutParams) child
                    .getLayoutParams();
            // 此处增加onlayout中的换行判断，用于计算所需的高度
            int childw = child.getMeasuredWidth()  ;//  child.getLayoutParams().width;   //child.getMeasuredWidth();
            int childh = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;  ///child.getLayoutParams().height;
            Log.d("jiang" , "childview  getheight = " + childh + "    childwidth =   " +childw);
            mX += childw; // 将每次子控件宽度进行统计叠加，如果大于设定的高度则需要换行，高度即Top坐标也需重新设置

//            Position position = new Position();
//            mLeft = getPosition(i - j, i);
//            mRight = mLeft + child.getLayoutParams().width;   //child.getMeasuredWidth();
//            if (mX >= mWidth) {
            if (i != 0 && i % colunmNum == 0) {
                mX = childw;
                mY += lineMaxHeight;// +  heightSpaceTop
                j = i;
                mLeft = 0;
                mRight = mLeft +   child.getLayoutParams().width; //  child.getMeasuredWidth();
                mTop = mY ;
                lineNum++;
                //保存上一行 最大view 高度
//                mLineHeight.add(lineMaxHeight);
                lineMaxHeight = 0;

            // PS：如果发现高度还是有问题就得自己再细调了
            }
            lineMaxHeight = lineMaxHeight > childh ? lineMaxHeight : childh;
            LogUtil.i("lineMaxHeight", ""+ lineMaxHeight);
            if (lineNum == 0){
                //第一行的子view  marginTop  = 0 ;
                mTop = 0;
            }
            mBottom = mTop + lineMaxHeight;//getLayoutParams().height;
            mY = mTop; // 每次的高度必须记录 否则控件会叠加到一起
//            position.left = mLeft;
//            position.top = mTop;
//            position.right = mRight;
//            position.bottom = mBottom;
//            map.put(child, position);
            if (lineNum == 0 && i == mCount - 1) {
//                mLineHeight.add(lineMaxHeight);
            }
        }
//        int expandSpec  =  MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
//        int widthSpec  =  MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
        Log.d("jiang" , "viewgroup  height = " + mBottom + "    viewgroup  width =   " +mWidth);
        setMeasuredDimension(mWidth, mBottom);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(0, 0); // default of 1px spacing
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //可能多次调用 需要clear heightMax 可能在Onmeasure 不准确;
        mAllViews.clear();
        mLineHeight.clear();

        //获取总宽度
        int width = getWidth();

        //单行宽度和当行高度
        int lineWidth = 0;
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> childViews = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child
                    .getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            Log.d("jiang", "childview  getheight = " + childHeight + "    childwidth =   " + childWidth);

            if (i != 0 && i % colunmNum == 0) {
                //保存上一行的最大高度  以及所以的上一行的所有子view
                mLineHeight.add(lineHeight);
                mAllViews.add(childViews);
                lineHeight = 0;
                childViews = new ArrayList<View>();
            }
            //  如果不需要换行，则累加
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            childViews.add(child);
        }
        //记录最后一行的 数据
        mLineHeight.add(lineHeight);
        mAllViews.add(childViews);

        int left = getPaddingLeft();
        int top = getPaddingTop();
        // 得到总行数
        int lineNum = mAllViews.size();
        for (int i = 0; i < lineNum; i++) {
            // 每一行的所有的views
            childViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            // 遍历当前行所有的子View
            for (int j = 0; j < childViews.size(); j++) {
                View child = childViews.get(j);
                if (child.getVisibility() != View.GONE) {
                    LayoutParams lp = (LayoutParams) child
                            .getLayoutParams();
                    //计算childView的left,top,right,bottom
                    int childLeft = left + lp.leftMargin;
                    int childTop = top + lp.topMargin;
                    int childRight = childLeft + child.getMeasuredWidth();
                    int childBottom = childTop + child.getMeasuredHeight();

                    child.layout(childLeft, childTop, childRight, childBottom);

                    left += child.getMeasuredWidth() + lp.rightMargin
                            + lp.leftMargin;
                }
            }
            //换行后，重新从第一个开始，高度累加
            left = getPaddingTop();
            top += lineHeight;
        }
    }

    private class Position {
        int left, top, right, bottom;
    }

    public int getPosition(int IndexInRow, int childIndex) {
        if (IndexInRow > 0) {
            return getPosition(IndexInRow - 1, childIndex - 1) + getChildAt(childIndex - 1).getLayoutParams().width + widthSpaceSize ;
//            return getPosition(IndexInRow - 1, childIndex - 1) + getChildAt(childIndex - 1).getMeasuredWidth() + widthSpaceSize ;
        }
        return getPaddingLeft();
    }
}