package test.com.livetest.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class BroadcastManager extends BroadcastReceiver {
    private static final String TAG = "信息：";

    public static void sendBroad(Context context, String stringAction){
        Intent intent = new Intent();
        intent.setAction(stringAction);
        context.getApplicationContext().sendBroadcast(intent);
    }

    public static void sendBroad(Context context, String stringAction, Bundle bundle){
        Intent intent = new Intent();
        intent.setAction(stringAction);
        intent.putExtras(bundle);
        context.getApplicationContext().sendBroadcast(intent);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        Log.i(TAG,"内容是："+name);
    }
}
