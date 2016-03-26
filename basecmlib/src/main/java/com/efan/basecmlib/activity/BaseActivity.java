package com.efan.basecmlib.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.efan.basecmlib.annotate.ViewInjectUtils;
import com.efan.basecmlib.event.NetStateChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by efan on 2016/2/25.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, IActivity, BaseUITask{

    public BaseActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inJect(BaseActivity.this);
        EventBus.getDefault().register(this);
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

    @Subscribe
    public void onEventMainThread(NetStateChangeEvent event) {
    }
}
