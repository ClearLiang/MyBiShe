package test.com.MyBiShe.interfaces;

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

}
