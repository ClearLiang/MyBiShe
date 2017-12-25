package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.StartLiveInterface;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class StartLivePresenter extends BasePresenter<StartLiveInterface> {
    StartLiveInterface mStartLiveInterface;

    public StartLivePresenter(StartLiveInterface startLiveInterface) {
        mStartLiveInterface = startLiveInterface;
    }

}
