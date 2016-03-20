package com.efan.basecmlib.event;


import com.efan.basecmlib.utils.NetStateUtils;

/**
 * Created by efan on 2016/2/26.
 */
public class NetStateChangeEvent {
    private boolean netState;
    private NetStateUtils.NetWorkState netType;

    public NetStateChangeEvent(boolean netState, NetStateUtils.NetWorkState netType) {
        this.netState = netState;
        this.netType = netType;
    }

    public boolean isNetState() {
        return this.netState;
    }

    public NetStateUtils.NetWorkState getNetType() {
        return this.netType;
    }

    public void setNetType(NetStateUtils.NetWorkState netType) {
        this.netType = netType;
    }
}
