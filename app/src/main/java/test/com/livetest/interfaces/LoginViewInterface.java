package test.com.livetest.interfaces;

/**
 * Created by dhht on 2017/12/7.
 */

public interface LoginViewInterface {
    //显示loading progress
    void showLoading();
    //隐藏loading progress
    void hideLoading();
    //Toast 消息
    void showMessage(String s);
    //跳转主界面
    void turnMainActivity(Class activity);

}
