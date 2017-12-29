package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.ManagerViewInterface;

/**
 * Created by ClearLiang on 2017/12/29.
 */

public class ManagerPresenter extends BasePresenter<ManagerViewInterface> {
    ManagerViewInterface mManagerViewInterface;

    public ManagerPresenter(ManagerViewInterface managerViewInterface) {
        mManagerViewInterface = managerViewInterface;
    }
}
