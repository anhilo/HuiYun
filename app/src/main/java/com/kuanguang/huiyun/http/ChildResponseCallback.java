package com.kuanguang.huiyun.http;

import android.content.Context;

import com.google.gson.stream.JsonReader;
import com.kuanguang.huiyun.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * 描述：
 * Created by enjoy on 2017/4/20.
 */

public abstract  class ChildResponseCallback<T> implements ResponseCallback {

    private Context mContext;

    public ChildResponseCallback(Context context) {
        mContext = context;
    }

    public abstract void onSucess(T result);
    public void onError(T lzyResponse1){};
    public abstract void onFilure(String errorMsg);

    @Override
    public void onResponseSucess(String msg) {
        LogUtil.E("返回报文=="+msg);
        //com.lzy.demo.callback.DialogCallback<com.lzy.demo.model.LzyResponse<com.lzy.demo.model.ServerModel>> 得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //com.lzy.demo.model.LzyResponse<com.lzy.demo.model.ServerModel>
        Type type = params[0];
        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        JsonReader jsonReader = new JsonReader(new StringReader(msg));
        LzyResponse lzyResponse = Convert.fromJson(jsonReader, type);
        try {
            JSONObject jsonObject = new JSONObject(msg);
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("msg");
            switch (status){
                case 200: //交易成功
                    onSucess((T) lzyResponse);
                    onSucessed(msg);
                    break;
                default:
                    onError((T) lzyResponse);
//                    WaitingUtil.getInstance().dismissWaiting();
                    break;
            }
        } catch (JSONException e) {
//            onFilure(mContext.getString(R.string.service_error));
        }
    }

    protected  void onSucessed(String msg){};

    @Override
    public void onResponseFailure(String msg) {
//        onFilure("onResponseFailure:"+msg);
        onFilure("连接异常，请查看您的网络连接");
//        WaitingUtil.getInstance().dismissWaiting();
    }
}
