package test.com.MyBiShe.presenter;

import test.com.MyBiShe.base.BasePresenter;
import test.com.MyBiShe.interfaces.RoomListViewInterface;


public class RoomListPresenter extends BasePresenter<RoomListViewInterface> {
    RoomListViewInterface mRoomListViewInterface;

    public RoomListPresenter(RoomListViewInterface roomListViewInterface) {
        mRoomListViewInterface = roomListViewInterface;
    }

}
