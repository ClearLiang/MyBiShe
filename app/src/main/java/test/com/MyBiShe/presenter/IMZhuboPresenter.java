package test.com.MyBiShe.presenter;


import android.util.Log;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.IMZhuboViewInterface;
import test.com.MyBiShe.tools.LeanCloudManager;

/**
 * Created by ClearLiang on 2017/12/26.
 */

public class IMZhuboPresenter extends BasePresenter<IMZhuboViewInterface> {
    IMZhuboViewInterface mIMZhuboViewInterface;

    public IMZhuboPresenter(IMZhuboViewInterface IMZhuboViewInterface) {
        mIMZhuboViewInterface = IMZhuboViewInterface;
    }

    public void log(){
        Log.i("信息","测试");
    }
    public void createConversation(){
        LeanCloudManager.getInstance().CreateConversation();
    }

    public void sendIMMessage(String text){
        LeanCloudManager.getInstance().sendMessage(text);
    }

}
