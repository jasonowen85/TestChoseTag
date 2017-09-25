package com.small.tag.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.small.tag.R;
import com.small.tag.entity.PhotoBean;
import com.small.tag.utils.ViewHolderUtil;
import com.small.tag.widget.MostGridView;

import java.util.List;

/**
 * 作者：jianglu on 2017/4/20 0020 16:41
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class ImageIdentifyAdapter extends BaseP2pAdapter<PhotoBean> {
    public ImageIdentifyAdapter(Context ct, List<PhotoBean> list) {
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
        MostGridView gView = ViewHolderUtil.get(convertView, R.id.gv_item_carMostGridViews);
        ImageGridViewAdapter mGridAdapter = new ImageGridViewAdapter(ct, bean.getBidSupervisor());
        gView.setAdapter(mGridAdapter);

        return convertView;
    }
}
