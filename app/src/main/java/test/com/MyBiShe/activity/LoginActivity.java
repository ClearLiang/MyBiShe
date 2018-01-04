package test.com.MyBiShe.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.entity.User;
import test.com.MyBiShe.presenter.LoginPresenter;
import test.com.MyBiShe.tools.LeanCloudManager;
import test.com.MyBiShe.tools.MyDate;
import test.com.livetest.R;
import test.com.MyBiShe.interfaces.LoginViewInterface;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class LoginActivity extends BaseActivity<LoginViewInterface,LoginPresenter> implements LoginViewInterface {
    private Button mBtnLoginLogin,mBtnLoginRegister;
    private EditText mEtLoginUser,mEtLoginPassword;
    private ImageView mIvLoginHead;
    private ProgressDialog waitingDialog;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        mBtnLoginLogin = (Button) findViewById(R.id.btn_login_login);
        mBtnLoginRegister = (Button) findViewById(R.id.btn_login_register);
        mEtLoginUser = (EditText) findViewById(R.id.et_login_user);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mIvLoginHead = (ImageView) findViewById(R.id.iv_login_head);
    }

    private void initEvent() {
        RxView.clicks(mBtnLoginLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                        switch (mPresenter.CheckUser(mEtLoginUser.getText().toString(), mEtLoginPassword.getText().toString())) {
                            case "成功":
                                login(mEtLoginUser.getText().toString());
                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                                break;

                            case "密码错误":
                                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
                                mEtLoginPassword.setText("");
                                break;

                            case "用户不存在":
                                Toast.makeText(getApplicationContext(), "用户不存在", Toast.LENGTH_LONG).show();
                                mEtLoginPassword.setText("");
                                mEtLoginUser.setText("");
                                break;

                            default:
                                Toast.makeText(getApplicationContext(), "未知错误，请稍候再试", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
        RxView.clicks(mBtnLoginRegister)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });

    }

    @Override
    public void showLoading() {
        waitingDialog = new ProgressDialog(getApplicationContext());
        waitingDialog.setTitle("信息");
        waitingDialog.setMessage("正在登陆中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }

    @Override
    public void hideLoading() {
        waitingDialog.cancel();
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(getApplicationContext(),"欢迎 "+s+" 登陆", Toast.LENGTH_LONG).show();
    }

    public static void login(String myName) {
        /* 根据用户名默认初始化一个用户 */
        // TODO: 2017/12/29  这里需要查询数据库用户信息
        User.getUser().setUserId(MyDate.getHMS());
        User.getUser().setUserHeadImg(String.valueOf(R.drawable.icon_imgdefault));
        User.getUser().setUserName(myName);
        User.getUser().setUserPermissions("有");

        LeanCloudManager.getInstance().initClient(myName);

    }


}
