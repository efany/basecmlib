package com.efan.basecmlib.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.efan.basecmlib.annotate.ViewInjectUtils;
import com.efan.basecmlib.event.NetStateChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by efan on 2016/2/25.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener, IActivity, BaseUITask{

    public BaseActivity() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        this.setRootView();
        ViewInjectUtils.inJect(this);
        this.initialize();
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

    public void onEventMainThread(NetStateChangeEvent event) {
    }
}
