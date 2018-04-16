package com.wj.base.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wj.base.R;

/**
 * Created by wj on 2018/1/10.
 * 自定义标题
 */

public class TitleBar extends FrameLayout {

    private String TAG = "CoreTitleView";
    private TextView mTitle;
    private ImageButton mLeftBtn;
    private ImageButton mRightBtn;
    private TextView mRightText;
    private EditText mEditTitle;
    private LinearLayout mRightFilterLay;
    private LinearLayout mRightContainer;//加载多个子view的容器,默认gone
    private ImageView mRotationImv;
    private int mRotation = 0;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_title_view, this, true);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mLeftBtn = (ImageButton) findViewById(R.id.ibt_back);
        mRotationImv = (ImageView) findViewById(R.id.lay_right_imv_flag);
        mRightBtn = (ImageButton) findViewById(R.id.ibt_title_right);
        mRightText = (TextView) findViewById(R.id.tv_title_right);
        mEditTitle = (EditText) findViewById(R.id.edit_title);
        mRightFilterLay = (LinearLayout) findViewById(R.id.lay_filter_right);
        mRightContainer = (LinearLayout) findViewById(R.id.ll_right_container);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void setTitle(String text) {
        mTitle.setText(text);
    }

    public void setTitle(int string) {
        mTitle.setText(string);
    }

    public void setTitileBg(int bg) {
        mTitle.setBackgroundColor(bg);
    }

    public void setTitleDrawableRight(int res) {
        Drawable drawable = getResources().getDrawable(res);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTitle.setCompoundDrawables(null, null, drawable, null);
    }

    public void setTitleTextColor(int color) {
        mTitle.setTextColor(color);
    }

    public String getTitle() {
        return mTitle.getText().toString();
    }

    public void setTitleClickListener(OnClickListener titleClickListener){
        mTitle.setOnClickListener(titleClickListener);
    }

    public void setBackButton(OnClickListener listener) {
        mLeftBtn.setVisibility(View.VISIBLE);
        if (listener != null)
            mLeftBtn.setOnClickListener(listener);
    }

    public TitleBar setRightImgButton(int bgId, OnClickListener listener) {
        if (bgId != 0) {
            mRightBtn.setVisibility(View.VISIBLE);
            mRightBtn.setBackgroundResource(bgId);
            if (listener != null)
                mRightBtn.setOnClickListener(listener);
        } else {
            mRightBtn.setVisibility(View.GONE);
        }
        return this;
    }

    public void setRightView(View view) {
        if (view == null)
            return;
        mRightContainer.addView(view);
        mRightContainer.setVisibility(View.VISIBLE);
    }

    public ImageButton[] setRightImgButtonArray(int[] bgIds, OnClickListener[] listeners) {
        if (bgIds == null || listeners == null)
            return null;
        final int count = bgIds.length;
        if (count != listeners.length)
            throw new IllegalArgumentException("非法参数:按钮与按钮监听器必需一一对应");
        if (count > 0) {
            mRightContainer.removeAllViews();
            ImageButton imageButton;
            LinearLayout linearLayout;
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ImageButton[] btns = new ImageButton[count];
            for (int i = 0; i < count; i++) {
                linearLayout = (LinearLayout) inflater.inflate(R.layout.inflater_imagebtn, null);
                imageButton = (ImageButton) linearLayout.findViewById(R.id.img);
                imageButton.setBackgroundResource(bgIds[i]);
                btns[i] = imageButton;
                mRightContainer.addView(linearLayout);
                imageButton.setOnClickListener(listeners[i]);
            }
            mRightContainer.setVisibility(View.VISIBLE);
            return btns;
        }

        return null;
    }

    public ImageButton getmRightImgBtn() {
        return mRightBtn;
    }

    public void setRightButton(String text, int bgId, OnClickListener listener) {
        if (!TextUtils.isEmpty(text) || bgId != 0) {
            mRightText.setVisibility(View.VISIBLE);
            mRightText.setText(text);
            mRightText.setBackgroundResource(bgId);
            mRightText.setGravity(Gravity.CENTER_VERTICAL);
            if (listener != null)
                mRightText.setOnClickListener(listener);
        } else
            mRightText.setVisibility(View.GONE);
    }

    public void setRightText(String text, OnClickListener listener) {
        if (text != null)
            mRightText.setText(text);
        if (listener != null)
            mRightText.setOnClickListener(listener);
        mRightText.setVisibility(View.VISIBLE);
    }

    public void setRightButtonTextSize(int size, int color) {
        mRightBtn.setVisibility(GONE);
        mRightText.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        mRightText.setTextColor(color);
    }

    public void setRightBtnTextColor(int color){
        mRightText.setTextColor(color);
    }

    public ImageButton getRightButton() {
        return mRightBtn;
    }

    public ImageButton setRightBtnResource(int r) {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setBackgroundResource(r);
        return mRightBtn;
    }

    public void setRightBtnText(String text) {
        mRightText.setVisibility(View.VISIBLE);
        mRightBtn.setVisibility(GONE);
        mRightText.setText(text);
    }

    public EditText getEditTitle() {
        return mEditTitle;
    }

    public void setmEditTitle(EditText mEditTitle) {
        this.mEditTitle = mEditTitle;
    }

    public TextView getRightText() {
        return mRightText;
    }

    public ImageButton getLeftBtn() {
        return mLeftBtn;
    }


    public TextView getTitleView() {
        return mTitle;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * 设置倒三角 及 标题内容
     *
     * @param text
     * @param toggle
     */
    public void setRightFilterText(String text, boolean toggle) {
        mRightBtn.setVisibility(GONE);
        mRightText.setVisibility(GONE);
        mRightFilterLay.setVisibility(VISIBLE);
        if (null != text)
            ((TextView) mRightFilterLay.findViewById(R.id.lay_right_tv_title_right)).setText(text);
        if (!toggle) {

        } else {
            if (mRotation == 60) {
                mRotation = -60;
            }
            ObjectAnimator rotation = ObjectAnimator.ofFloat(mRotationImv, "rotation", mRotation, mRotation + 60);
            mRotation += 60;
            rotation.setDuration(300);
            rotation.setInterpolator(new DecelerateInterpolator());
            rotation.start();
        }
    }


}
