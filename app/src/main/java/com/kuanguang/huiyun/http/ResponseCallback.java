package com.kuanguang.huiyun.http;

/**
 * 描述：
 * Created by enjoy on 2017/4/12.
 */

public interface ResponseCallback {
    void onResponseSucess(String msg);
    void onResponseFailure(String msg);
}
