package com.small.tag.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.MuPDFPageAdapter;
import com.artifex.mupdfdemo.ReaderView;
import com.artifex.service.DownloadBindService;
import com.small.tag.R;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class PdfViewActivity extends FragmentActivity {

    private ReaderView mDocView;
    private MuPDFCore mCore;
    private MuPDFPageAdapter mAdapter;

    private boolean isBindService;
    private RelativeLayout rootView;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private LinearLayout rootProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        rootView = (RelativeLayout) findViewById(R.id.rl_root_view);

        rootProgress = (LinearLayout) findViewById(R.id.ll_progress_bar);
        progressBar = (ProgressBar) findViewById(R.id.pb_progress_bar);
        tvProgress = (TextView) findViewById(R.id.tv_progress);
        //initData();
        final String path = "http://dlsvr04.asus.com/pub/ASUS/nb/X550CC/C_eManual_X550CC_X550VB_X550VC_VER7926.pdf";
        bindService(path);
    }

    private void initPdfView(String pdfPath) {
        mDocView = new ReaderView(this);
        try {
            mCore = new MuPDFCore(this,pdfPath);
        } catch (Exception e){
            e.printStackTrace();
        }

        if (mCore!=null&&mCore.countPages()==0){
            mCore = null;
        }
        if (null == mCore){
            Toast.makeText(this,"文件已损坏，无法打开",Toast.LENGTH_LONG).show();
            return;
        }
        mAdapter = new MuPDFPageAdapter(this,mCore);
        rootView.setBackgroundColor(Color.BLACK);
        mDocView.setAdapter(mAdapter);
        rootView.addView(mDocView);
    }


    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadBindService.DownloadBinder binder = (DownloadBindService.DownloadBinder) service;
            DownloadBindService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadBindService.OnProgressListener() {
                @Override
                public void onProgress(float fraction, final String downloadPath) {
                    Log.i("下载进度：","" + fraction);
                    int progress = (int)(fraction * 100);
                    tvProgress.setText(progress+"%");
                    progressBar.setProgress(progress);
                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownloadBindService.UNBIND_SERVICE && isBindService) {
                        unbindService(conn);
                        rootView.removeAllViews();
                        isBindService = false;
                        File file = new File(downloadPath);
                        if (file.exists()){
                            initPdfView(downloadPath);
                        }
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public void bindService(String apkUrl) {
        Intent intent = new Intent(this, DownloadBindService.class);
        intent.putExtra(DownloadBindService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
        isBindService = bindService(intent, conn, BIND_AUTO_CREATE);
    }

}
