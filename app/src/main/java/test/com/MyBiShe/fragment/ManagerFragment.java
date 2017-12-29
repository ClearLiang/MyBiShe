package test.com.MyBiShe.fragment;

import android.os.Bundle;

import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.interfaces.ManagerViewInterface;
import test.com.MyBiShe.presenter.ManagerPresenter;

/**
 * Created by ClearLiang on 2017/12/29.
 */

public class ManagerFragment extends BaseFragment<ManagerViewInterface,ManagerPresenter> implements ManagerViewInterface {

    @Override
    protected ManagerPresenter createPresenter() {
        return new ManagerPresenter(this);
    }

    public static ManagerFragment newInstance() {

        Bundle args = new Bundle();

        ManagerFragment fragment = new ManagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
