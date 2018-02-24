package com.wj.baseutils.adapter;

import android.app.Service;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wj.base.utils.StringUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeTagBean;

import java.util.List;

/**
 * Created by wj on 2018/1/19.
 */

public class CategoryAdapter extends BaseQuickAdapter<HomeTagBean.DataBean, BaseViewHolder> {

    public CategoryAdapter(@Nullable List<HomeTagBean.DataBean> data) {
        super(R.layout.item_tag, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTagBean.DataBean item) {
        TextView tvTag = helper.getView(R.id.tv_tag);
        tvTag.setText(StringUtils.setStr(item.name));

        helper.getView(R.id.ll_item).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Vibrator vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
                vib.vibrate(150);
                return false;
            }
        });
    }
}
