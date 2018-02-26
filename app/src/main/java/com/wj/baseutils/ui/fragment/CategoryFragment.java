package com.wj.baseutils.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.animation.Animation;

import com.wj.base.base.BaseFragment;
import com.wj.base.data.Constants;
import com.wj.base.utils.SPUtils;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.HomeTagBean;
import com.wj.baseutils.contract.CategoryContract;
import com.wj.baseutils.model.CategoryModelImpl;
import com.wj.baseutils.presenter.CategoryPresenterImpl;
import com.wj.baseutils.adapter.CategoryAdapter;
import com.wj.baseutils.widget.ItemDragHelperCallBack;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wj on 2018/1/18.
 */

@SuppressLint("ValidFragment")
public class CategoryFragment extends BaseFragment<CategoryPresenterImpl, CategoryModelImpl>
        implements CategoryContract.CategoryView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<HomeTagBean.DataBean> tagList;
    public static final String KEY = "key";
    public static final String KEY_FRAGMENT = "key_fragment";
    public static final String KEY_SELECT_CATEGORY = "select_category";
    private CategoryAdapter adapter;
    private HomeFragment.OnCategoryChangeCallback onCategoryChangeCallback;
    private String selectCategory;
    private int selectPosition;

    @SuppressLint("ValidFragment")
    public CategoryFragment(HomeFragment.OnCategoryChangeCallback onCategoryChangeCallback) {
        this.onCategoryChangeCallback = onCategoryChangeCallback;
    }

    @Override
    protected CategoryPresenterImpl createPresenter() {
        return new CategoryPresenterImpl();
    }

    @Override
    protected CategoryModelImpl createModel() {
        return new CategoryModelImpl();
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        HomeTagBean bean = (HomeTagBean) bundle.getSerializable(KEY);
        selectCategory = bundle.getString(KEY_SELECT_CATEGORY);
        tagList = new ArrayList<>();
        tagList.clear();
        tagList.addAll(bean.data);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        adapter = new CategoryAdapter(tagList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemDragHelperCallBack(
                new ItemDragHelperCallBack.OnChannelDragListener() {
                    @Override
                    public void onItemMove(int starPos, int endPos) {
                        Collections.swap(tagList, starPos, endPos);
                        Collections.swap(HomeFragment.fragments, starPos, endPos);
                        adapter.notifyItemMoved(starPos, endPos);
                        if (onCategoryChangeCallback != null) {
                            for (int i = 0; i < tagList.size(); i++) {
                                if (tagList.get(i).name.equals(selectCategory)) {
                                    selectPosition = i;
                                    break;
                                }
                            }

                        }
                        SPUtils.getInstance().put(Constants.SHARE_PREFENCE_KEY.SP_CATEGORY, tagList);
                    }
                }));

        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void setTagData(HomeTagBean tagBean) {

    }

    @Override
    public boolean onBackPressed() {
        return exitFragment();
    }

    public boolean exitFragment() {
        if (this != null && !this.isHidden() && this.isVisible()) {
            onCategoryChangeCallback.onCategoryChange(tagList, selectPosition);
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.category_enter, R.anim.category_exit, R.anim.category_enter, R.anim.category_exit)//设置退出动画
                    .remove(this).commit();
            return true;
        }
        return false;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @OnClick(R.id.ll_hide)
    public void hideFragment() {
        exitFragment();
    }

}
