package test.com.MyBiShe.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ClearLiang on 2017/12/29.
 */

public class RoomInfoList {
    private List<User> mUserList = new ArrayList<>();

    public RoomInfoList(List<User> userList) {
        mUserList = userList;
    }


    public List<User> getUserList() {
        return mUserList;
    }

    public void setUserList(List<User> userList) {
        mUserList = userList;
    }

}
