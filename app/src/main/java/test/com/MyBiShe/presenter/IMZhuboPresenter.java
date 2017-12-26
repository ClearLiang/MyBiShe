package test.com.MyBiShe.presenter;

import android.view.View;

import com.avos.avoscloud.AVObject;

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

    public void sendMessage(String toName, final String text, boolean isTransient) {
        LeanCloudManager.getInstance().sendMessage(toName,text,isTransient);
    }
    public void sendMessage(String text) {
        LeanCloudManager.getInstance().sendMessage(text);
    }

    public AVObject initSave(String objectName){
        AVObject avObject = LeanCloudManager.getInstance().save(objectName);
        return avObject;
    }
    public void saveMessage(AVObject avObject, String myName, String toName, String content){
        LeanCloudManager.getInstance().save(avObject,myName,toName,content);
    }
}
