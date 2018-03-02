package com.wj.baseutils.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wj.base.base.SimpleActivity;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.SplashBean;
import com.wj.baseutils.net.ApiRetrofit;
import com.wj.baseutils.net.ApiService;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends SimpleActivity {


    @BindView(R.id.ll_welcome)
    LinearLayout llWelcome;
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.iv_slogan)
    ImageView ivSlogan;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Observable<SplashBean> splashImg = apiService.getSplashImg();
        Observer<SplashBean> observer = new Observer<SplashBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SplashBean splashBean) {
                if (splashBean != null && splashBean.data != null
                        && !StringUtils.isEmpty(splashBean.data.url)) {
                    ivSplash.setVisibility(View.VISIBLE);
                    ImageLoadUtils.display(SplashActivity.this, splashBean.data.url, ivSplash);
                    startToMainAnimal();
                }else{
                    startToMainAnimal();
                }
            }

            @Override
            public void onError(Throwable e) {
                startToMainAnimal();
            }

            @Override
            public void onComplete() {

            }
        };
        splashImg.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
        ;

    }

    private void startToMainAnimal() {
        llWelcome.animate().scaleX(1.12f)
                .scaleY(1.12f)
                .setDuration(3000)
                .setStartDelay(100)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        toMain();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        toMain();
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }

    private void toMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }


}
