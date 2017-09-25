package com.small.tag.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.small.tag.R;
import com.small.tag.entity.PhotoBean;
import com.small.tag.manager.FileManager;
import com.small.tag.manager.LogoManager;
import com.small.tag.utils.LogUtil;
import com.small.tag.utils.PixToDipUtil;
import com.small.tag.utils.ViewHolderUtil;
import com.small.tag.widget.ChangeGridViewSize;
import com.small.tag.widget.MostGridView;
import com.small.tag.widget.MyImageView;

import java.io.File;
import java.util.List;

/**
 * 作者：jianglu on 2017/4/20 0020 16:41
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class ImageGridViewAdapter extends BaseP2pAdapter<PhotoBean.BidSupervisor> {
    public ImageGridViewAdapter(Context ct) {
        super(ct);
    }

    public ImageGridViewAdapter(Context ct, List<PhotoBean.BidSupervisor> list) {
        super(ct, list);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_sigle_image_info, null);
        }
        PhotoBean.BidSupervisor bean = list.get(position);
        MyImageView iMag = ViewHolderUtil.get(convertView, R.id.iv_item_cars);
        String imgUrl = "http://p2p-4.test3.wangdai.me" +  bean.getUrl();
        LogUtil.e(imgUrl);
        LogoManager.setImageViewBitmap(ct, imgUrl, iMag);

        WindowManager wm = (WindowManager) ct.getSystemService(Context.WINDOW_SERVICE);
        int width = (int) (wm.getDefaultDisplay().getWidth() * 0.5 );    //宽度设置为屏幕的
        int gViewWidth = width - PixToDipUtil.dip2px(ct, 20);
        iMag.setMaxWidth(gViewWidth);
        iMag.setMaxHeight(gViewWidth);
        iMag.setOnChangeGridViewSize(new ChangeGridViewSize() {
            @Override
            public void updataSize(float scale) {
                PixToDipUtil.setListViewHeightBasedOnChildren((GridView) parent, 2);
            }
        });

        return convertView;
    }


}
