package test.com.livetest.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clearliang.leancloud.R;
import com.example.clearliang.leancloud.base.BaseActivity;
import com.example.clearliang.leancloud.interfaces.LoginViewInterface;
import com.example.clearliang.leancloud.presenter.LoginPresenter;
import com.example.clearliang.leancloud.tools.LeanCloudManager;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;


public class LoginActivity extends BaseActivity<LoginViewInterface,LoginPresenter> implements LoginViewInterface {
    private Button mBtnLoginLogin,mBtnLoginRegister;
    private EditText mEtLoginUser,mEtLoginPassword;

    LoginPresenter mLoginPresenter;
    ProgressDialog waitingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    private void initEvent() {
        RxView.clicks(mBtnLoginLogin)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //turnMainActivity(IMTitleActivity.class);
                        switch (mLoginPresenter.CheckUser(mEtLoginUser.getText().toString(), mEtLoginPassword.getText().toString())){
                            case "成功":
                                showLoading();
                                try {
                                    Thread.sleep(3000);
                                    turnMainActivity(IMActivity.class);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "密码错误":
                                Toast.makeText(LoginActivity.this,"密码错误", Toast.LENGTH_LONG).show();
                                mEtLoginPassword.setText("");
                                break;
                            case "用户不存在":
                                Toast.makeText(LoginActivity.this,"用户不存在", Toast.LENGTH_LONG).show();
                                mEtLoginPassword.setText("");
                                mEtLoginUser.setText("");
                                break;
                            default:
                                Toast.makeText(LoginActivity.this,"未知错误，请稍候再试", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
        RxView.clicks(mBtnLoginRegister)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        LeanCloudManager.getInstance().initClient(mEtLoginUser.getText().toString());
                        Intent i = new Intent(LoginActivity.this,IMTitleActivity.class);
                        i.putExtra("myName",mEtLoginUser.getText().toString());
                        startActivity(i);
                    }
                });
    }

    private void initView() {
        mBtnLoginLogin = findViewById(R.id.btn_login_login);
        mBtnLoginRegister = findViewById(R.id.btn_login_register);
        mEtLoginUser = findViewById(R.id.et_login_user);
        mEtLoginPassword = findViewById(R.id.et_login_password);
    }

    @Override
    public void showLoading() {
        waitingDialog=
                new ProgressDialog(LoginActivity.this);
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
        Toast.makeText(LoginActivity.this,"欢迎 "+s+" 登陆", Toast.LENGTH_LONG).show();
    }

    @Override
    public void turnMainActivity(Class activity) {
        Intent i = new Intent(LoginActivity.this,activity);
        startActivity(i);
    }

}
