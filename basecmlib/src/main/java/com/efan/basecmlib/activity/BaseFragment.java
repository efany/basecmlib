package com.efan.basecmlib.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efan.basecmlib.annotate.ViewInjectUtils;
import com.efan.basecmlib.event.NetStateChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by efan on 2016/2/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, BaseUITask {

    public BaseFragment() {
    }

    protected abstract View inflaterView(LayoutInflater var1, ViewGroup var2, Bundle var3);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = this.inflaterView(inflater, container, savedInstanceState);
        ViewInjectUtils.inJect(this,view);
        this.initView();
        this.initData();
        this.initEvent();
        return view;
    }

    @Override
    public void initView() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(NetStateChangeEvent event) {
    }
}
