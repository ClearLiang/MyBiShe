package test.com.MyBiShe.presenter;


import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.IMTitleViewInterface;

public class IMTitlePresenter extends BasePresenter<IMTitleViewInterface> {
    private IMTitleViewInterface mIMTitleViewInterface;

    public IMTitlePresenter(IMTitleViewInterface IMTitleViewInterface) {
        mIMTitleViewInterface = IMTitleViewInterface;
    }

}
