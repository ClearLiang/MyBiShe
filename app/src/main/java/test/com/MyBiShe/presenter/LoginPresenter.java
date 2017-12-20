package test.com.MyBiShe.presenter;


import android.support.v4.app.FragmentManager;

import test.com.livetest.R;
import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.fragment.IMTitleFragment;
import test.com.MyBiShe.interfaces.LoginViewInterface;

public class LoginPresenter extends BasePresenter<LoginViewInterface> {
    private LoginViewInterface mLoginViewInterface;

    public LoginPresenter(LoginViewInterface loginViewInterface) {
        mLoginViewInterface = loginViewInterface;
    }

    public String CheckUser(String user, String password){
        if(user .equals("123") ){
            if(password.equals("123")){
                return "成功";
            }else {
                return "密码错误";
            }
        }else{
            return "用户不存在";
        }
    }

    public void replaceFragment(FragmentManager manager) {

        manager.beginTransaction()
                .replace(R.id.container, IMTitleFragment.newInstance(), "IMTitle")
                .commit();
    }

}
