package com.small.tag.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


import com.small.tag.R;
import com.small.tag.activity.CustomHorizontalActivity;
import com.small.tag.activity.PdfViewActivity;
import com.small.tag.activity.TestAdjustResizeActivity;
import com.small.tag.adapter.ImageIdentifyAdapter2;
import com.small.tag.entity.PhotoBean;
import com.small.tag.main.ConsultBaseFragment;
import com.small.tag.service.DownloadService;
import com.small.tag.utils.LogUtil;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasonwen on 2017/3/29.
 */
public class QueryFragment extends ConsultBaseFragment {

    private ListView mListView;
    private ImageView iv_ball;
    private View mCircleProgress;
    private BroadcastReceiver receiver;
    //private PdfWebView mPdfWebView;
    //    private ReaderView mDocView;
//    private MuPDFCore mCore;
//    private MuPDFPageAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.query_fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
//        initDatas(view);
        ((ImageView) view.findViewById(R.id.iv_object_animation)).setOnClickListener(this);
        iv_ball = (ImageView) view.findViewById(R.id.iv_my_ball);
        mCircleProgress = view.findViewById(R.id.custom_circle_progress);
        iv_ball.setOnClickListener(this);

        mListView = (ListView) view.findViewById(R.id.listview_main);
        Button btnPdfView = (Button) view.findViewById(R.id.btn_check_pdf);
        btnPdfView.setOnClickListener(this);
//        mPdfWebView = (PdfWebView) view.findViewById(R.id.my_pdf_web_view);
    }

//    private void setPdfView(String path) {
//        MuPDFCore core = null;
//        try{
//            core = new MuPDFCore(mActivity,path);
//        }catch (Exception e){
//
//        }
//        MuPDFPageAdapter adapter = new MuPDFPageAdapter(mActivity, core);
//        mListView.setAdapter(adapter);
//    }

//    private void setLoadPdfView(String mPdfFilePath){
//        mDocView = new ReaderView(mActivity);
//        try {
//            mCore = new MuPDFCore(mActivity,mPdfFilePath);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        if (mCore!=null&&mCore.countPages()==0){
//            mCore = null;
//        }
//        if (null == mCore){
//            Toast.makeText(mActivity,"文件已损坏，无法打开",Toast.LENGTH_LONG).show();
//            return;
//        }
//         mAdapter =  new MuPDFPageAdapter(mActivity,mCore);
//        mDocView.setAdapter(mAdapter);
//        mListView.addFooterView(mDocView);
//    }
//
//    private void initPdfView(String mPdfFilePath){
//        Intent intent = new Intent(mActivity, PdfViewActivity.class);
//        intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, mPdfFilePath);
//        startActivity(intent);
//    }

    private void initDatas(View view) {

        mListView = (ListView) view.findViewById(R.id.ls_show_identify);
        List<PhotoBean> mPhotoDatas = new ArrayList<>();
        String url = "/data/attachments/fcc04fb8-3400-4ffa-a259-45363862481c";

        String url2 = "data/attachments/5de6a80e-8473-42ce-9c0e-94b27bae00fa";
        PhotoBean pBean = new PhotoBean();


        PhotoBean.BidAuditSubject auditSubjectBean = pBean.new BidAuditSubject("身份证");
        pBean.setAdjuest(auditSubjectBean);

        PhotoBean.BidSupervisor bidSupervisorBean = pBean.new BidSupervisor("jpg", url);
        PhotoBean.BidSupervisor bidSupervisorBean2 = pBean.new BidSupervisor("jpg", url2);


        List<PhotoBean.BidSupervisor> mList = new ArrayList<PhotoBean.BidSupervisor>();
        mList.add(bidSupervisorBean);
        mList.add(bidSupervisorBean2);
        mList.add(bidSupervisorBean2);
        pBean.setBidSupervisor(mList);


        mPhotoDatas.add(pBean);

        List<PhotoBean.BidSupervisor> mList2 = new ArrayList<PhotoBean.BidSupervisor>();

        PhotoBean pBean2 = new PhotoBean();
        pBean2.setAdjuest(auditSubjectBean);
        mList2.add(bidSupervisorBean);
        mList2.add(bidSupervisorBean);
        mList2.add(bidSupervisorBean2);

        pBean2.setBidSupervisor(mList2);

        mPhotoDatas.add(pBean2);

        mPhotoDatas.add(pBean);

        mListView.setAdapter(new ImageIdentifyAdapter2(getmContext(), mPhotoDatas));
    }

    @Override
    protected void initData() {
        final String path = "http://ry.tunnel.qydev.com/data/pdf/contract/ea7a4d66-4479-4cba-8599-75327e22ba37.pdf";

        //setPdfView(pdfFile);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String fileName = path.split("/")[path.split("/").length - 1];
                String pdfPath = Environment.getExternalStorageDirectory() + "/download/" + fileName;
                File file = new File(Environment.getExternalStorageDirectory() + "/download/" + fileName);
                LogUtil.i("下载完成 解析pdf文件  == " + file.getAbsolutePath() + "   filename = " + fileName);
//                if(file.exists()) mPdfWebView.loadFilePath(pdfPath);
//                if (file.exists()) {
//                    Uri path = Uri.fromFile(file);
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setDataAndType(path, "application/pdf");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    try {
//                        startActivity(intent);
//                    }
//                    catch (ActivityNotFoundException e) {
//                        T.showShort(context,
//                                "No Application Available to View PDF"
//                        );
//                    }
//
//                }
                //setPdfView(pdfPath);
                //setLoadPdfView(pdfPath);
//                initPdfView(pdfPath);

            }
        };
        mActivity.registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

//        startDownload(path);
    }

    private void startDownload(String uri) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (uri != null) bundle.putString("pdfPath", uri);
        intent.putExtras(bundle);
        intent.setClass(mActivity,DownloadService.class);
        mActivity.startService(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_object_animation:
//                startAnimator(view);
                Intent intent = new Intent(mActivity, CustomHorizontalActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_my_ball:
                startDrop();

            case R.id.btn_check_pdf:
                startActivity(new Intent(mActivity, TestAdjustResizeActivity.class));
                //startActivity(new Intent(mActivity, PdfViewActivity.class));
                break;
        }

    }

    private void startDrop() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)mActivity).getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        LogUtil.i("ball height = " + iv_ball.getHeight() + "    "+height);
        startValueAnimator(0, height-iv_ball.getHeight()/2);
    }


    public void startAnimator(final View v){
        ObjectAnimator objectAnimator = ObjectAnimator
                .ofFloat(v, "xyz",0.0f,1.0f)
                .setDuration(1000);
        objectAnimator.start();

        objectAnimator .addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Float scaleValue = (Float) animation.getAnimatedValue();
                        v.setScaleX(scaleValue);
                        v.setScaleY(scaleValue);
                        v.setAlpha(scaleValue);
                    }
                });
    }

    public void startValueAnimator(int start, int end){
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(start,end);
        valueAnimator.setDuration(2000);
        valueAnimator.setTarget(iv_ball);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float drop = (Float)valueAnimator.getAnimatedValue();
                iv_ball.setTranslationY(drop);
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity.unregisterReceiver(receiver);
    }
}
