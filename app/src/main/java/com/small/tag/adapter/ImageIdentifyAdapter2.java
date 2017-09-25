package com.small.tag.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.small.tag.R;
import com.small.tag.entity.PhotoBean;
import com.small.tag.manager.LogoManager;
import com.small.tag.utils.LogUtil;
import com.small.tag.utils.PixToDipUtil;
import com.small.tag.utils.ViewHolderUtil;
import com.small.tag.widget.AutoNextLinearLayout;
import com.small.tag.widget.MostGridView;
import com.small.tag.widget.SpecificWidthImageView;

import java.util.List;

/**
 * 作者：jianglu on 2017/4/20 0020 16:41
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class ImageIdentifyAdapter2 extends BaseP2pAdapter<PhotoBean> {
    public ImageIdentifyAdapter2(Context ct, List<PhotoBean> list) {
        super(ct, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView ){
            convertView= inflater.inflate(R.layout.item_sigle_image_carsinfo, null);
        }
        PhotoBean bean = list.get(position);
        TextView tvTitle  = ViewHolderUtil.get(convertView, R.id.tv_title_name);
        tvTitle.setText(bean.getAdjuest().getName());
        WindowManager wm = (WindowManager) ct.getSystemService(Context.WINDOW_SERVICE);
        int width = (int) (wm.getDefaultDisplay().getWidth() * 0.5 );    //宽度设置为屏幕的
        //imageview 的宽度  根据屏幕大小的一半 计算 预留margin空间
        int gViewWidth = width - PixToDipUtil.dip2px(ct, 21);
        AutoNextLinearLayout gView = ViewHolderUtil.get(convertView, R.id.gv_item_carMostGridViews);

        for (int i = 0; i<bean.getBidSupervisor().size(); i++){
            ImageView iv  = null;
            if(null == gView.getChildAt(i)){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gViewWidth
                        , LinearLayout.LayoutParams.WRAP_CONTENT);
                iv= new ImageView(ct);
                iv.setLayoutParams(params);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                gView.addView(iv);
            } else {
                iv =(ImageView) gView.getChildAt(i);
            }
            String imgUrl = "http://p2p-4.test3.wangdai.me/"+ bean.getBidSupervisor().get(i).getUrl();
            Log.e("jiang", imgUrl + "   gView  width = " + gView.getWidth() + "iv width = " + iv.getWidth());

            LogoManager.setImageViewBitmap(ct, imgUrl, iv);
        }

        //处理缓存 多余显示;
//        int size = bean.getBidSupervisor().size();
//        if (bean.getBidSupervisor().size()< gView.getChildCount()){
//            int count = gView.getChildCount();
//            for (int i=size; i<count; i++){
//                if (null !=gView.getChildAt(i))
//                    gView.removeViewAt(i);
//            }
//        }
        return convertView;
    }
}
