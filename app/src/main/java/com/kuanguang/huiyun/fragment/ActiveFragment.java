package com.kuanguang.huiyun.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kuanguang.huiyun.adapter.ActiveAdapter;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.base.BaseFragment;
import com.kuanguang.huiyun.model.TestModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by
 */

public class ActiveFragment extends BaseFragment{
    @BindView(R.id.rv_active)
    RecyclerView rv_active;

    private ActiveAdapter activeAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initCreat() {
        List<TestModel> list = new ArrayList<>();
        for (int i = 0;i < 5;i++){
            list.add(new TestModel());
        }
        activeAdapter = new ActiveAdapter(list);
        rv_active.setLayoutManager(new LinearLayoutManager(ct));
        rv_active.setAdapter(activeAdapter);

    }
}
