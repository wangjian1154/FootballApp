package com.wj.baseutils.presenter;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.wj.base.base.BasePresenter;
import com.wj.baseutils.ui.activity.MainActivity;
import com.wj.baseutils.ui.contract.SplashContract;

/**
 * Created by wj on 2018/1/12.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private Context mContext;

    public SplashPresenter(Context context) {
        this.mContext = context;
    }

    @Override
    public void startAnim(RelativeLayout view) {
        view.animate().scaleX(1.12f)
                .scaleY(1.12f)
                .setDuration(3000)
                .setStartDelay(100)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        toMain(mContext);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        toMain(mContext);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }


    @Override
    public void toMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((AppCompatActivity) context).finish();
    }
}
