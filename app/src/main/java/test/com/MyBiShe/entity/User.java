package test.com.MyBiShe.entity;

/**
 * Created by ClearLiang on 2017/12/20.
 * 自定义用户单例
 */

public class User {
    private String userId;          //判断用户是管理员、主播、观众
    private String userName;        //根据用户name去创建对话
    private String userHeadImg;     //用户头像，聊天室用到
    private String userPermissions; //用户权限，是否具有开启直播间的权限

    private static User sUser;

    public static User getUser() {
        if (sUser == null) {
            synchronized (User.class) {
                if (sUser == null) {
                    sUser = new User();
                }
            }
        }
        return sUser;
    }

    private User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserPermissions() {
        return userPermissions;
    }

    public void setUserPermissions(String userPermissions) {
        this.userPermissions = userPermissions;
    }
}
