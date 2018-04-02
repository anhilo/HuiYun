package com.kuanguang.huiyun.fragment;

import android.view.View;
import android.widget.TextView;

import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.base.BaseFragment;

import butterknife.BindView;


public class SimpleCardFragment extends BaseFragment {
    @BindView(R.id.tv_statue)
    TextView tv_statue;

    private String mTitle;

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
        tv_statue.setText(mTitle);
    }
}