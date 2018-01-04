package test.com.MyBiShe.activity;

import android.os.Bundle;

import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.interfaces.ManagerUserViewInterface;
import test.com.MyBiShe.presenter.ManagerUserPresent;

/**
 * Created by ClearLiang on 2018/1/2.
 */

public class ManagerUserActivity extends BaseActivity<ManagerUserViewInterface,ManagerUserPresent> implements ManagerUserViewInterface {

    @Override
    protected ManagerUserPresent createPresenter() {
        return new ManagerUserPresent(this);
    }


}
