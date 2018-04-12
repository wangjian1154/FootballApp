package com.wj.baseutils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wj.baseutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wj on 2018/4/12.
 */

public class PersonItemView extends LinearLayout {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String title;

    public PersonItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initAttrs(context, attrs);

        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_person_item_view, this, true);
        ButterKnife.bind(this, view);

        if (title != null) {
            tvTitle.setText(title);
        }
    }

    @SuppressLint("RestrictedApi")
    private void initAttrs(Context context, AttributeSet attrs) {
        TintTypedArray tta = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.PersonItemView);
        title = tta.getString(R.styleable.PersonItemView_title);
        tta.recycle();
    }
}
