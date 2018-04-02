package com.kuanguang.huiyun.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.utils.SPUtils;
import com.kuanguang.huiyun.view.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * BaseFragment
 */

public abstract class BaseFragment extends Fragment {
    public Context ct;//全局Context
    private LoadingDialog dialog;//loading 等待框
    private boolean isViewCreated;//Fragment的View加载完毕的标记
    private boolean isUIVisible;//Fragment对用户可见的标记
    public Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(getContentView(), container, false);
        ct = getActivity();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCreat();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /**
     * fragment懒加载，当可见时调用该方法
     */
    public void loadData(){}

    @Override
    public void onDestroy() {
//        if(unbinder != null) unbinder.unbind();//解绑
        unbinder = null;
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isUIVisible = false;
        isViewCreated = false;
    }

    public void setSwipeRefresh(SwipeRefreshLayout swipeRefresh){
        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(ct, R.color.theme_bar));
    }

    /**
     * 显示loading
     */
    public void showDialog() {
        dialog = new LoadingDialog(ct, R.style.custom_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 隐藏loading
     */
    public void dismissDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null){
                    dialog.dismiss();
                }
            }
        }, 500);
    }

    /**
     * fragment关闭软键盘
     */
    public void closeKeyboard(){
        //隐藏软件盘
        try {
            View mv = getActivity().getWindow().peekDecorView();
            if (mv != null){
                InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(mv.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * fragment显示软键盘
     */
    public void showKeyboard(EditText mv){
        try {
            //View mv = getActivity().getWindow().peekDecorView();
            if (mv != null){
                InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                //第一个参数必须为EditText或者子类，第二个参数有效就要确保,EditText是可以获取焦点
                inputmanger.showSoftInput(mv,InputMethodManager.SHOW_FORCED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getContentView();

    /**
     * 初始化view
     *
     */
    protected abstract void initCreat();

}
