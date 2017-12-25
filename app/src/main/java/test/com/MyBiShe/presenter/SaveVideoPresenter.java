package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.SaveVideoInterface;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class SaveVideoPresenter extends BasePresenter<SaveVideoInterface> {
    SaveVideoInterface mSaveVideoInterface;

    public SaveVideoPresenter(SaveVideoInterface saveVideoInterface) {
        mSaveVideoInterface = saveVideoInterface;
    }
}
