package com.small.tag.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.small.tag.widget.MyJazzyViewPager;

import java.util.List;

/**
 * 作者：jason on 2017/7/24 0024 11:33
 * 邮箱：jianglu@eims.com.cn
 * 说明：
 */
public abstract class BasePagerAdapter<T extends Object> extends PagerAdapter {
    protected Activity activity;
    protected List<T> list;
    protected MyJazzyViewPager mViewPager;

    public BasePagerAdapter(Activity activity, List<T> list){
        this.activity = activity;
        this.list = list;
    }


    public void addData(T t){
        this.list.add(t);
        notifyDataSetChanged();
    }

    public void addData(List<T> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return list;
    }

    public void removeData(List<T> list){
        this.list.removeAll(list);
        notifyDataSetChanged();
    }

    public void clearAdapter(){
        if(null!=list&&list.size()>0){
            list.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0:list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container,position);
    }


}
