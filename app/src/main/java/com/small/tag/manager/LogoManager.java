package com.small.tag.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.small.tag.R;
import com.small.tag.widget.MyImageView;


import java.io.File;

/**
 */
public class LogoManager {


    public interface getHttpUrlListener {
        void callBack(String url);
    }

//
    /**
     //     * 设置logo图标
     //     *
     //     * @param context
     //     * @param imageView
     //     */
//    public static void setLogo(final Context context, final ImageView imageView) {
//
//        if (!NetWorkUtil.isNetworkAvailable(context)) {
//            Bitmap bmp = FileManager.readerBitmap(new File(Constant.LOGO_URL));
//            if (bmp != null) {
//                imageView.setImageBitmap(bmp);
//            }
//        } else {
//            LogoManager.getUrl(context, new LogoManager.getHttpUrlListener() {
//                @Override
//                public void callBack(String url) {
//                    L.e("LogoManager", ServiceConfig.getRootUrl() + url);
//                    LogoManager.setImageViewBitmap(context, ServiceConfig.getRootUrl() + url,
//                            imageView, Constant.LOGO_URL, R.drawable.logo);
//                }
//            });
//        }
//
//    }

    /**
     * 获取网络图片并保存到本地
     */
    public static void setImageViewBitmap(final Context context, String url,
                                          final ImageView imageView, final String saveUrl, int defaultImgId) {

        Glide.with(context).load(url).asBitmap().placeholder(defaultImgId).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {

                if (resource != null) {
                    imageView.setImageBitmap(resource);
                    FileManager.saveBitmap(new File(saveUrl), resource);//保存图片
                } else {
                    Bitmap bmp = FileManager.readerBitmap(new File(saveUrl));
                    if (bmp != null) {
                        imageView.setImageBitmap(bmp);

                    }
                }

            }
        });
    }

    /**
     * 获取网络图片并保存到本地
     */
    public static void setImageViewBitmap(final Context context, final String url,final ImageView imageView) {

        Glide.with(context).load(url).asBitmap().placeholder(R.drawable.app_logo).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                String saveUrl = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + url.substring(url.lastIndexOf("/") + 1);
                //判断是否是MyImageView 实例
                boolean b = imageView.getClass().getSimpleName().equals(MyImageView.class.getSimpleName())
                        && ((MyImageView)imageView).getListenSize() !=null ;
                if (resource != null) {
                    imageView.setImageBitmap(resource);
                FileManager.saveBitmap(new File(saveUrl), resource);//保存图片
                   if (b )
                        ((MyImageView)imageView).getListenSize().
                                updataSize(resource.getHeight() / resource.getWidth());
            } else {
                Bitmap bmp = FileManager.readerBitmap(new File(saveUrl));
                if (bmp != null) {
                    imageView.setImageBitmap(bmp);
                    if (b)
                        ((MyImageView)imageView).getListenSize().
                                updataSize(resource.getHeight() / resource.getWidth());
                }
            }

            }
        });
    }
//
//    /**
//     * 网络请求获取logo图片
//     *
//     * @param context
//     * @param httpUrlListener
//     */
//    public static void getUrl(Context context, final getHttpUrlListener httpUrlListener) {
//        Map<String, String> map = new HashMap<>();
//        map.put("OPT", Constant.APP_LOGO);
//        NetWorkUtil.volleyHttpGet(context, map, new NetWorkUtil.ResponseCallBack() {
//            @Override
//            public void response(JSONObject obj) {
//                if (obj.optInt("code") == Constant.RESULT_OK) {
//                    String url = obj.optString("platformIconFileName");
//                    if (!StringUtils.isEmpty(url)) {
//                        httpUrlListener.callBack(url);
//                    }
//                }
//            }
//        },null);
//    }

}
