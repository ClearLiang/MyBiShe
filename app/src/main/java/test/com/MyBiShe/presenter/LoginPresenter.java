package test.com.MyBiShe.presenter;


import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.LoginViewInterface;

public class LoginPresenter extends BasePresenter<LoginViewInterface> {
    private LoginViewInterface mLoginViewInterface;

    public LoginPresenter(LoginViewInterface loginViewInterface) {
        mLoginViewInterface = loginViewInterface;
    }

    public String CheckUser(String user, String password){
        if(user .equals("1")|| user .equals("2")||user .equals("3")){
            if(password.equals("123")){
                return "成功";
            }else {
                return "密码错误";
            }
        }else{
            return "用户不存在";
        }
    }

}
