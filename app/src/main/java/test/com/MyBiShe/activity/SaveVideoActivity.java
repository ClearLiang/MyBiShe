package test.com.MyBiShe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jiangdg.mediacodec4mp4.RecordMp4;

import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.interfaces.SaveVideoInterface;
import test.com.MyBiShe.presenter.SaveVideoPresenter;
import test.com.MyBiShe.tools.SaveVideoManager;
import test.com.livetest.R;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class SaveVideoActivity extends BaseActivity<SaveVideoInterface,SaveVideoPresenter> implements SaveVideoInterface,SurfaceHolder.Callback ,View.OnClickListener{
    SaveVideoManager videoManager = new SaveVideoManager(getApplicationContext());

    public Button mBtnSaveVideo;
    public Button mBtnSwitchCam;
    public SurfaceView mSurfaceView;

    private boolean isRecording = false;
    private RecordMp4 mRecMp4;

    @Override
    protected SaveVideoPresenter createPresenter() {
        return new SaveVideoPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_save);

        initView();
        initEvent();
    }

    private void initEvent() {
        mBtnSaveVideo.setOnClickListener(this);
        mBtnSwitchCam.setOnClickListener(this);
    }

    private void initView() {
        mBtnSaveVideo = (Button) findViewById(R.id.btn_video_save_start);
        mBtnSwitchCam = (Button) findViewById(R.id.btn_video_save_switch);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_video_save);

        mSurfaceView.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        videoManager.getRecordMp4().startCamera(surfaceHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        videoManager.getRecordMp4().stopCamera();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //录制
            case R.id.btn_video_save_start:
                if(!isRecording){
                    videoManager.StartSaveVideo();
                    mBtnSaveVideo.setText("停止录像");
                }else{
                    // 4. 停止录制
                    videoManager.StopSaveVideo();
                    mBtnSaveVideo.setText("开始录像");
                }
                isRecording = !isRecording;
                break;
            //切换摄像头
            case R.id.btn_video_save_switch:
                if(isRecording){
                    showMsg("正在录像，无法切换");
                }else {
                    videoManager.SwitchCamera();
                }
                break;
            // 对焦
            case R.id.surface_video_save:
                videoManager.EnableFocus();
                break;
        }
    }
    private void showMsg(String msg){
        Toast.makeText(SaveVideoActivity.this, msg,Toast.LENGTH_SHORT).show();
    }
}
