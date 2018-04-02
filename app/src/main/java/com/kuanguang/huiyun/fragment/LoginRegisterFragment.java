package com.kuanguang.huiyun.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by
 */

public class LoginRegisterFragment extends BaseFragment {
    @BindView(R.id.tv_complet)
    TextView tv_complet;
    @BindView(R.id.rel_setpwd)
    RelativeLayout rel_setpwd;
    @BindView(R.id.tv_setpwd)
    TextView tv_setpwd;
    @BindView(R.id.tv_mima)
    TextView tv_mima;

    private String mTitle;

    public static LoginRegisterFragment getInstance(String title) {
        LoginRegisterFragment sf = new LoginRegisterFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_login_register;
    }

    @Override
    protected void initCreat() {
        if (mTitle.equals("登录")){
            tv_mima.setVisibility(View.VISIBLE);
            tv_setpwd.setVisibility(View.GONE);
            rel_setpwd.setVisibility(View.GONE);
            tv_complet.setText("登录");
        }else {
            tv_mima.setVisibility(View.INVISIBLE);
            tv_setpwd.setVisibility(View.VISIBLE);
            rel_setpwd.setVisibility(View.VISIBLE);
            tv_complet.setText("完成注册");
        }
    }
}
