package com.kuanguang.huiyun.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kuanguang.huiyun.R;

/**
 * Glide 公共方法
 */

public class GUtils {


    /** 普通设置图片方法 */
    public static void display(Context ct, String data, ImageView img){
        Glide.with(ct)
                .load(data)
                .transform(new GlideRoundTransform(ct,20))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.default_img)
                .into(img);
    }

    /** 设置圆角弧度图片方法 */
    public static void display(Context ct, String data, ImageView img,int radiu){
        Glide.with(ct)
                .load(data)
                .transform(new GlideRoundTransform(ct,radiu))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.default_img)
                .into(img);
    }

    /** 设置圆形图片方法 */
    public static void display(Context ct, String data, ImageView img,boolean isCircle){
        Glide.with(ct)
                .load(data)
                .transform(isCircle ? new GlideRoundTransform(ct) : new GlideRoundTransform(ct,0))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.default_img)
                .into(img);
    }

}
