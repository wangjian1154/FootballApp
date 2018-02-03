package com.wj.base.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wj on 2018/2/3.
 * Reference from Blankj
 * https://github.com/Blankj/AndroidUtilCode
 */
public class ImageUtils {

    /**
     * 根据文件名判断文件是否为图片
     *
     * @param file
     * @return
     */
    public static boolean isImage(File file) {
        return file != null && file.getPath() != null && isImage(file.getPath());
    }

    /**
     * 根据文件名判断文件是否为图片
     * @param filePath
     * @return
     */
    public static boolean isImage(String filePath) {
        String path = filePath.toUpperCase();
        return path.endsWith(".PNG") || path.endsWith(".JPG")
                || path.endsWith(".JPEG") || path.endsWith(".BMP")
                || path.endsWith(".GIF");
    }

    /**
     * 保存图片
     *
     * @param src      源图片
     * @param filePath 需要保存到的那个文件的路径
     * @param format   格式
     * @return
     */
    public static boolean save(Bitmap src, String filePath,
                               CompressFormat format) {
        return save(src, filePath, format, false);
    }

    /**
     * 保存图片
     *
     * @param src    源图片
     * @param file   要保存的文件
     * @param format 格式
     * @return
     */
    public static boolean save(Bitmap src, File file, CompressFormat format) {
        return save(src, file, format, false);
    }

    /**
     * 保存图片
     *
     * @param src      源图片
     * @param filePath 要保存到的文件路径
     * @param format   格式
     * @param recycle  是否回收
     */
    public static boolean save(final Bitmap src,
                               final String filePath,
                               final CompressFormat format,
                               final boolean recycle) {
        return save(src, FileUtils.getFileByPath(filePath), format, recycle);
    }

    /**
     * @param src     源图片
     * @param file    要保存的文件
     * @param format  格式
     * @param recycle 是否回收
     * @return
     */
    private static boolean save(Bitmap src, File file,
                                CompressFormat format,
                                boolean recycle) {
        if (isEmptyBitmap(src) || createFileByDeleteOldFile(file)) return false;
        OutputStream os = null;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = src.compress(format, 100, os);
            if (recycle && !src.isRecycled()) src.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeIO(os);
        }

        return ret;
    }

    /**
     * 判断Bitmap对象是否为空
     *
     * @param src
     * @return
     */
    public static boolean isEmptyBitmap(Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }

    private static boolean createFileByDeleteOldFile(File file) {
        if (file == null) return false;
        if (file.exists() && !file.delete()) return false;
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        // 如果存在，是目录则返回 true，是文件则返回 false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


}
