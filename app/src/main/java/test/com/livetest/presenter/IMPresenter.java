package test.com.livetest.presenter;

import com.avos.avoscloud.AVObject;

import test.com.livetest.base.BasePresenter;
import test.com.livetest.interfaces.IMViewInterface;
import test.com.livetest.tools.LeanCloudManager;


public class IMPresenter extends BasePresenter<IMViewInterface> {
    IMViewInterface mIMViewInterface;

    public IMPresenter(IMViewInterface IMViewInterface) {
        mIMViewInterface = IMViewInterface;
    }

    public void sendMessage(String toName, final String text, boolean isTransient) {
        LeanCloudManager.getInstance().sendMessage(toName,text,isTransient);
    }

    public AVObject initSave(String objectName){
        AVObject avObject = LeanCloudManager.getInstance().save(objectName);
        return avObject;
    }
    public void saveMessage(AVObject avObject, String myName, String toName, String content){
        LeanCloudManager.getInstance().save(avObject,myName,toName,content);
    }
}
