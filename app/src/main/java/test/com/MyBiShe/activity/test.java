package test.com.MyBiShe.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import test.com.MyBiShe.tools.LeanCloudManager;
import test.com.livetest.R;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class test extends AppCompatActivity {
    // 此 id 与 key 仅供测试使用
    private final String APP_ID = "zNuggiKPXeQDfostTM7O8i7R-gzGzoHsz";
    private final String APP_KEY = "LIrsg2Fic3YAMgCMd32BxPH6";
    private String ChatRoomId = "";

    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Calendar c = Calendar.getInstance();
        c.set(2017,12,29,9,50);

        //创建Intent对象，action为ELITOR_CLOCK，附加信息为字符串“你该打酱油了”
        Intent intent = new Intent("ELITOR_CLOCK");
        intent.putExtra("msg","你该打酱油了");

        //定义一个PendingIntent对象，PendingIntent.getBroadcast包含了sendBroadcast的动作。
        //也就是发送了action 为"ELITOR_CLOCK"的intent
        PendingIntent pi = PendingIntent.getBroadcast(this,0,intent,0);

        //AlarmManager对象,注意这里并不是new一个对象，Alarmmanager为系统级服务
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        //设置闹钟从当前时间开始，每隔5s执行一次PendingIntent对象pi，注意第一个参数与第二个参数的关系
        // 5秒后通过PendingIntent pi对象发送广播
        if (am != null) {

            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,c.getTimeInMillis(),pi);
            am.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),5*1000,pi);

        }


    }
}
