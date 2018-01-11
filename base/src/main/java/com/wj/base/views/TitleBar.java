package com.wj.base.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wj.base.R;

/**
 * Created by wj on 2018/1/10.
 * 自定义标题
 */

public class TitleBar extends FrameLayout {

    private TextView tv_title;
    private TextView imb_left;
    private TextView imb_right;

    private boolean hideNavigatorIcon;

    private OnClickListener navigatorOnClickListener;
    private OnClickListener actionOnClickListener;

    private int actionTextColor = 0xFF000000;
    private Drawable backDrawable;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_title_view, this);
        
        tv_title = view.findViewById(R.id.tv_title);
        imb_left = view.findViewById(R.id.tv_back);
        imb_right = view.findViewById(R.id.tv_right);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);

        hideNavigatorIcon = a.getBoolean(R.styleable.TitleBar_tb_hide_navigator_icon, false);

        tv_title.setText(a.getString(R.styleable.TitleBar_tb_title));
        imb_left.setText(a.getString(R.styleable.TitleBar_tb_navigator_label));
        Drawable drawable = a.getDrawable(R.styleable.TitleBar_tb_navigator_icon);

        if (hideNavigatorIcon) {
            imb_left.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else if (drawable != null) {
            imb_left.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }

        actionTextColor = a.getColor(R.styleable.TitleBar_tb_action_text_color, actionTextColor);
        imb_right.setTextColor(actionTextColor);
        imb_right.setText(a.getString(R.styleable.TitleBar_tb_action_label));
        drawable = a.getDrawable(R.styleable.TitleBar_tb_action_icon);
        if (drawable != null) {
            imb_right.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }

        backDrawable = context.getResources().getDrawable(R.drawable.ic_back);

        imb_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (navigatorOnClickListener != null) {
                    navigatorOnClickListener.onClick(v);
                } else {
                    Context context = getContext();
                    if (context instanceof Activity) {
                        ((Activity) context).onBackPressed();
                    }
                }
            }
        });
        imb_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionOnClickListener != null) {
                    actionOnClickListener.onClick(v);
                }
            }
        });

    }

    //设置标题
    public void setTitle(String label) {
        tv_title.setText(label);
    }

    public void setActionLabel(String label) {
        imb_right.setText(label);
    }

    public View getNavigator() {
        return imb_left;
    }

    public View getActionLabel() {
        return imb_right;
    }

    public void setBackOnClickListenner(OnClickListener navigatorOnClickListener) {
        if (backDrawable!=null){
            imb_left.setCompoundDrawablesWithIntrinsicBounds(backDrawable, null, null, null);
        }
        this.navigatorOnClickListener = navigatorOnClickListener;
    }

    public void setRightOnClickListener(OnClickListener actionOnClickListener) {
        this.actionOnClickListener = actionOnClickListener;
    }
}
