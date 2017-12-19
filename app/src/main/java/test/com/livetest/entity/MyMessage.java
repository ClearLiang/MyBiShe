package test.com.livetest.entity;

/**
 * Created by dhht on 2017/12/13.
 */

public class MyMessage {
    public static final int TYPE_SEND    = 0;
    public static final int TYPE_RECEIVE = 1;

    private String time;    //时间
    private String name;    //发送者
    private String content; //内容
    private int type;       //类型
    private String icon;    //头像路径

    public MyMessage() {
    }

    public MyMessage(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
