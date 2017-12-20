package test.com.livetest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import test.com.livetest.R;
import test.com.livetest.base.BaseActivity;
import test.com.livetest.interfaces.MainViewInterface;
import test.com.livetest.presenter.MainPresenter;


/**
 * Created by ClearLiang on 2017/12/20.
 */

public class MainActivity extends BaseActivity<MainViewInterface,MainPresenter> implements MainViewInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);
        mPresenter.initFragment(getSupportFragmentManager());
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }
}
