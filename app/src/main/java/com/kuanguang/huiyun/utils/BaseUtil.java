package com.kuanguang.huiyun.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.Window;
import android.view.WindowManager;

import com.kuanguang.huiyun.common.Constants;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 描述：
 * Created by enjoy on 2017/5/3.
 */

public class BaseUtil {

    /**
     * 获取设备唯一标识
     * Pseudo-Unique ID,在任何Android手机中都有效
     * 参考：http://blog.csdn.net/ljz2009y/article/details/22895297
     *
     * @return 备唯一标识
     */
    public static String getUniqueID() {
        String uniqueID = "35" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;
        return uniqueID;
    }

    /**
     * 根据BASE_URL拼接完整url路径
     *
     * @param url 局部路径
     * @return 完整的路径
     */
    public static String getUrL(String url) {
        return Constants.URlS.BASE_URL + url;
    }

    /**
     * 拼接get参数
     * @param url
     * @param params
     * @return
     */
    public static String getUrl(String url, HashMap params) {
        String completeUrl = getUrL(url);
        // 添加url参数
        if (params != null) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                Object value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            completeUrl += sb.toString();
        }
        return completeUrl;
    }

    /**
     * 检测设备是否启用NFC功能
     *
     * @param context
     * @return
     */
    public static boolean hasNfc(Context context) {
//        boolean bRet = false;
//        if (context == null)
//            return bRet;
//        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
//        NfcAdapter adapter = manager.getDefaultAdapter();
//        if (adapter != null && adapter.isEnabled()) {
//            // adapter存在，能启用
//            bRet = true;
//        }
//        return bRet;
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
        boolean bRet = mNfcAdapter.isEnabled();
        return bRet;
    }

    /**
     * 判断设备是否支持NFC
     *
     * @param context
     * @return
     */
    public static boolean isNFC(Context context) {
        boolean bRet = false;
//        if (context == null)
//            return bRet;
//        PackageManager pm = context.getPackageManager();
//        bRet = pm.hasSystemFeature(PackageManager.FEATURE_NFC);
//        return bRet;
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(context);
        if(mNfcAdapter != null){
            bRet = true;
        }
        return bRet;
    }

    /**
     * Scrollview内嵌gridview，设置gridview的高度。
     *
     * @param listView
     */
//    public static void setListViewHeightBasedOnChildren(GridView listView) {
//        // 获取listview的adapter
//        RechargeAdapter listAdapter = (RechargeAdapter) listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//        // 固定列宽，有多少列
//        int col = 3;// listView.getNumColumns();
//        int totalHeight = 0;
//        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
//        // listAdapter.getCount()小于等于8时计算两次高度相加
//        //第一个item的高度
//        View listItem = listAdapter.getView(0, null, listView);
//        listItem.measure(0, 0);
//        // 获取item的高度和
//        int itemHeight = listItem.getMeasuredHeight();
//        int count = listAdapter.getCount();
//        int sum = 0;
//        if (count % col == 0) {
//            sum = count / col;
//        } else {
//            sum = count / col + 1;
//        }
//        totalHeight = itemHeight * sum + 15 * sum;
//        // 获取listview的布局参数
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        // 设置高度
//        params.height = totalHeight;
//        // 设置margin
////        ((ViewGroup.MarginLayoutParams) params).setMargins(0, 15, 0, 15);
//        // 设置参数
//        listView.setLayoutParams(params);
//    }

    /**
     * 正则匹配
     *
     * @param strEmail   被判断的数据
     * @param strPattern 正则表达式字符串
     * @return
     */
    public static boolean isMatcher(String strEmail, String strPattern) {
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 生成随机数字和字母
     *
     * @param length 随机数的长度
     * @return
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 获取format格式的字符串
     *
     * @param obj
     * @param format 数据格式
     * @return
     */
    public static String getDataFormat(double obj, String format) {
        DecimalFormat df = new DecimalFormat(format);
        String result = df.format(obj);
        return result;
    }

    /**
     * 保留两位小数
     *
     * @param str
     * @return
     */
    public static String getBalanceFormat(String str) {
        if (TextUtils.isEmpty(str) || "null".equals(str)) {
            return "0.00";
        }
        double douStr = Double.parseDouble(str);
        return getDataFormat(douStr, "######0.00");
    }

    /**
     * 单位分转化为元 并保留两位小数
     *
     * @param str
     * @return
     */
    public static String getBalanceYuan(String str) {
        if (TextUtils.isEmpty(str) || "null".equals(str)) {
            return "0.00";
        }
        double douStr = Double.parseDouble(str)/100;
        return getDataFormat(douStr, "######0.00");
    }

    /**
     * 一个textview设置不同的字体大小
     *
     * @param content
     * @param start
     * @param center
     * @param end
     * @return
     */
    public static Spannable getDifferentSize(String content, int start, int center, int end) {
        Spannable WordtoSpan = new SpannableString(content);
        WordtoSpan.setSpan(new AbsoluteSizeSpan(40), start, center, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        WordtoSpan.setSpan(new AbsoluteSizeSpan(80), center, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return WordtoSpan;
    }

    /**
     * 金额单位元转化为单位分
     *
     * @param minute
     * @return
     */
    public static String getMinuteMoney(String minute) {
        if (TextUtils.isEmpty(minute) || "null".equals(minute) || !BaseUtil.isMatcher(minute,"[0-9]*") || !BaseUtil.isMatcher(minute,"[0-9]*")) {
            return "0";
        }
        double douStr = Double.parseDouble(minute) * 100;
        return (int)douStr + "";
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersion(Activity activity) {
        try {
            PackageManager manager = activity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 是否为10的倍数
     * @param str
     * @return
     */
    public static boolean isMultipe(String str){
        if(TextUtils.isEmpty(str)){
            return false;
        }
        int data = Integer.parseInt(str.replace(".00",""));
        if(data > 0 && data <= 200){
            if(data % 10 == 0){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断是否有网络连接
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获得系统亮度
     *
     * @return
     */
    public static int getSystemBrightness(Context context) {
        int systemBrightness = 0;
        try {
            systemBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;
    }

    /**
     * 改变App当前Window亮度
     *
     * @param brightness
     */
    public static void changeAppBrightness(Activity activity,int brightness) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness == -1) {
            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        } else {
            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
        }
        window.setAttributes(lp);
    }

    /**
     * 判断是否有外部存储设备sdcard
     */
    public static boolean isSdcardExit() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
}
