package com.kuanguang.huiyun.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ScrollView;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.kuanguang.huiyun.adapter.BannerHolderView;
import com.kuanguang.huiyun.adapter.MainActiveAdapter;
import com.kuanguang.huiyun.adapter.MainCouponAdapter;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.base.BaseFragment;
import com.kuanguang.huiyun.model.TestModel;
import com.kuanguang.huiyun.view.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页fragment
 */

public class HomeFragment extends BaseFragment{
    @BindView(R.id.rv_active)
    RecyclerView rv_active;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;
    @BindView(R.id.banner)
    ConvenientBanner convenientBanner;

    private MainCouponAdapter mainCouponAdapter;
    private MainActiveAdapter mainActiveAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initCreat() {
        initBannber();
    }

    private void initBannber() {
        List<TestModel> list = new ArrayList<>();
        list.add(new TestModel());
        list.add(new TestModel());
        list.add(new TestModel());

        mainCouponAdapter = new MainCouponAdapter(list);
        rv_coupon.setLayoutManager(new ScrollLinearLayoutManager(ct));
        rv_coupon.setAdapter(mainCouponAdapter);
        rv_coupon.getItemAnimator().setChangeDuration(300);
        rv_coupon.getItemAnimator().setMoveDuration(300);

        mainActiveAdapter = new MainActiveAdapter(list);
        rv_active.setLayoutManager(new ScrollLinearLayoutManager(ct));
        rv_active.setAdapter(mainActiveAdapter);

        convenientBanner.setPages(
                new CBViewHolderCreator<BannerHolderView>() {
                    @Override
                    public BannerHolderView createHolder() {
                        return new BannerHolderView();
                    }
                }, list)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.shap_circle_f, R.drawable.shap_circle_t})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(4000);
        try {
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + FlipHorizontalTransformer.class.getSimpleName());
            ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
            convenientBanner.getViewPager().setPageTransformer(true,transforemer);
            convenientBanner.setScrollDuration(2000);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        convenientBanner.setManualPageable(list.size() == 1 ? false : true);
        convenientBanner.setCanLoop(list.size() == 1 ? false : true);

        scrollView.smoothScrollTo(0,0);
    }
}
