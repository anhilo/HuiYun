package com.kuanguang.huiyun.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.kuanguang.huiyun.R;
import com.kuanguang.huiyun.view.LVCircularRing;


/**
 * 加载中Dialog
 * 
 * @author lexyhp
 */
public class LoadingDialog extends Dialog {

//	private TextView tips_loading_msg;
//	private int layoutResId;
//	private String message = null;

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
	}

//	/**
//	 * 构造方法
//	 *
//	 * @param context
//	 *            上下文
//	 * @param layoutResId
//	 *            要传入的dialog布局文件的id
//	 */
//	public LoadingDialog(Context context, int layoutResId) {
//		super(context);
////		LayoutInflater inflater = (LayoutInflater) context
////				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////		LoadingDialog dialog = new LoadingDialog(context,R.style.custom_dialog);
////		View layout = inflater.inflate(layoutResId, null);
////		dialog.addContentView(layout, new LinearLayout.LayoutParams(
////				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//		this.layoutResId = layoutResId;
//		message = context.getResources().getString(R.string.loading);
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.view_tips_loading2);
//		LVCircularRing ring = (LVCircularRing) findViewById(R.id.ring);
//		ring.startAnim();
//		tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
//		tips_loading_msg.setText(this.message);
	}

}
