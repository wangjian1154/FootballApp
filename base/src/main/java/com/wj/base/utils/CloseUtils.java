package com.wj.base.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by wj on 2018/2/3.
 * Reference from Blankj
 * https://github.com/Blankj/AndroidUtilCode
 */

public class CloseUtils {

    /**
     * 关闭IO
     *
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 安静的关闭IO
     *
     * @param closeables
     */
    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


}
