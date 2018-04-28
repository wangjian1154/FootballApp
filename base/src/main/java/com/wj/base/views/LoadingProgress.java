package com.wj.base.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wj.base.R;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.ToastUtils;

/**
 * Created by wj on 2018/2/7.
 * 加载的对话框
 */

public class LoadingProgress extends Dialog {

    private Context mContext;
    private static LoadingProgress progressDialog;

    public static LoadingProgress getInstance(Context context) {
        if (progressDialog != null) {
            if (progressDialog.getContext() == context) {
                return progressDialog;
            }
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new LoadingProgress(context);
        return progressDialog;
    }

    private LoadingProgress(@NonNull Context context) {
        super(context, R.style.LoadingProgress);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading_progress);

        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        getWindow().setBackgroundDrawable(new ColorDrawable(mContext.getResources().getColor(R.color.page_bg)));
        //一定要在setContentView之后调用，否则无效
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView iv_load = findViewById(R.id.iv_load);
        ImageLoadUtils.display(mContext, R.drawable.ic_loading, iv_load);
    }

    public void show() {
        try {
            if (progressDialog != null && !isShowing()) {
                super.show();
            }
        } catch (Exception e) {
            ToastUtils.showDebugShort(e.getMessage());
        }
    }

    public void dismiss() {
        try {
            if (progressDialog != null && isShowing()) {
                super.dismiss();
            }
        } catch (Exception e) {
            ToastUtils.showDebugShort(e.getMessage());
        }
    }

}
