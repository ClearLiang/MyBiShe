package test.com.MyBiShe.tools;

/**
 * Created by dhht on 2017/12/18.
 * Event事件
 */

public class Event<T> {
    private int code;
    private T data;

    public Event(T data) {
        this.data = data;
    }

    public Event(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}