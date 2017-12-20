package test.com.MyBiShe.tools;

import android.os.Handler;
import android.os.Message;

import java.util.TimerTask;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class MyTimer {

    public static TimerTask setTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 需要做的事
            }
        };
        return task;
    }

    public static TimerTask setTimer(final Handler handler){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 需要做的事:发送消息
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        return task;
    }
}
