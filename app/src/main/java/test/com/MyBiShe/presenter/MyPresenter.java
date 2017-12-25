package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.MyViewInterface;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class MyPresenter extends BasePresenter<MyViewInterface> {
    MyViewInterface mMyViewInterface;

    public MyPresenter(MyViewInterface myViewInterface) {
        mMyViewInterface = myViewInterface;
    }
}
