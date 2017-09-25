package com.small.tag;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.small.tag.manager.TitleManager;
import com.small.tag.utils.L;
import com.small.tag.utils.SystemStatusUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import qiu.niorgai.StatusBarCompat;

/**
 * 基本acitivity所有的activity父类，在这里实现一些公共的功能
 *1，实例化context,Resources,LayoutInflater,TitleManager供子类使用
 * 2，监听灭屏时间，如果超过5分钟退出登陆，再次打开跳转到手势密码页面进行登陆
 * @author weiyunchao
 */
public class BaseActivity extends FragmentActivity {
    protected String TAG = getClass().getSimpleName();
    protected int time;// 灭屏时间
    protected Timer timer;// 倒计时定时器，超过5分钟计算重新登录，不超过，输完手势密码直接进入退出时的页面
    protected Activity context;

    private boolean DownHomeKey;//是否按home键灭屏
    protected Resources rs;

    protected LayoutInflater inflater;
    protected boolean isSetStatusBar = true;
    protected TitleManager titleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        time = 60;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        context = this;
        rs = this.getResources();
        inflater = LayoutInflater.from(this);
        titleManager = new TitleManager(this);

        if(enableEventBus()){
            EventBus.getDefault().register(this);
        }

        if(getLayoutRes() != 0){
            setContentView(getLayoutRes());
            ButterKnife.bind(this);
            if(isSetStatusBar){  //只有19以上才起效过
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//                    SystemStatusUtil.setTranslucentStatus(this, R.color.qh_color_theme);//
                    StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this,R.color.qh_color_theme));
                }
            }
        }

        L.i(TAG, "onCreate is  run");
        initView();
    }

    protected String getRunningActivityName() {
        String contextString = context.toString();
        return contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
    }


    /**
     * 返回布局文件
     * @return
     */
    protected int getLayoutRes(){
        return 0;
    }

    protected void initView(){

    }

    protected boolean enableEventBus(){
        return false;
    }


    protected <T extends View> T find(int resId){
        return (T) findViewById(resId);
    }

    protected void setText(int id,String txt){
        ((TextView) find(id)).setText(txt);
    }


    /**
     * 注册Home键的监听
     */
    protected void registerHomeListener() {

    }

    public interface TimerListener{
        public void listener();

    }


    /**
     * 倒计时定时器，灭屏超过5分钟重新登录
     */
    protected void timerManager(final TimerListener timerListener){
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                timerListener.listener();
            }
        }, 1000, 1000);
    }


    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
        registerHomeListener();
        L.i(TAG, "onResume is  run");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        if (DownHomeKey) {
//            if (time == 0) {
//                app.exit();
//                app.setActivity(null);
//                time = 300;
//                UiManager.switcher(this,UnlockGesturePasswordActivity.class);
//            } else {
//                if (timer != null) {
//                    timer.cancel();
//                }
//
//            }
//
//            DownHomeKey = false;
//        }
        L.i(TAG, "onRestart is  run");
    }

    @Override
    protected void onPause() {
        super.onPause();

        L.i(TAG, "onPause is  run");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }

        if(enableEventBus()){
            EventBus.getDefault().unregister(this);
        }

        ButterKnife.unbind(this);
        L.i(TAG, "onDestroy is  run");
    }
}
