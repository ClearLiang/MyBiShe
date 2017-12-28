package test.com.MyBiShe.presenter;

import com.avos.avoscloud.AVObject;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.CameraViewInterface;
import test.com.MyBiShe.tools.LeanCloudManager;

/**
 * Created by ClearLiang on 2017/12/19.
 */

public class CameraPresenter extends BasePresenter<CameraViewInterface> {
    CameraViewInterface mCameraViewInterface;

    public CameraPresenter(CameraViewInterface cameraViewInterface) {
        mCameraViewInterface = cameraViewInterface;
    }

    public void isStartBarrage(boolean flag){
        mCameraViewInterface.startBarrage(flag);
    }

}
