package com.wj.baseutils;

import android.os.Bundle;
import android.view.View;

import com.wj.base.base.BaseActivity;
import com.wj.base.views.TitleBar;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        TitleBar titleBar=findViewById(R.id.title_bar);
        titleBar.setBackOnClickListenner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
