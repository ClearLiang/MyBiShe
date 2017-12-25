package test.com.MyBiShe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
        LeanCloudManager.initIM(getApplicationContext(),APP_ID,APP_KEY);
        LeanCloudManager.getInstance().initClient("liang");
        LeanCloudManager.getInstance().CreateChatroom();

        mButton = (Button) findViewById(R.id.btn_test);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }
}
