package com.wj.base.utils;

import android.icu.text.UFormat;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wj on 2018/2/3.
 * Reference from Blankj
 * https://github.com/Blankj/AndroidUtilCode
 */

public class FileUtils {

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param filePath
     * @return
     */
    public static boolean createOrExistsFile(String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在，不存在则判断是否创建成功
     *
     * @param file
     * @return
     */
    public static boolean createOrExistsFile(File file) {
        if (file == null) return false;
        // 如果存在，是文件则返回 true，是目录则返回 false
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param dirPath
     * @return
     */
    public static boolean createOrExistsDir(final String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 判断目录是否存在，不存在则判断是否创建成功
     *
     * @param file
     * @return
     */
    public static boolean createOrExistsDir(final File file) {
        // 如果存在，是目录则返回 true，是文件则返回 false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    /**
     * 根据文件路径获取文件
     *
     * @param filePath
     * @return
     */
    public static File getFileByPath(String filePath) {
        return StringUtils.isEmpty(filePath) ? null : new File(filePath);
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExists(String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    /**
     * 判断是否是文件
     *
     * @param filePath
     * @return
     */
    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    /**
     * 判断是否是文件
     *
     * @param file
     * @return
     */
    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    /**
     * 获取文件的大小
     *
     * @param filePath
     * @return
     */
    public static String getFileSize(String filePath) {
        long len = getFileLength(filePath);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    /**
     * 获取文件的大小
     *
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        long len = getFileLength(file);
        return len == -1 ? "" : byte2FitMemorySize(len);
    }

    /**
     * 获取文件长度
     *
     * @param filePath
     * @return
     */
    public static long getFileLength(String filePath) {
        boolean isURL = filePath.matches("[a-zA-z]+://[^\\s]*");
        if (isURL) {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(filePath).openConnection();
                conn.setRequestProperty("Accept-Encoding", "identity");
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    return conn.getContentLength();
                }
                return -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileLength(getFileByPath(filePath));
    }

    /**
     * 获取文件长度
     *
     * @param file
     * @return
     */
    public static long getFileLength(File file) {
        if (!isFile(file)) return -1;
        return file.length();
    }

    /**
     * 字节数转合适内存大小
     * 保留3位小数
     *
     * @param byteNum
     */
    private static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < 1024) {
            return String.format("%3.fB", (double) byteNum);
        } else if (byteNum < 1048576) {
            return String.format("%3.fKB", (double) byteNum);
        } else if (byteNum < 1073741824) {
            return String.format("%3.fMB", (double) byteNum);
        } else {
            return String.format("%3.fGB", (double) byteNum);
        }
    }
}
