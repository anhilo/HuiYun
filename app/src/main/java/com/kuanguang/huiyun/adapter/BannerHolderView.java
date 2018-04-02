package com.kuanguang.huiyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.model.TestModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 图片轮播适配器
 */
public class BannerHolderView implements Holder<TestModel> {

    @BindView(R.id.img_banner)
    ImageView img_banner;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_img, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, TestModel data) {
//        if (data.getImageInfo() != null){
//            XUtilsImageUtils.display(img_banner,data.getImageInfo().getImageUrl());
//        }
    }
}
