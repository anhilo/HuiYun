package com.kuanguang.huiyun.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.model.TestModel;

import java.util.List;

/**
 * Created by
 */

public class MainCouponAdapter extends BaseQuickAdapter<TestModel> {
    private Context ct;


    public MainCouponAdapter(Context ct,List<TestModel> data) {
        super(R.layout.item_main_coupon_gift, data);
        this.ct = ct;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final TestModel item) {

        if(item.statue == 1){//礼品券
            helper.setText(R.id.tv_statue,"礼品券").setBackgroundRes(R.id.tv_statue,R.drawable.shape_lettle_green_radiu);
            helper.setVisible(R.id.img_statue_gift,true).setVisible(R.id.tv_discount_price,true)
                    .setVisible(R.id.tv_validity,false).setVisible(R.id.lin_dis_cash,false);
            helper.getView(R.id.img_path).setVisibility(View.VISIBLE);
            helper.setBackgroundColor(R.id.tv_recevice, ContextCompat.getColor(ct,R.color.green));
        }else if(item.statue == 2){//折扣券
            helper.setText(R.id.tv_statue,"折扣券").setBackgroundRes(R.id.tv_statue,R.drawable.shape_lettle_blue_radiu);
            helper.setVisible(R.id.img_statue_gift,false).setVisible(R.id.tv_discount_price,false)
                    .setVisible(R.id.tv_validity,true).setVisible(R.id.lin_dis_cash,true);
            helper.setBackgroundColor(R.id.tv_recevice,ContextCompat.getColor(ct,R.color.theme_bar));
            helper.getView(R.id.img_path).setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tv_yuan,ContextCompat.getColor(ct,R.color.theme_bar)).setTextColor(R.id.tv_price,ContextCompat.getColor(ct,R.color.theme_bar));
            helper.setText(R.id.tv_rule,"满699元可用");
        }else if(item.statue == 3){//代金券
            helper.setText(R.id.tv_statue,"代金券").setBackgroundRes(R.id.tv_statue,R.drawable.shape_lettle_yellow_radiu);
            helper.setVisible(R.id.img_statue_gift,false).setVisible(R.id.tv_discount_price,false)
                    .setVisible(R.id.tv_validity,true).setVisible(R.id.lin_dis_cash,true);
            helper.getView(R.id.img_path).setVisibility(View.INVISIBLE);
            helper.setBackgroundColor(R.id.tv_recevice,ContextCompat.getColor(ct,R.color.tv_price));
            helper.setTextColor(R.id.tv_yuan,ContextCompat.getColor(ct,R.color.tv_price)).setTextColor(R.id.tv_price,ContextCompat.getColor(ct,R.color.tv_price));
            helper.setText(R.id.tv_rule,"限类别使用");
        }

        if (item.isExplan){
            helper.setVisible(R.id.lin_coupon_info,true);
            helper.setImageResource(R.id.img_arrow,R.mipmap.yhq_shouqi_icon);
        }else {
            helper.setVisible(R.id.lin_coupon_info,false);
            helper.setImageResource(R.id.img_arrow,R.mipmap.yhq_zhankai_icon);
        }

        helper.setOnClickListener(R.id.rel_coupon_info, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.isExplan = !item.isExplan;
                notifyItemChanged(helper.getAdapterPosition());
            }
        });
    }
}
