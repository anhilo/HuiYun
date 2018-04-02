package com.kuanguang.huiyun.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.kuanguang.huiyun.adapter.MyFragmentAdapter;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.common.AppManager;
import com.kuanguang.huiyun.base.BaseActivity;
import com.kuanguang.huiyun.utils.StatusBarUtils;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation_bottom_tab)
    PageBottomTabLayout pageBottomTabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private NavigationController mNavigationController;//底部导航栏控制器
    boolean isMenu2, isMenu3, isMenu4;
    public static MainActivity mainActivity;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected String setBarTitle() {
        return null;
    }

    @Override
    public void initCreat() {
        mainActivity = this;
        initNavBottomBar();
    }

    /**
     * 初始化底部导航栏
     */
    private void initNavBottomBar() {
        mNavigationController = pageBottomTabLayout.material()
                .addItem(R.mipmap.bottom_btn_home, R.mipmap.bottom_btn_home_s, "首页", ContextCompat.getColor(ct,R.color.blue))
                .addItem(R.mipmap.bottom_btn_yhq, R.mipmap.bottom_btn_yhq_s, "优惠券", ContextCompat.getColor(ct,R.color.blue))
                .addItem(R.mipmap.bottom_btn_hd, R.mipmap.bottom_btn_hd_s, "活动", ContextCompat.getColor(ct,R.color.blue))
                .addItem(R.mipmap.bottom_btn_my, R.mipmap.bottom_btn_my_s, "我的", ContextCompat.getColor(ct,R.color.blue))
                .setDefaultColor(ContextCompat.getColor(ct,R.color.bottom_defaul))
                .build();

        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                seletMenuInit(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //自动适配ViewPager页面切换
        mNavigationController.setupWithViewPager(viewPager);

        //也可以设置Item选中事件的监听
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                seletMenuInit(index);
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }

    private void seletMenuInit(int position) {
        if (position == 3){
            StatusBarUtils.transparencyBar(MainActivity.this);
        }else {
            StatusBarUtils.blackcyBar(MainActivity.this);
        }
//        if (position == 1) {
//            if (isMenu2) return;
//            isMenu2 = true;
////            HomeFragment.mfragment.init();
//        } else if (position == 2) {
//            if (isMenu3) return;
//            isMenu3 = true;
////            QAFragment.mfragment.init();
//        } else if (position == 3) {
//            if (isMenu4) return;
//            isMenu4 = true;
////            MyFragment.mfragment.init();
//        }
    }

    boolean isExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), getString(R.string.exit), Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            AppManager.getAppManager().AppExit(ct);
        }
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }

    };

}
