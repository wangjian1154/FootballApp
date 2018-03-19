package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.widget.Button;

import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.ToastUtils;
import com.wj.baseutils.R;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/11.
 * 我的
 */

public class MineFragment extends SimpleFragment {

    @BindView(R.id.btn_test)
    Button btnTest;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        btnTest.setOnClickListener(v -> {
            ToastUtils.showShort("没有Bug？");
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

}
