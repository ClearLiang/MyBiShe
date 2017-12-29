package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.WelcomeViewInterface;

/**
 * Created by ClearLiang on 2017/12/29.
 */

public class WelcomePresenter extends BasePresenter<WelcomeViewInterface> {
    WelcomeViewInterface mWelcomeViewInterface;

    public WelcomePresenter(WelcomeViewInterface welcomeViewInterface) {
        mWelcomeViewInterface = welcomeViewInterface;
    }
}
