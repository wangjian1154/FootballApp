package com.wj.baseutils.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.LinkmanBean;

import java.util.List;

/**
 * Created by wj on 2018/3/6.
 */

public class LinkmanAdapter extends BaseQuickAdapter<LinkmanBean, BaseViewHolder> implements SectionIndexer {

    private List<LinkmanBean> list;

    public LinkmanAdapter(@Nullable List<LinkmanBean> data) {
        super(R.layout.item_list_linkman, data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkmanBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        LinearLayout llTitle = helper.getView(R.id.ll_title);
        TextView tvTitle = helper.getView(R.id.tv_title);
        tvName.setText(item.name);

        tvTitle.setText(item.getFirstLetter());
        if (helper.getAdapterPosition() == 0) {
            llTitle.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            String firstLetter = list.get(helper.getAdapterPosition() - 1).getFirstLetter();
            if (item.getFirstLetter().equals(firstLetter)) {
                llTitle.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
            } else {
                llTitle.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFirstLetter().charAt(0) == sectionIndex) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (list != null && list.size() > 0) {
            return list.get(position).getFirstLetter().charAt(0);
        }
        return 0;
    }

}
