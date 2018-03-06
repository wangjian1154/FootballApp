package com.wj.baseutils.adapter;

import android.widget.SectionIndexer;
import android.widget.TextView;

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
    private int selectionPos = -1;

    public LinkmanAdapter(List<LinkmanBean> data) {
        super(R.layout.item_list_linkman, data);
        this.list = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkmanBean item) {
        TextView tvName = helper.getView(R.id.tv_name);
        tvName.setText(item.name);
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

    public void setSelection(int selectionPos) {
        this.selectionPos = selectionPos;
    }

}
