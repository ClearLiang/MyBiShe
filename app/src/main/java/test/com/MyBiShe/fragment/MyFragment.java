package test.com.MyBiShe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.interfaces.MyViewInterface;
import test.com.MyBiShe.presenter.MyPresenter;
import test.com.livetest.R;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class MyFragment extends BaseFragment<MyViewInterface,MyPresenter> implements MyViewInterface {

    @Override
    protected MyPresenter createPresenter() {
        return new MyPresenter(this);
    }

    public static Fragment newInstance() {
        return new MyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }
}
