package com.kuanguang.huiyun.fragment;

import android.content.Intent;
import android.view.View;

import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.activity.LoginRegisterActivity;
import com.kuanguang.huiyun.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by
 */

public class MyFragment extends BaseFragment{

    @Override
    protected int getContentView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initCreat() {

    }

    @OnClick({R.id.lin_login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.lin_login:
                startActivity(new Intent(ct, LoginRegisterActivity.class));
                break;
        }
    }

}
