package com.efan.basecmlib.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.efan.basecmlib.annotate.ViewInjectUtils;
import com.efan.basecmlib.application.BaseApplication;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by efan on 2016/2/26.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener, IActivity, BaseUITask {

    protected BaseApplication mApplication;

    public BaseFragmentActivity() {
    }

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.mApplication = (BaseApplication)this.getApplication();
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
}
