package com.kuanguang.huiyun.http;

import android.content.Context;

import com.kuanguang.huiyun.utils.BaseUtil;
import com.kuanguang.huiyun.utils.LogUtil;
import com.lzy.okgo.OkGo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;


/**
 * 描述：
 * Created by enjoy on 2017/4/12.
 */

public class HttpLoader {
    private static volatile HttpLoader singleton;
    private static Context mConte4xt;
//    private static String token = "";

    public static HttpLoader getInstance(Context context) {
        if (singleton == null) {
            synchronized (HttpLoader.class) {
                if (singleton == null) {
                    mConte4xt = context.getApplicationContext();
                    singleton = new HttpLoader();
                }
            }
        }
//        if (Constants.Variable.mLoginBean != null) {
//            token = Constants.Variable.mLoginBean.getToken();
//        }
        return singleton;
    }

    public void post(String url, String param, ResponseCallback callback) {
        if (!BaseUtil.isNetworkConnected(mConte4xt)) {
            callback.onResponseFailure("网络异常，请稍候重试");
            return;
        }
        LogUtil.E("上送报文==" + param);
        OkGo.post(url)//
                .tag(this)//
//                .headers(Constants.Variable.TOKEN_KEY, token)//
                .params("param", param)//
                .isMultipart(false)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new OkgoCallback(mConte4xt, callback));

    }


    /**
     * 单文件上传
     *
     * @param url
     * @param file
     * @param callback
     */
    public void postFile(String url, File file, ResponseCallback callback) {
        LogUtil.E("上送报文==" + file.toString());
        OkGo.post(url)//
                .tag(this)//
                .isMultipart(true)
//                .headers(Constants.Variable.TOKEN_KEY, token)//
                .params("file1", file)//
                .execute(new OkgoCallback(mConte4xt, callback));

    }

    /**
     * @param url      请求路径
     * @param callback 请求结果回调处理对象
     */
    public void get(String url, ResponseCallback callback) {
        if (!BaseUtil.isNetworkConnected(mConte4xt)) {
            callback.onResponseFailure("网络异常，请稍候重试");
            return;
        }
        LogUtil.E(url);
        OkGo.get(url)//
                .tag(this)//
//                .headers(Constants.Variable.TOKEN_KEY, token)//
                .execute(new OkgoCallback(mConte4xt, callback));
    }

    public void get(String url, String param, ResponseCallback callback) {
        get(jsonToString(url, param), callback);
    }

    public String jsonToString(String url, String param) {
        StringBuilder sb = new StringBuilder(url);
        try {
            JSONObject jsonObject = new JSONObject(param);
            Iterator keys = jsonObject.keys();
            sb.append("?");
            while (keys.hasNext()) {
                String key = String.valueOf(keys.next());
                Object value = jsonObject.get(key);
                sb.append(key + "=");
                sb.append(value + "&");
            }
            return sb.toString().substring(0, sb.length() - 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
