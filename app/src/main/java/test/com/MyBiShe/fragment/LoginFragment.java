package test.com.MyBiShe.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.entity.User;
import test.com.MyBiShe.presenter.LoginPresenter;
import test.com.MyBiShe.tools.MyDate;
import test.com.livetest.R;
import test.com.MyBiShe.interfaces.LoginViewInterface;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class LoginFragment extends BaseFragment<LoginViewInterface,LoginPresenter> implements LoginViewInterface {
    private Button mBtnLoginLogin,mBtnLoginRegister;
    private EditText mEtLoginUser,mEtLoginPassword;
    private ImageView mIvLoginHead;
    private ProgressDialog waitingDialog;

    Timer timer = new Timer();

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
               //showMessage(mEtLoginUser.getText().toString());
            }
            super.handleMessage(msg);
        };
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initView(view);
        initEvent(view);
        Glide.with(this)
                .load("")
                .placeholder(R.drawable.icon_imgdefault)
                .error(R.drawable.icon_imgfail)
                .into(mIvLoginHead);
        return view;
    }

    private void initView(View view) {
        mBtnLoginLogin = (Button) view.findViewById(R.id.btn_login_login);
        mBtnLoginRegister = (Button) view.findViewById(R.id.btn_login_register);
        mEtLoginUser = (EditText) view.findViewById(R.id.et_login_user);
        mEtLoginPassword = (EditText) view.findViewById(R.id.et_login_password);
        mIvLoginHead = (ImageView) view.findViewById(R.id.iv_login_head);
    }

    private void initEvent(final View view) {
        RxView.clicks(mBtnLoginLogin)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mBundle.putString("myName",mEtLoginUser.getText().toString());
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.container, RoomListFragment.newInstance());
                        ft.commit();
                        /*showLoading();
                        timer.schedule(myTimer(),0,3000);*/
                        /*switch (mPresenter.CheckUser(mEtLoginUser.getText().toString(), mEtLoginPassword.getText().toString())) {
                            case "成功":
                                showLoading();
                                timer.schedule(myTimer(),0,3000);
                                break;
                            case "密码错误":
                                Toast.makeText(view.getRootView().getContext(), "密码错误", Toast.LENGTH_LONG).show();
                                mEtLoginPassword.setText("");
                                break;
                            case "用户不存在":
                                Toast.makeText(view.getRootView().getContext(), "用户不存在", Toast.LENGTH_LONG).show();
                                mEtLoginPassword.setText("");
                                mEtLoginUser.setText("");
                                break;
                            default:
                                Toast.makeText(view.getRootView().getContext(), "未知错误，请稍候再试", Toast.LENGTH_LONG).show();
                                break;
                        }*/
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
        waitingDialog = new ProgressDialog(getContext());
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
        Toast.makeText(getContext(),"欢迎 "+s+" 登陆", Toast.LENGTH_LONG).show();
    }

    @Override
    public void turnMainActivity() {
        /* 根据用户名默认初始化一个用户 */
        User.getUser().setUserId(MyDate.getHMS());
        User.getUser().setUserHeadImg(String.valueOf(R.drawable.icon_imgdefault));
        User.getUser().setUserName(mEtLoginUser.getText().toString());
        //mPresenter.replaceFragment(getFragmentManager());
    }

    @Override
    public TimerTask myTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 需要做的事:发送消息
                hideLoading();
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
                turnMainActivity();
            }
        };
        return task;
    }




}
