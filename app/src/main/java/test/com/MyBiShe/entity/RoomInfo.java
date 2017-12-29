package test.com.MyBiShe.entity;

/**
 * Created by ClearLiang on 2017/12/20.
 * 直播间单例
 */

public class RoomInfo {
    // TODO: 2017/12/20 这里需要设置推流地址
    private int id;                         //房间id
    private String name;                    //房间名
    private String pushUrl = "rtmp://...";  //推流地址
    private String pullUrl = "rtmp://...";  //拉流地址
    private String convId = "";             //会话id

    private static RoomInfo sRoomInfo;

    public static RoomInfo getRoomInfo() {
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

    public String getPullUrl() {
        return pullUrl;
    }

    public void setPullUrl(String pullUrl) {
        this.pullUrl = pullUrl;
    }

    public String getConvId() {
        return convId;
    }

    public void setConvId(String convId) {
        this.convId = convId;
    }
}
