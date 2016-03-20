package com.efan.basecmlib.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.efan.basecmlib.event.NetStateChangeEvent;
import com.efan.basecmlib.utils.NetStateUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by efan on 2016/2/26.
 */
public class NetStateReceiver extends BroadcastReceiver {
    public NetStateReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info != null && info.isConnected()) {
            if(info.getState() == NetworkInfo.State.CONNECTED) {
                EventBus.getDefault().post(new NetStateChangeEvent(true, NetStateUtils.getNetWorkConnectionType(context)));
            } else {
                EventBus.getDefault().post(new NetStateChangeEvent(false, NetStateUtils.NetWorkState.NONE));
            }
        } else {
            EventBus.getDefault().post(new NetStateChangeEvent(false, NetStateUtils.NetWorkState.NONE));
        }

    }
}

