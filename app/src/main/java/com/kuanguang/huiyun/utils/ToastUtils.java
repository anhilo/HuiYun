package com.kuanguang.huiyun.utils;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by WYY on 2016/2/23 0023.
 */

public class ToastUtils {
    private static Toast toast;

    /*
    * 短时间的toast
    * */
    public static void showShortToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    msg,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();

    }

}
