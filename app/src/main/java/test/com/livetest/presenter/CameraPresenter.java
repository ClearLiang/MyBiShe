package test.com.livetest.presenter;

import test.com.livetest.base.BasePresenter;
import test.com.livetest.interfaces.CameraViewInterface;

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
