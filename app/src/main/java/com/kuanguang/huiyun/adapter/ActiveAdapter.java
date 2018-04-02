package com.kuanguang.huiyun.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.model.TestModel;

import java.util.List;

/**
 * Created by
 */

public class ActiveAdapter extends BaseQuickAdapter<TestModel> {

    public ActiveAdapter(List<TestModel> data) {
        super(R.layout.item_active, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestModel item) {

    }
}
