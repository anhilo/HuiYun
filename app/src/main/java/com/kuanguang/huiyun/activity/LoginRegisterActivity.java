package com.kuanguang.huiyun.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.base.BaseActivity;
import com.kuanguang.huiyun.fragment.LoginRegisterFragment;
import com.kuanguang.huiyun.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by
 */

public class LoginRegisterActivity extends BaseActivity {
    @BindView(R.id.tabs)
    SlidingTabLayout tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private String[] mTitles = {"登录","注册"};
    private List<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_register;
    }

    @Override
    protected String setBarTitle() {
        return null;
    }

    @Override
    public void initCreat() {
        StatusBarUtils.transparencyBar(LoginRegisterActivity.this);
        for (String str : mTitles){
            mFragments.add(LoginRegisterFragment.getInstance(str));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabs.setViewPager(viewPager);
    }

    @OnClick({R.id.rel_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rel_back:
                finish();
                break;
        }
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
