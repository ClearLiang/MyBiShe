package test.com.MyBiShe.presenter;

import android.support.v4.app.FragmentManager;

import test.com.livetest.R;
import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.fragment.LoginFragment;
import test.com.MyBiShe.interfaces.MainViewInterface;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class MainPresenter extends BasePresenter<MainViewInterface> {
    MainViewInterface mMainViewInterface;

    public MainPresenter(MainViewInterface mainViewInterface) {
        mMainViewInterface = mainViewInterface;
    }

    public void initFragment(FragmentManager manager) {
        manager.beginTransaction()
                .add(R.id.container, LoginFragment.newInstance(), "login")
                .commit();
    }
}