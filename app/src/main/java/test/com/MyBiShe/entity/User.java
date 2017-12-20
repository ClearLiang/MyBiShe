package test.com.MyBiShe.entity;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class User {
    private String userId;
    private String userName;
    private String userHeadImg;

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
}
