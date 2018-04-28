package com.wj.baseutils.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.wj.base.base.SimpleActivity;
import com.wj.baseutils.R;

public class GalleryActivity extends SimpleActivity {

    public static void show(Context context){
        Intent intent=new Intent(context,GalleryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gallery;
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {

    }
}
