package com.wj.base.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by wj on 2018/1/16.
 */

public class ImageLoadUtils {

    public static void display(Context context, Object path, ImageView imageView){
        Glide.with(context).load(path).into(imageView);
    }
}
