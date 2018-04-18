package com.wj.base.utils;

import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.wj.base.Initialization;

import java.util.List;

/**
 * Created by wj on 2018/3/19.
 * 常用的方法
 */

public class BaseUtils {

    /**
     * 获取VersionName
     *
     * @return
     */
    public static String getVersionName() {
        PackageManager pm = Initialization.getContext().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(Initialization.getContext().getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取VersionCode
     *
     * @return
     */
    public static String getVersionCode() {
        PackageManager pm = Initialization.getContext().getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(Initialization.getContext().getPackageName(), 0);
            return info.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取应用的渠道号
     *
     * @return
     */
    public static String getAppChannel() {
        return "360";
    }

    /**
     * 包名判断是否为主进程
     *
     * @return
     */
    public static boolean isMainProcess() {
        return Initialization.getContext().getPackageName().equals(getProcessName());
    }

    /**
     * 获取进程名称
     *
     * @return
     */
    public static String getProcessName() {
        ActivityManager am = (ActivityManager) Initialization.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 进入应用市场评分
     *
     * @param context
     */
    public static void toAppMarketScre(Context context) {
        try {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Couldn't launch the market !", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断通知权限是否打开
     */
    public static boolean isNotificationEnable(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }

    /**
     * 进入通知打开页面
     *
     * @param context
     */
    public static void openNotificationSetting(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
}
