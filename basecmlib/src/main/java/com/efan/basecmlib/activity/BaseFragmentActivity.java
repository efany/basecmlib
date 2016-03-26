package com.efan.basecmlib.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.efan.basecmlib.annotate.ViewInjectUtils;
import com.efan.basecmlib.event.NetStateChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by efan on 2016/2/26.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener, IActivity, BaseUITask {

    public BaseFragmentActivity() {
    }

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ViewInjectUtils.inJect(BaseFragmentActivity.this);
        this.initialize();
        EventBus.getDefault().register(this);
    }

    public void initialize() {
        this.initView();
        this.initData();
        this.initEvent();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(NetStateChangeEvent event) {
    }
}
