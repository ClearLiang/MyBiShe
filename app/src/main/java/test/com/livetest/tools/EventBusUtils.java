package test.com.livetest.tools;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dhht on 2017/12/18.
 * EventBus工具类
 */

public class EventBusUtils {

    public static void register(Object subscribe){
        EventBus.getDefault().register(subscribe);
    }

    public static void unregister(Object subscribe){
        EventBus.getDefault().unregister(subscribe);
    }

    public static void sendEvent(Event event){
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event){
        EventBus.getDefault().postSticky(event);
    }

    public class EventCode{
        public static final int MAIN_FRAGMENT = 0x0001;
    }
}
