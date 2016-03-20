package com.efan.basecmlib.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.efan.basecmlib.annotate.ViewInjectUtils;
import com.efan.basecmlib.application.BaseApplication;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by efan on 2016/2/25.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, BaseUITask {

    protected View mBaseView;

    public BaseFragment() {
    }

    protected abstract void initView(View var1);

    protected abstract View inflaterView(LayoutInflater var1, ViewGroup var2, Bundle var3);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = this.inflaterView(inflater, container, savedInstanceState);
        this.mBaseView = view;
        ViewInjectUtils.inJect(getActivity());
        this.initView();
        this.initView(view);
        this.initData();
        this.initEvent();
        return view;
    }

    public View findViewById(int id) {
        return this.mBaseView != null?this.mBaseView.findViewById(id):null;
    }

    public Context getApplicationContext() {
        return (Context)(this.getActivity() != null?this.getActivity().getApplicationContext(): BaseApplication.getInstance());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
