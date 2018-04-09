package com.kuanguang.huiyun.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.adapter.MainCouponAdapter;
import com.kuanguang.huiyun.base.BaseFragment;
import com.kuanguang.huiyun.model.TestModel;
import com.kuanguang.huiyun.view.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class SimpleCardFragment extends BaseFragment {
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;

    private String mTitle;
    private MainCouponAdapter mainCouponAdapter;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_simple_card;
    }

    @Override
    protected void initCreat() {
        List<TestModel> list1 = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            TestModel mo = new TestModel();
            mo.statue = 1;
            list1.add(mo);
        }

        List<TestModel> list2 = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            TestModel mo = new TestModel();
            mo.statue = 2;
            list2.add(mo);
        }

        List<TestModel> list3 = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            TestModel mo = new TestModel();
            mo.statue = 3;
            list3.add(mo);
        }

        if (mTitle.equals("礼品券")){
            mainCouponAdapter = new MainCouponAdapter(ct,list1);
        }else if(mTitle.equals("折扣券")){
            mainCouponAdapter = new MainCouponAdapter(ct,list2);
        }else if(mTitle.equals("代金券")){
            mainCouponAdapter = new MainCouponAdapter(ct,list3);
        }
        rv_coupon.setLayoutManager(new LinearLayoutManager(ct));
        rv_coupon.setAdapter(mainCouponAdapter);
        rv_coupon.getItemAnimator().setChangeDuration(300);
        rv_coupon.getItemAnimator().setMoveDuration(300);

    }
}