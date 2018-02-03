package com.wj.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.wj.base.R;
import com.wj.base.base.SimpleActivity;
import com.wj.base.views.FixedViewPager;

import java.io.Serializable;
import java.util.List;

/**
 * 图片查看器
 */
public class ImageBrowserActivity extends SimpleActivity {

    private FixedViewPager viewPager;
    private TextView tvNum;
    private ImageView ivDownload;

    public static final String PARAMS_IMG_LIST = "photo_url_list";
    public static final String PARAMS_SELECT_POSITION = "select_position";

    private List<String> imgList;
    private int selectPos;

    public static void show(Context context, List<String> imgList, int selectPos) {
        Intent intent = new Intent(context, ImageBrowserActivity.class);
        intent.putExtra(PARAMS_SELECT_POSITION, selectPos);
        intent.putExtra(PARAMS_IMG_LIST, (Serializable) imgList);
        context.startActivity(intent);
    }

    @Override
    protected void initViewAndEvent(Bundle savedInstanceState) {
        viewPager = findViewById(R.id.vp_browser);
        tvNum = findViewById(R.id.tv_num);
        ivDownload = findViewById(R.id.iv_download);

        Intent intent = getIntent();
        imgList = (List<String>) intent.getSerializableExtra(PARAMS_IMG_LIST);
        selectPos = intent.getIntExtra(PARAMS_SELECT_POSITION, 0);
        viewPager.setAdapter(new ImageBrowserAdapter(this, imgList));
        viewPager.setCurrentItem(selectPos);

        tvNum.setText((selectPos + 1) + "/" + imgList.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvNum.setText((position + 1) + "/" + imgList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ImageUtils.save()
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_browser;
    }

    class ImageBrowserAdapter extends PagerAdapter {

        private Context mContext;
        private List<String> data;
        private LayoutInflater inflater;

        public ImageBrowserAdapter(Context mContext, List<String> data) {
            this.mContext = mContext;
            this.data = data;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return data != null ? data.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = inflater.inflate(R.layout.item_vp_image_browser, null);

            PhotoView photoView = itemView.findViewById(R.id.photoView);
            ImageLoadUtils.display(mContext, data.get(position), photoView);

            photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    ((Activity) mContext).finish();
                }
            });

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
