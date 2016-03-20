package com.efan.basecmlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by efan on 2016/2/26.
 */
public class NetStateUtils {
    public NetStateUtils() {
    }

    public static final boolean hasNetWorkConnection(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivity)
        {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected())
            {
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean hasWifiConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null)
            return false;
        return (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI)&&cm.getActiveNetworkInfo().isAvailable();
    }

    public static final boolean hasGPRSConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static final NetStateUtils.NetWorkState getNetWorkConnectionType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            return NetWorkState.NONE;
        }
        if (cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_MOBILE){
            return NetWorkState.MOBILE;
        }else if(cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI){
            return NetWorkState.WIFI;
        }else{
            return NetWorkState.NONE;
        }
    }

    public static enum NetWorkState {
        WIFI,
        MOBILE,
        NONE;

        private NetWorkState() {
        }
    }
}

