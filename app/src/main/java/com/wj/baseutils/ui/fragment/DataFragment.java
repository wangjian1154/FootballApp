package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.NumAnim;
import com.wj.base.utils.PinyinUtils;
import com.wj.base.utils.ToastUtils;
import com.wj.base.views.ListIndexView;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.LinkmanAdapter;
import com.wj.baseutils.bean.LinkmanBean;
import com.wj.baseutils.ui.activity.LinkmanDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/11.
 * 数据
 */

public class DataFragment extends SimpleFragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.show_list_in_center)
    TextView tvListInCenter;
    @BindView(R.id.liv)
    ListIndexView listIndexView;
    @BindView(R.id.tv_money_value)
    TextView tvMoneyValue;
    private List<LinkmanBean> list;
    private LinkmanAdapter adapter;
    private String[] names = {"张震岳", "吴亦凡", "潘玮柏", "张韶涵", "李晨", "范冰冰", "诸葛亮", "王祖蓝", "李明亮",
            "朱桢", "薛之谦", "汪涵", "谢娜", "何炅", "苏菲儿", "陈一发", "冯提莫", "胡发", "黄磊", "雷军", "任正飞"
            , "杨过", "罗欧赔", "齐秦", "谢霆锋", "鹿晗", "迪丽热巴", "王嘉尔", "魏大勋", "黄渤", "孙红雷", "罗志祥"
            , "王迅", "杨洋", "张艺兴", "巴宝莉", "奥尼尔", "科比", "Angele Baby", "刘亦菲", "罗永浩", "马云", "马化腾", "丁磊", "周鸿祎"
            , "贾跃亭", "居里夫人", "张杰", "陈旭", "魏晨", "何炅", "杜海涛", "张国荣", "周杰伦", "陈奕迅", "宋冬野", "赵雷",
            "张靓颖", "张惠妹", "梁静茹", "孙燕姿", "李宇春", "陈伟霆", "潘长江", "Ac Fun", "BBQ"};

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initData();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new LinkmanAdapter(list);
        recyclerView.setAdapter(adapter);
        listIndexView.setTextViewDialog(tvListInCenter);

        initEvent();
    }

    private void initEvent() {
        listIndexView.setUpdateListView(currentChar -> {
            int positionForSection = adapter.getPositionForSection(currentChar.charAt(0));
            if (positionForSection >= 0 && positionForSection < list.size()) {
                recyclerView.smoothScrollToPosition(positionForSection);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItem = linearManager.findFirstVisibleItemPosition();
                    int sectionForPosition = adapter.getSectionForPosition(firstVisibleItem);
                    listIndexView.updateLetterIndexView(sectionForPosition);
                }
            }
        });

        adapter.setOnItemClickListener((adapter, view, position) -> {
            LinkmanBean linkmanBean = list.get(position);
            LinkmanDetailActivity.show(getContext(),linkmanBean);
        });
    }

    private void initData() {
        list = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            LinkmanBean user = new LinkmanBean();
            user.mobile = 182905252 + i + "";
            user.name = names[i];
            list.add(user);
            String convert = PinyinUtils.getInstance().getPinyin(user.name).toUpperCase();
            user.setPinyin(convert);
            String substring = convert.substring(0, 1);

            if (substring.matches("[A-Z]")) {
                user.setFirstLetter(substring);
            } else {
                user.setFirstLetter("#");
            }
        }

        Collections.sort(list, (lhs, rhs) -> {

            if (lhs.getFirstLetter() != null) {
                if (lhs.getFirstLetter().contains("#")) {
                    return 1;
                } else if (rhs.getFirstLetter().contains("#")) {
                    return -1;
                } else {
                    return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
                }
            } else {
                return -1;
            }

        });

        NumAnim.startAnim(tvMoneyValue, 9890.00f);

        tvMoneyValue.setOnClickListener(view -> NumAnim.startAnim(tvMoneyValue, 9890.00f));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
    }

}
