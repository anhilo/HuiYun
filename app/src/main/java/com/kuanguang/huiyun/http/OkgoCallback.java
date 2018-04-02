package com.kuanguang.huiyun.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * 修订历史：
 * -
 * -
 * -
 * -
 * -我的注释都已经写的不能再多了,不要再来问我怎么获取数据对象,怎么解析集合数据了,你只要会 gson ,就会解析
 * -
 * -如果你对这里的代码原理不清楚，可以看这里的详细原理说明：https://github.com/jeasonlzy/okhttp-OkGo/blob/master/README_JSONCALLBACK.md
 * -如果你对这里的代码原理不清楚，可以看这里的详细原理说明：https://github.com/jeasonlzy/okhttp-OkGo/blob/master/README_JSONCALLBACK.md
 * -如果你对这里的代码原理不清楚，可以看这里的详细原理说明：https://github.com/jeasonlzy/okhttp-OkGo/blob/master/README_JSONCALLBACK.md
 * -
 * -
 * -
 * ================================================
 */
public class OkgoCallback extends JsonCallback {

    private Context mContext;
    private ResponseCallback callback;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case -1://失败
                    try {
                        callback.onResponseFailure(msg.obj.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 0://成功
                    try {
                        callback.onResponseSucess(msg.obj.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    public OkgoCallback(Context mContext, ResponseCallback callback) {
        this.mContext = mContext;
        this.callback = callback;
    }

    @Override
    public void onSuccess(String s, Call call, Response response) {
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = s;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
//        super.onError(call, response, e);
        Message msg = Message.obtain();
        msg.what = -1;
        msg.obj = e.getMessage();
        mHandler.sendMessage(msg);
    }
}