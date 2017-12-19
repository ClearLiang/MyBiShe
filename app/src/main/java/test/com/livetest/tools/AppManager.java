package test.com.livetest.tools;

import android.app.Activity;

import java.util.Stack;

public class AppManager {
    public static Stack<Activity> sActivityStack;
    private static AppManager sAppManager;

    private AppManager() {

    }

    /**
     * 获取AppManager单例
     *
     * @return
     * */
    public static AppManager getAppManager() {
        if(sAppManager == null){
            synchronized (AppManager.class){
                sAppManager = new AppManager();
            }
        }
        return sAppManager;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity
     * */
    public void addActivity(Activity activity){
        if(sActivityStack == null){
            sActivityStack = new Stack<>();
        }
        sActivityStack.add(activity);
    }

    /**
     * 获取当前Activity
     *
     * @return
     * */
    public Activity getCurrentActivity(){
        Activity activity = sActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity
     * */
    public void finishCurrentActivity(){
        Activity activity = sActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定Activity
     *
     *@param activity
     * */
    public void finishActivity(Activity activity){
        if(activity != null){
            sActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     *
     * @param classActivity
     * */
    public void finishActivity(Class<?> classActivity){
        for(Activity activity:sActivityStack){
            if(activity.getClass().equals(classActivity)){
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有的Activity
     * */
    public void finishActivity(){
        for(int i = 0;i<sActivityStack.size();i++){
            if(sActivityStack.get(i) != null){
                sActivityStack.get(i).finish();
            }
        }
    }





}
