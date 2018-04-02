package com.kuanguang.huiyun.adapter;

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


    public MainCouponAdapter( List<TestModel> data) {
        super(R.layout.item_main_coupon, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final TestModel item) {

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
