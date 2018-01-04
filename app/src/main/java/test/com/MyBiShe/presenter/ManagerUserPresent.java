package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.ManagerUserViewInterface;

/**
 * Created by ClearLiang on 2018/1/2.
 */

public class ManagerUserPresent extends BasePresenter<ManagerUserViewInterface> {

    ManagerUserViewInterface mManagerUserViewInterface;

    public ManagerUserPresent(ManagerUserViewInterface managerUserViewInterface) {
        mManagerUserViewInterface = managerUserViewInterface;
    }
}
