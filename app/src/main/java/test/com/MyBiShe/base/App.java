package test.com.MyBiShe.base;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;

import test.com.MyBiShe.tools.LeanCloudManager;


public class App extends Application {

  // 此 id 与 key 仅供测试使用
  private final String APP_ID = "zNuggiKPXeQDfostTM7O8i7R-gzGzoHsz";
  private final String APP_KEY = "LIrsg2Fic3YAMgCMd32BxPH6";

  @Override
  public void onCreate() {
    super.onCreate();
    AVOSCloud.setDebugLogEnabled(true);
    LeanCloudManager.getInstance().initIM(getApplicationContext(), APP_ID, APP_KEY);
    AVIMClient.setAutoOpen(false);
  }
}
