package com.kuanguang.huiyun.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.activity.MainActivity;
import com.kuanguang.huiyun.common.AppManager;
import com.kuanguang.huiyun.permission.PermissionReq;
import com.kuanguang.huiyun.utils.StatusBarUtils;
import com.kuanguang.huiyun.utils.ToastUtils;
import com.kuanguang.huiyun.view.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * BaseActivity 基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context ct;//全局Context
    private View layout_content;//布局装载器
    protected LinearLayout ll_base;//全部布局
    protected View in_action_bar;//ActionBar布局不需要用到的话直接GONE掉
    protected TextView tv_bar_title;//标题文字
    protected TextView tv_bar_right;//标题文字右边
    protected ImageView img_rule;//右边按钮
    protected ImageView img_back;//返回按钮
    protected RelativeLayout rel_titleBg;//ActionBar背景色
//    private LoadingDialog dialog;//loading等待框
    private com.android.tu.loadingdialog.LoadingDialog dialog;
    private InputMethodManager mInputMethodManager;
    private BaseActivity baseActivity;
    private Unbinder binder;//注解

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        baseActivity = this;
        ct = this;
        mySetContentView(getContentViewId());
        setBarTitle(setBarTitle());
        StatusBarUtils.blackcyBar(baseActivity);
        initDialog();
        initCreat();
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(baseActivity);
    }

    private void initDialog() {
        com.android.tu.loadingdialog.LoadingDialog.Builder builder1=new com.android.tu.loadingdialog.LoadingDialog.Builder(ct)
                .setMessage("加载中...")
//                .setShowMessage(false)
                .setCancelable(true);
        dialog = builder1.create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binder != null) binder.unbind();//解绑
        AppManager.getAppManager().finishActivity(baseActivity.getClass());// 结束Activity&从堆栈中移除
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }

    /**
     * 设置内容页面
     *
     * @param resource 内容部分对应的布局文件
     */
    public void mySetContentView(int resource) {
        rel_titleBg = (RelativeLayout) findViewById(R.id.rel_titleBg);
        img_back = (ImageView) findViewById(R.id.img_back);
        ll_base = (LinearLayout) findViewById(R.id.ll_base);
        in_action_bar = findViewById(R.id.in_action_bar);
        layout_content = View.inflate(ct, resource, null);
        layout_content.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll_base.addView(layout_content);
        binder = ButterKnife.bind(this, ll_base);

    }

    /**
     * 设置页面标题，子类需调用该方法
     *
     * @param titleName 返回值是标题
     */
    public void setBarTitle(String titleName) {
//        StatusBarUtils.setWindowStatusBarColor(BaseActivity.this, ContextCompat.getColor(ct,R.color.theme_bar));
        tv_bar_title = (TextView) in_action_bar.findViewById(R.id.tv_bar_title);
        if (titleName == null) {
            in_action_bar.setVisibility(View.GONE);
        } else {
            tv_bar_title.setText(titleName);
        }
    }

    public void toast(final String msg){
        ToastUtils.showShortToast(ct,msg);
    }

    public void setBarTitleRight(String titleName) {
        tv_bar_right = (TextView) in_action_bar.findViewById(R.id.tv_bar_right);
        tv_bar_right.setVisibility(View.VISIBLE);
        tv_bar_right.setText(titleName);
    }

    public void setBarImgRight(){
        img_rule = (ImageView) in_action_bar.findViewById(R.id.img_rule);
        img_rule.setVisibility(View.VISIBLE);
    }

    /**
     * 设置页面背景，子类需调用该方法
     *
     * @param resours 颜色资源
     */
    public void setBg(int resours) {
        rel_titleBg.setBackgroundColor(getResources().getColor(resours));
    }

    public void setSwipeRefresh(SwipeRefreshLayout swipeRefresh){
        swipeRefresh.setColorSchemeColors(ContextCompat.getColor(ct, R.color.theme_bar));
    }

    /**
     * 显示loading
     */
    public void showDialog() {
        dialog.show();
//        dialog = new LoadingDialog(this, R.style.custom_dialog);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.show();
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
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        try {
            if (mInputMethodManager == null) {
                mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            }
            mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    /**
     * 返回事件
     */
    public void backClick(View v) {
        this.finish();
    }

    /**
     * 获取布局id
     */
    protected abstract int getContentViewId();

    /**
     * 设置导航栏标题
     */
    protected abstract String setBarTitle();

    /**
     * 初始化数据
     */
    public abstract void initCreat();
}
