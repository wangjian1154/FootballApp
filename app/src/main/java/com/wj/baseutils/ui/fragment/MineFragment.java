package com.wj.baseutils.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wj.base.base.BaseFragment;
import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.FastBlur;
import com.wj.base.utils.StatusBarUtil;
import com.wj.baseutils.R;
import com.wj.baseutils.contract.MineContract;
import com.wj.baseutils.model.MineModelImpl;
import com.wj.baseutils.presenter.MinePresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by wj on 2018/1/11.
 * 我的
 */

public class MineFragment extends BaseFragment<MinePresenterImpl, MineModelImpl> implements MineContract.MineView {

    @BindView(R.id.rl_head_bg)
    RelativeLayout rlHeadBg;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    @BindView(R.id.iv_user)
    ImageView iv_user;

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        mPresenter.setData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenterImpl createPresenter() {
        return new MinePresenterImpl();
    }

    @Override
    protected MineModelImpl createModel() {
        return new MineModelImpl();
    }

    @Override
    public void setBackgroundBlur() {
        Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) iv_user.getDrawable()).getBitmap());
        Bitmap fastBlueBitmap = FastBlur.getDefaultFastBlueBitmap(bitmap);
        iv_bg.setImageBitmap(fastBlueBitmap);
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.red));
    }

    @OnClick(R.id.rl_mine_balance)
    public void mineBalanceClick() {
        mPresenter.setMineBalanceClick(getContext());
    }

    @OnClick(R.id.rl_score)
    public void scoreOnClick(){
        mPresenter.setOnScoreClick(getContext());
    }
}
