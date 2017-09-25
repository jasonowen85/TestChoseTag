package com.small.tag.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.small.tag.adapter.ImageGridViewAdapter;

/**
 * 作者：lyx on 2017/4/22 0022 12:25
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class PixToDipUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void setListViewHeightBasedOnChildren(GridView listView, int column) {
        // 获取listview的adapter
        ImageGridViewAdapter listAdapter = (ImageGridViewAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        listAdapter.notifyDataSetChanged();

        // 固定列宽，有多少列
        int col = column;//listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listView.getChildAt(i);
            if (null == listItem) continue;
            listItem.measure(0, 0);
            // 获取item的高度和 行数越多 这个差距就越大....
            totalHeight += listItem.getMeasuredHeight();
                    //+PixToDipUtil.dip2px(listView.getContext(),1);
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置高度
        params.height = totalHeight;
        Log.d("jiang", "总高度 = " + totalHeight);
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
//        listView.measure(0,0);
    }
}
