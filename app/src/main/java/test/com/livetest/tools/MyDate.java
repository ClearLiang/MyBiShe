package test.com.livetest.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    /**
     * 获取系统当前 年月日时分秒
     * */
    public static String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 获取系统当前 年月日时分秒
     * */
    public static String getDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 获取系统当前 年月日
     * */
    public static String getYMD(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 获取系统当前 年月日
     * */
    public static String getYMDString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }
    /**
     * 获取系统当前 时分秒
     * */
    public static String getHMS(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

}
