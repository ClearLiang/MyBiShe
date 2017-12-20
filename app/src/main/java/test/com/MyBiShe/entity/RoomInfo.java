package test.com.MyBiShe.entity;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class RoomInfo {
    private int id;
    private String name;
    // TODO: 2017/12/20 这里需要设置推流地址
    private String pushUrl = "rtmp://...";

    private static RoomInfo sRoomInfo;

    public static RoomInfo getUser() {
        if (sRoomInfo == null) {
            synchronized (User.class) {
                if (sRoomInfo == null) {
                    sRoomInfo = new RoomInfo();
                }
            }
        }
        return sRoomInfo;
    }

    private RoomInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }
}
