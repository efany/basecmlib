package com.efan.basecmlib_sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.efan.basecmlib.activity.BaseFragment;
import com.efan.basecmlib.annotate.OnClick;
import com.efan.basecmlib.annotate.ViewInject;

/**
 * Created by 一帆 on 2016/3/23.
 */
public class MyFragment extends BaseFragment {
    @ViewInject(id = R.id.text)
    private TextView textView;

    public MyFragment() {
    }

    @Override
    protected View inflaterView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        View view = var1.inflate(R.layout.fragment, var2, false);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        textView.setText("egweg");
    }

    @Override
    @OnClick(value = {R.id.text})
    public void onClick(View v) {
        Toast.makeText(getActivity(),"erherg",Toast.LENGTH_SHORT).show();
    }
}
