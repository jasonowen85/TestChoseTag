package com.small.tag.adapter;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.small.tag.widget.MyJazzyViewPager;

import java.util.List;

/**
 * 作者：jason on 2017/7/24 0024 11:53
 * 邮箱：jianglu@eims.com.cn
 * 说明：
 */
public class ImageSwitchAdapter extends BasePagerAdapter<Integer> {
    protected MyJazzyViewPager mViewPager;

    public ImageSwitchAdapter(Activity activity, List<Integer> list, MyJazzyViewPager mViewPager) {
        super(activity, list);
        this.mViewPager = mViewPager;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(list.get(position));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        container.addView(imageView);
        mViewPager.setObjectForPosition(imageView, position);
        return imageView;
    }

}
