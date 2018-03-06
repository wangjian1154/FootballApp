package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wj.base.base.SimpleFragment;
import com.wj.base.utils.PinyinUtils;
import com.wj.base.views.ListIndexView;
import com.wj.baseutils.R;
import com.wj.baseutils.adapter.LinkmanAdapter;
import com.wj.baseutils.bean.LinkmanBean;

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
    private List<LinkmanBean> list;
    private LinkmanAdapter adapter;
    private String[] names={"张sam","赵是","王3","刘","李是","千是","诸葛","吴看","吗话","朱工","清风","明月","徐飞"};

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

        initData();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new LinkmanAdapter(list);
        recyclerView.setAdapter(adapter);
        listIndexView.setTextViewDialog(tvListInCenter);


        listIndexView.setUpdateListView(new ListIndexView.UpdateListView() {
            @Override
            public void updateListView(String currentChar) {
                int positionForSection = adapter.getPositionForSection(currentChar.charAt(0));
                adapter.setSelection(positionForSection);
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

        Collections.sort(list, new Comparator<LinkmanBean>() {
            @Override
            public int compare(LinkmanBean lhs, LinkmanBean rhs) {
                if (lhs.getFirstLetter().contains("#")) {
                    return 1;
                } else if (rhs.getFirstLetter().contains("#")) {
                    return -1;
                } else {
                    return lhs.getFirstLetter().compareTo(rhs.getFirstLetter());
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
    }

}
