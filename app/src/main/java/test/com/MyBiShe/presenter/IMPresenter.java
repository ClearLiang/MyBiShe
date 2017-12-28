package test.com.MyBiShe.presenter;

import com.avos.avoscloud.AVObject;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.IMViewInterface;
import test.com.MyBiShe.tools.LeanCloudManager;


public class IMPresenter extends BasePresenter<IMViewInterface> {
    IMViewInterface mIMViewInterface;

    public IMPresenter(IMViewInterface IMViewInterface) {
        mIMViewInterface = IMViewInterface;
    }

}
