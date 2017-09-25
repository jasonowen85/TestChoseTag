package com.small.tag.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.small.tag.R;
import com.small.tag.adapter.BasePagerAdapter;
import com.small.tag.adapter.ImageSwitchAdapter;
import com.small.tag.widget.MyJazzyViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 作者：jason on 2017/7/24 0024 11:05
 * 邮箱：jianglu@eims.com.cn
 * 说明：
 */
public class CustomHorizontalActivity extends FragmentActivity {
    private List<Integer> imageList = new ArrayList<>(Arrays.asList(R.drawable.guide01,
            R.drawable.guide02,R.drawable.guide03,R.drawable.guide04,R.drawable.rotation));
    private MyJazzyViewPager mJazzyViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal);
        mJazzyViewPager = (MyJazzyViewPager) findViewById(R.id.my_jazzy_view_pager);
        mJazzyViewPager.setAdapter(new ImageSwitchAdapter(this,imageList, mJazzyViewPager));
    }


}
