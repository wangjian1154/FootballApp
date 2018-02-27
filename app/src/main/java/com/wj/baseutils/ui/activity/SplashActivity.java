package com.wj.baseutils.ui.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.wj.base.base.SimpleActivity;
import com.wj.baseutils.R;

import butterknife.BindView;

public class SplashActivity extends SimpleActivity {


    @BindView(R.id.rl_welcome)
    RelativeLayout rlWelcome;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        rlWelcome.animate().scaleX(1.12f)
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }


}
