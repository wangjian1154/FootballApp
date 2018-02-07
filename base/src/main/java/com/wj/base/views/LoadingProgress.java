package com.wj.base.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.wj.base.R;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.ToastUtils;

/**
 * Created by wj on 2018/2/7.
 * 加载的对话框
 */

public class LoadingProgress extends android.app.AlertDialog {

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
