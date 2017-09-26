package com.small.tag;


import android.app.Application;
import android.content.Context;

/**
 * Created by jasonwen on 2017/3/29.
 */
public class App extends Application {

    private static App mContext ;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = (App) getApplicationContext();
    }

    public static App getContext() {
        return mContext;
    }
}
