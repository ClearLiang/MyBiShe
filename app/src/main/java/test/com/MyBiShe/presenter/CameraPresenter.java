package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.CameraViewInterface;

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
