package com.kuanguang.huiyun.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.base.BaseFragment;
import com.kuanguang.huiyun.model.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by
 */

public class CouponFragment extends BaseFragment{
    @BindView(R.id.tabs)
    CommonTabLayout tabs;
    @BindView(R.id.vp)
    ViewPager vp;

    private String[] mTitles = {"礼品券","折扣券","代金券"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initCreat() {
        for (String str : mTitles){
            mTabEntities.add(new TabEntity(str, 0, 0));
            mFragments.add(SimpleCardFragment.getInstance(str));
        }
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(mAdapter);
        tabs.setTabData(mTabEntities);

        tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabs.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
