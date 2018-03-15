package com.wj.baseutils.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.wj.base.base.SimpleActivity;
import com.wj.base.data.Constants;
import com.wj.base.utils.ImageUtils;
import com.wj.base.utils.NoFastClickUtils;
import com.wj.base.utils.SpanUtils;
import com.wj.base.utils.ToastUtils;
import com.wj.base.views.TitleBar;
import com.wj.baseutils.R;
import com.wj.baseutils.bean.LinkmanBean;
import com.wj.baseutils.widget.dialog.LinkmanBottomDialog;

import java.util.ArrayList;

import butterknife.BindView;

public class LinkmanDetailActivity extends SimpleActivity {

    @BindView(R.id.lay_title)
    TitleBar layTitle;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
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

        layTitle.setBackButton(v -> finish());

        layTitle.setRightImgButton(R.drawable.ic_more, v -> {
            if (!NoFastClickUtils.isDoubleClick()) {
                LinkmanBottomDialog dialog = new LinkmanBottomDialog(LinkmanDetailActivity.this);
                dialog.show();
            }
        });

        setSupportActionBar(toolbar);
        searchView.setVoiceSearch(true);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ToastUtils.showShort(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_linkman_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
