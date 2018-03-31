package com.wj.baseutils.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wj.base.base.BaseFragment;
import com.wj.base.utils.FastBlur;
import com.wj.base.utils.ImageLoadUtils;
import com.wj.base.utils.ImageUtils;
import com.wj.base.utils.StatusBarUtil;
import com.wj.baseutils.R;
import com.wj.baseutils.contract.MineContract;
import com.wj.baseutils.model.MineModelImpl;
import com.wj.baseutils.presenter.MinePresenterImpl;
import com.wj.baseutils.ui.activity.MainActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wj on 2018/1/11.
 * 我的
 */

public class MineFragment extends BaseFragment<MinePresenterImpl, MineModelImpl>
        implements MineContract.MineView {

    @BindView(R.id.rl_head_bg)
    RelativeLayout rlHeadBg;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    @BindView(R.id.iv_user)
    ImageView iv_user;
    private int REQUEST_CODE_CHOOSE = 10;
    private List<Uri> mSelected;

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
    public void scoreOnClick() {
        mPresenter.setOnScoreClick(getContext());
    }

    @OnClick(R.id.iv_user)
    public void choiceAvaterClick() {
        ((MainActivity) getActivity()).checkPermission(() -> {
                    Matisse.from(MineFragment.this)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(1)
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f)
                            .imageEngine(new PicassoEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
                }
                , R.string.permission_default, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            if (mSelected != null && mSelected.size() > 0) {
                ImageLoadUtils.display(getContext(), mSelected.get(0), iv_user);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
