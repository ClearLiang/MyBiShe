package test.com.MyBiShe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import test.com.MyBiShe.fragment.RoomListFragment;
import test.com.MyBiShe.fragment.StartLiveFragment;
import test.com.MyBiShe.interfaces.StartLiveInterface;
import test.com.MyBiShe.presenter.MainPresenter;
import test.com.livetest.R;
import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.interfaces.MainViewInterface;


/**
 * Created by ClearLiang on 2017/12/20.
 */

public class MainActivity extends BaseActivity<MainViewInterface,MainPresenter> implements MainViewInterface {
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.initFragment(getSupportFragmentManager());
        initView();
        initEvent();
    }

    private void initView() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initEvent() {
        RxView.clicks(mFab)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.container, StartLiveFragment.newInstance());
                        ft.commit();
                    }
                });
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, RoomListFragment.newInstance());
            ft.commit();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
