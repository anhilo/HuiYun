package com.kuanguang.huiyun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kuanguang.huiyun.fragment.ActiveFragment;
import com.kuanguang.huiyun.fragment.CouponFragment;
import com.kuanguang.huiyun.fragment.HomeFragment;
import com.kuanguang.huiyun.fragment.MyFragment;


/**
 * MyFragmentAdapter
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CouponFragment();
            case 2 :
                return new ActiveFragment();
            case 3 :
                return new MyFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
