package test.com.livetest.presenter;

import android.support.v4.app.FragmentManager;

import test.com.livetest.R;
import test.com.livetest.base.BasePresenter;
import test.com.livetest.fragment.LoginFragment;
import test.com.livetest.interfaces.MainViewInterface;

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
