package test.com.MyBiShe.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.TimeUtils;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.interfaces.WelcomeViewInterface;
import test.com.MyBiShe.presenter.WelcomePresenter;
import test.com.livetest.R;

/**
 * Created by ClearLiang on 2017/12/29.
 */

public class WelcomeActivity extends BaseActivity<WelcomeViewInterface,WelcomePresenter> implements WelcomeViewInterface {
    private int time = 3;
    private TextView mTvWelcomeTime;

    @Override
    protected WelcomePresenter createPresenter() {
        return new WelcomePresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mTvWelcomeTime = (TextView) findViewById(R.id.tv_welcome_time);

        Intent intent = new Intent(this,MainActivity.class);
        final PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC,System.currentTimeMillis()+3000,pi);

    }
}
