package com.wj.baseutils.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.wj.base.base.BaseActivity;
import com.wj.baseutils.R;
import com.wj.baseutils.presenter.SplashPresenter;
import com.wj.baseutils.ui.contract.SplashContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<SplashContract.View, SplashPresenter> {


    @BindView(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        mPresenter.startAnim(rlWelcome );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(this);
    }

}
