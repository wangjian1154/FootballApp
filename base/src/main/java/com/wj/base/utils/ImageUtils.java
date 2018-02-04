package com.wj.base.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;

import com.wj.base.Initialization;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wj on 2018/2/3.
 * Reference from Blankj
 */
public class ImageUtils {

    //App默认图片保存位置文件夹的名称
    public static final String NORMAL_PIC_SAVE_DIR_NAME = "Pictures";

    /**
     * 保存图片到指定文件夹
     *
     * @param bitmap   图片文件
     * @param dirPath  文件夹路径
     * @param fileName 文件名称
     * @return
     */
    public static boolean saveImg(Bitmap bitmap, String dirPath, String fileName) {
        boolean ret = false;
        if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) return ret;
        File file = new File(dirPath, fileName);
        if (file.exists()) {
            ToastUtils.showShort("已经保存该张图片了");
            return ret;
        }
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = bitmap.compress(CompressFormat.PNG, 100, os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(os);
            Initialization.getContext().sendBroadcast(
                    new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.parse("file://" + dirPath)));
        }
        return ret;
    }

    /**
     * 获取App图片默认的存储地址
     *
     * @return
     */
    public static String getNormalPictureSaveDir() {
        File normalDir = new File(Environment.getExternalStorageDirectory(), NORMAL_PIC_SAVE_DIR_NAME);
        if (!normalDir.exists()) {
            normalDir.mkdir();
        }
        return normalDir.getAbsolutePath();
    }


}
