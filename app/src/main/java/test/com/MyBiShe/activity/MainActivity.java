package test.com.MyBiShe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
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
        setContentView(R.layout.activity_main_start);

        mPresenter.initFragment(getSupportFragmentManager());
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        RxView.clicks(mFab)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
                        startActivity(intent);
                    }
                });
        mPresenter.initFragment(getSupportFragmentManager());
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }
}
