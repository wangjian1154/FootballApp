package com.wj.baseutils.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.ScreenUtils;
import com.wj.baseutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wj on 2018/3/13.
 * 联系人详情底部菜单
 */

public class LinkmanBottomDialog extends Dialog {

    private List<MenuBean> data;
    private String[] titles = {"设置备注及标签", "标为星际朋友", "设置朋友圈权限", "发送该名片",
            "投诉", "加入黑名单", "删除", "添加到桌面"};

    public LinkmanBottomDialog(@NonNull Context context) {
        super(context, R.style.translucent_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_linkman_bottom);
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ScreenUtils.getWidthInPx(getContext()), ScreenUtils.getHeightInPx(getContext()) * 2 / 5);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        data = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        for (int i = 0; i < titles.length; i++) {
            MenuBean bean = new MenuBean();
            bean.leftImg = R.mipmap.ic_launcher;
            bean.typeName = titles[i];
            data.add(bean);
        }

        ListAdapter adapter = new ListAdapter(data);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            if ("设置备注及标签".equals(titles[position])){

            }
        });
    }

    class ListAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

        public ListAdapter(@Nullable List<MenuBean> data) {
            super(R.layout.item_list_linkman_detail_menu, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MenuBean item) {
            TextView tvName = helper.getView(R.id.tv_name);
            ImageView ivIcon = helper.getView(R.id.iv_left_icon);

            ivIcon.setImageResource(item.leftImg);
            tvName.setText(item.typeName);
        }
    }

    class MenuBean {
        public int leftImg;
        public String typeName;
    }
}
