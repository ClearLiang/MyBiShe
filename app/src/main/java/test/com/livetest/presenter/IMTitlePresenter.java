package test.com.livetest.presenter;


import test.com.livetest.base.BasePresenter;
import test.com.livetest.interfaces.IMTitleViewInterface;

public class IMTitlePresenter extends BasePresenter<IMTitleViewInterface> {
    private IMTitleViewInterface mIMTitleViewInterface;

    public IMTitlePresenter(IMTitleViewInterface IMTitleViewInterface) {
        mIMTitleViewInterface = IMTitleViewInterface;
    }

}
