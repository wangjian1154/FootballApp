package com.wj.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.wj.base.data.Constants;
import com.wj.base.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wj on 2018/2/8.
 * 网络状态变化
 */

public class NetStateChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            EventBus.getDefault().post(new Handler(Looper.getMainLooper()).obtainMessage(
                    Constants.Key_EventBus_Msg.NET_CONNECT_SUCCESS));
        }
    }
}
