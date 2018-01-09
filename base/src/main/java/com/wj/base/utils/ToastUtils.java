package com.wj.base.utils;

import android.content.Context;
import android.widget.Toast;

import com.wj.base.Initialization;

/**
 * Created by wj on 2018/1/3.
 */

public class ToastUtils {

    // Toast
    private static Toast toast;

    public static final boolean debug = Initialization.DEBUG;

    private static Context appContext = Initialization.getContext();

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        try {
            if (null == toast) {
                if (context == null)
                    return;
                toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.TOP, 0, 300);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception e) {
        }
    }

    public static void showShort(CharSequence message) {
        try {
            if (null == toast) {
                if (message == null)
                    return;
                toast = Toast.makeText(appContext, message, Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.TOP, 0, 300);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception e) {
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        try {
            if (null == toast) {
                if (context == null)
                    return;
                toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
//			toast.setGravity(Gravity.TOP, 0, 300);
            } else {
                toast.setText(message);
            }
            toast.show();
        } catch (Exception e) {

        }
    }

    public static void showDebugShort(Throwable e) {
        if (debug) {
            e.printStackTrace();
            showDebugShort(e.toString());
        }
    }

    public static void showDebugShort(String message) {
        if (debug) {
            try {
                if (null == toast) {
                    toast = Toast.makeText(appContext, message, Toast.LENGTH_SHORT);
                } else {
                    toast.setText(message);
                }
                System.out.println("debugToast::" + message);
                toast.show();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(":::" + e);
            }
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (null == toast) {
            if (context == null)
                return;
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (null == toast) {
            if (context == null)
                return;
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (null == toast) {
            if (context == null)
                return;
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
        if (null == toast) {
            if (context == null)
                return;
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * Hide the toast, if any.
     */
    public static void hideToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}
