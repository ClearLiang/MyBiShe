package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.PlayViewInterface;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class PlayPresenter extends BasePresenter<PlayViewInterface> {
    PlayViewInterface mPlayViewInterface;

    public PlayPresenter(PlayViewInterface playViewInterface) {
        mPlayViewInterface = playViewInterface;
    }

}
