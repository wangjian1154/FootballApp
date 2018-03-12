package com.wj.baseutils.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wj.base.base.SimpleActivity;
import com.wj.base.data.Constants;
import com.wj.base.utils.SpanUtils;
import com.wj.base.views.TitleBar;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.LinkmanBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkmanDetailActivity extends SimpleActivity {

    @BindView(R.id.lay_title)
    TitleBar layTitle;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;

    public static void show(Context context, LinkmanBean linkmanBean) {
        Intent intent = new Intent(context, LinkmanDetailActivity.class);
        intent.putExtra(Constants.Key.BUNDLE, linkmanBean);
        ((SimpleActivity) context).startActivityForResult(intent, 1);
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        Intent intent = getIntent();

        LinkmanBean linkmanBean = (LinkmanBean) intent.getSerializableExtra(Constants.Key.BUNDLE);

        layTitle.setTitle(linkmanBean.name);

        tvUsername.setText(linkmanBean.name);
        tvMobile.setText(new SpanUtils()
                .append(linkmanBean.mobile)
                .setForegroundColor(Color.BLUE)
                .setUnderline()
                .create());

        tvMobile.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + linkmanBean.mobile));
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        });

        layTitle.setBackOnClickListenner(v -> finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_linkman_detail;
    }

}
