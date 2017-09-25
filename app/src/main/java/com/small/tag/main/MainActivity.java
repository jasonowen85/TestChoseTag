package com.small.tag.main;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.small.tag.BaseActivity;
import com.small.tag.R;
import com.small.tag.fragment.AddFragment;
import com.small.tag.fragment.QueryFragment;
import com.small.tag.utils.LogUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    //mCurrent 为0 表示首页  为1 表示 查询
    private int mCurrtent;
    private FragmentManager mFragmenManager;
    private FragmentTransaction mFragmentTransaction;

    private GoogleApiClient client;
    private AddFragment mHomeFragment;
    private QueryFragment mQueryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.rg_test_tag);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                LogUtil.i("test id = " + i);
                switch (i) {
                    case R.id.rb_add_item:
                        LogUtil.i("test id = " + R.id.rb_add_item);
                        switchFragment(i);
//                        radioGroup.check(R.id.rb_add_item);
                        break;
                    case R.id.rb_query_item:
                        LogUtil.i("test id = " + R.id.rb_query_item);
                        switchFragment(i);
                        break;
                }
            }
        });
        //第一步; ft
        mFragmenManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmenManager.beginTransaction();
        mQueryFragment = new QueryFragment();
        //第二部 add  show
        mFragmentTransaction.add(R.id.fl_main_content, mQueryFragment);
        // 3  commit()
        mFragmentTransaction.commit();

        RadioButton rbAdd = (RadioButton) findViewById(R.id.rb_add_item);
        rbAdd.setOnClickListener(this);
//        rbAdd.setChecked(true);
        ((RadioButton) findViewById(R.id.rb_query_item)).performClick();
        switchFragment( R.id.rb_query_item);
        ((RadioButton) findViewById(R.id.rb_query_item)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    private void switchFragment(int id){
        if(mCurrtent ==id){
            return;
        }
        FragmentTransaction ft = mFragmenManager.beginTransaction();
        //不相等的话 就做切换动作; 隐藏掉当前的fragment
        if(mCurrtent == R.id.rb_add_item){
            ft.hide(mHomeFragment);
        } else {
            if(mQueryFragment !=null){
                ft.hide(mQueryFragment);
            }
        }
        mCurrtent = id;

        if(mCurrtent == R.id.rb_add_item){
            if (mHomeFragment == null){
                mHomeFragment = new AddFragment();
                ft.add(R.id.fl_main_content, mHomeFragment);
            }else {
                ft.show(mHomeFragment);
            }
        }else {
            if (mQueryFragment == null){
                mQueryFragment = new QueryFragment();
                ft.add(R.id.fl_main_content, mQueryFragment);
            }else {
                ft.show(mQueryFragment);
            }
        }
        ft.commit();
        mFragmenManager.executePendingTransactions();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
