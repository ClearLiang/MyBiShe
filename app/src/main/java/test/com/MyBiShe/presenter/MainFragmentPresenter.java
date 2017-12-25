package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.MainFragmentViewInterface;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class MainFragmentPresenter extends BasePresenter<MainFragmentViewInterface> {
    MainFragmentViewInterface mMainFragmentViewInterface;

    public MainFragmentPresenter(MainFragmentViewInterface mainFragmentViewInterface) {
        mMainFragmentViewInterface = mainFragmentViewInterface;
    }
}
