package test.com.MyBiShe.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import test.com.MyBiShe.entity.User;
import test.com.MyBiShe.fragment.MyFragment;
import test.com.MyBiShe.fragment.RoomListFragment;
import test.com.MyBiShe.fragment.StartLiveFragment;
import test.com.MyBiShe.presenter.MainPresenter;
import test.com.livetest.R;
import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.interfaces.MainViewInterface;


/**
 * Created by ClearLiang on 2017/12/20.
 */

public class MainActivity extends BaseActivity<MainViewInterface,MainPresenter> implements MainViewInterface {
    private FloatingActionButton mFab;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.initFragment(getSupportFragmentManager());
        initView();
        initEvent();
        initNavigationView();
    }

    private void initNavigationView() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(RoomListFragment.newInstance());
                        return true;
                    case R.id.navigation_dashboard:
                        switchFragment(StartLiveFragment.newInstance());
                        return true;
                    case R.id.navigation_notifications:
                        switchFragment(MyFragment.newInstance());
                        return true;

                }
                return false;
            }
        });
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
                        if(User.getUser().getUserPermissions().equals("有")){
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.frame_main_container, StartLiveFragment.newInstance());
                            ft.commit();
                        }else {
                            Toast.makeText(MainActivity.this,User.getUser().getUserName()+"您不具有开启直播间权限!",Toast.LENGTH_LONG).show();
                        }
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
            ft.replace(R.id.frame_main_container, RoomListFragment.newInstance());
            ft.commit();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void showNormalDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void switchFragment(android.support.v4.app.Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main_container, fragment, fragment.getTag())
                .commit();
    }

}
