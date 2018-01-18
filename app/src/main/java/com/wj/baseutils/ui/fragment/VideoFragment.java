package com.wj.baseutils.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wj.base.base.BaseFragment;
import com.wj.baseutils.R;
import com.wj.baseutils.app.Constants;
import com.wj.baseutils.bean.HomeDataBean;
import com.wj.baseutils.contract.HomeSupportContract;
import com.wj.baseutils.model.HomeSupportModelImpl;
import com.wj.baseutils.presenter.HomeSupportPresenterImpl;
import com.wj.baseutils.ui.adapter.VideoListAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wj on 2018/1/18.
 */

public class VideoFragment extends BaseFragment<HomeSupportPresenterImpl, HomeSupportModelImpl>
        implements HomeSupportContract.HomeSupportView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<HomeDataBean.DataBean.PostsBeanX> posts;
    private String key;
    private VideoListAdapter adapter;

    @Override
    protected HomeSupportPresenterImpl createPresenter() {
        return new HomeSupportPresenterImpl();
    }

    @Override
    protected HomeSupportModelImpl createModel() {
        return new HomeSupportModelImpl();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mPresenter.loadData(true, key);
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        key = bundle.getString(Constants.Key.KEY);

        posts = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VideoListAdapter(posts);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration
                .Builder(getActivity())
                .colorResId(R.color.decoration_color)
                .build());
    }

    @Override
    public void setTopNewsData(boolean isRefresh, HomeDataBean homeDataBean) {
        if (isRefresh) {
            posts.clear();
        }
        if (homeDataBean != null
                && homeDataBean.data != null
                && homeDataBean.data.posts != null
                && homeDataBean.data.posts.size() > 0) {
            posts.addAll(homeDataBean.data.posts);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

}
