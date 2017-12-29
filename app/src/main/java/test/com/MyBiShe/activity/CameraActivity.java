package test.com.MyBiShe.activity;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.avos.avoscloud.AVObject;
import com.github.faucamp.simplertmp.RtmpHandler;
import com.seu.magicfilter.utils.MagicFilterType;

import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;

import java.io.IOException;
import java.net.SocketException;

import test.com.MyBiShe.entity.User;
import test.com.MyBiShe.fragment.IMZhuboFragment;
import test.com.MyBiShe.tools.EventBusUtils;
import test.com.MyBiShe.tools.LeanCloudManager;
import test.com.livetest.R;
import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.interfaces.CameraViewInterface;
import test.com.MyBiShe.presenter.CameraPresenter;


public class CameraActivity extends BaseActivity<CameraViewInterface,CameraPresenter> implements SrsEncodeHandler.SrsEncodeListener, RtmpHandler.RtmpListener, SrsRecordHandler.SrsRecordListener, View.OnClickListener ,CameraViewInterface{
    private static final String TAG = "信息";

    private Button mPublishBtn;
    private Button mCameraSwitchBtn;
    private Button mEncoderBtn;
    private Button mStartBarrageBtn;
    private EditText mRempUrlEt;
    private SrsPublisher mPublisher;
    private String rtmpUrl;
    private FrameLayout mFrameLayout;

    @Override
    protected CameraPresenter createPresenter() {
        return new CameraPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_video_push);

        initView();
        initPublisher();
        initBarrageFragment();
    }

    private void initBarrageFragment() {
        IMZhuboFragment fragment = new IMZhuboFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_video_push,fragment);
        ft.commit();

    }

    private void initPublisher() {
        mPublisher = new SrsPublisher((SrsCameraView) findViewById(R.id.glsurfaceview_camera));
        //编码状态回调
        mPublisher.setEncodeHandler(new SrsEncodeHandler(this));
        mPublisher.setRecordHandler(new SrsRecordHandler(this));
        //rtmp推流状态回调
        mPublisher.setRtmpHandler(new RtmpHandler(this));
        //预览分辨率
        mPublisher.setPreviewResolution(1280, 720);
        //推流分辨率
        mPublisher.setOutputResolution(720, 1280);
        //传输率
        mPublisher.setVideoHDMode();
        //开启美颜（其他滤镜效果在MagicFilterType中查看）
        mPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
        //打开摄像头，开始预览（未推流）
        mPublisher.startCamera();
    }

    private void initView() {
        mPublishBtn = (Button) findViewById(R.id.btn_video_push_publish);
        mCameraSwitchBtn = (Button) findViewById(R.id.btn_video_push_swCam);
        mEncoderBtn = (Button) findViewById(R.id.btn_video_push_swEnc);
        mStartBarrageBtn = (Button) findViewById(R.id.btn_video_push_barrage);
        mRempUrlEt = (EditText) findViewById(R.id.et_video_push_url);
        mPublishBtn.setOnClickListener(this);
        mCameraSwitchBtn.setOnClickListener(this);
        mEncoderBtn.setOnClickListener(this);
        mStartBarrageBtn.setOnClickListener(this);

        mFrameLayout = (FrameLayout) findViewById(R.id.frame_video_push);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //开始/停止推流
            case R.id.btn_video_push_publish:
                if (mPublishBtn.getText().toString().contentEquals("开始")) {
                    rtmpUrl = mRempUrlEt.getText().toString();
                    if (TextUtils.isEmpty(rtmpUrl)) {
                        Toast.makeText(getApplicationContext(), "地址不能为空！", Toast.LENGTH_SHORT).show();
                    }
                    mPublisher.startPublish(rtmpUrl);
                    mPublisher.startCamera();

                    if (mEncoderBtn.getText().toString().contentEquals("软编码")) {
                        Toast.makeText(getApplicationContext(), "当前使用硬编码", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "当前使用软编码", Toast.LENGTH_SHORT).show();
                    }
                    mPublishBtn.setText("停止");
                    mEncoderBtn.setEnabled(false);
                } else if (mPublishBtn.getText().toString().contentEquals("停止")) {
                    mPublisher.stopPublish();
                    mPublisher.stopRecord();
                    mPublishBtn.setText("开始");
                    mEncoderBtn.setEnabled(true);

                }
                break;
            //切换摄像头
            case R.id.btn_video_push_swCam:
                mPublisher.switchCameraFace((mPublisher.getCamraId() + 1) % Camera.getNumberOfCameras());
                break;
            //切换编码方式
            case R.id.btn_video_push_swEnc:
                if (mEncoderBtn.getText().toString().contentEquals("软编码")) {
                    mPublisher.switchToSoftEncoder();
                    mEncoderBtn.setText("硬编码");
                } else if (mEncoderBtn.getText().toString().contentEquals("硬编码")) {
                    mPublisher.switchToHardEncoder();
                    mEncoderBtn.setText("软编码");
                }
                break;
            //开关弹幕
            case R.id.btn_video_push_barrage:
                if (mStartBarrageBtn.getText().toString().equals("开启弹幕")) {
                    mPresenter.isStartBarrage(true);
                    mStartBarrageBtn.setText("关闭弹幕");
                } else if (mStartBarrageBtn.getText().toString().equals("关闭弹幕")) {
                    mPresenter.isStartBarrage(false);
                    mStartBarrageBtn.setText("开启弹幕");
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPublisher.resumeRecord();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPublisher.pauseRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublisher.stopPublish();
        mPublisher.stopRecord();
        mPublisher.stopCamera();
        mPublisher.stopAudio();
        EventBusUtils.unregister(this);

    }

    @Override
    public void startBarrage(boolean flag) {
        if(flag){
            mFrameLayout.setVisibility(View.VISIBLE);
            Toast.makeText(this,"弹幕已开启",Toast.LENGTH_LONG).show();
        }else {
            mFrameLayout.setVisibility(View.GONE);
            Toast.makeText(this,"弹幕已关闭",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    /******************************************** My methods is End *********************************************/

    /**
     * 下面的方法是与RTMP推流有关的方法、回调
     * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPublisher.stopEncode();
        mPublisher.stopRecord();
        mPublisher.setScreenOrientation(newConfig.orientation);
        if (mPublishBtn.getText().toString().contentEquals("停止")) {
            mPublisher.startEncode();
        }
        mPublisher.startCamera();
    }

    @Override
    public void onNetworkWeak() {
        Toast.makeText(getApplicationContext(), "网络型号弱", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkResume() {

    }

    @Override
    public void onEncodeIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    private void handleException(Exception e) {
        try {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            mPublisher.stopPublish();
            mPublisher.stopRecord();
            mPublishBtn.setText("开始");
        } catch (Exception e1) {
            //
        }
    }

    @Override
    public void onRtmpConnecting(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpConnected(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoStreaming() {

    }

    @Override
    public void onRtmpAudioStreaming() {

    }

    @Override
    public void onRtmpStopped() {
        Toast.makeText(getApplicationContext(), "已停止", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpDisconnected() {
        Toast.makeText(getApplicationContext(), "未连接服务器", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRtmpVideoFpsChanged(double fps) {

    }

    @Override
    public void onRtmpVideoBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpAudioBitrateChanged(double bitrate) {

    }

    @Override
    public void onRtmpSocketException(SocketException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

    @Override
    public void onRtmpIllegalStateException(IllegalStateException e) {
        handleException(e);
    }

    @Override
    public void onRecordPause() {
        Toast.makeText(getApplicationContext(), "Record paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordResume() {
        Toast.makeText(getApplicationContext(), "Record resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordStarted(String msg) {
        Toast.makeText(getApplicationContext(), "Recording file: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordFinished(String msg) {
        Toast.makeText(getApplicationContext(), "MP4 file saved: " + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordIOException(IOException e) {
        handleException(e);
    }

    @Override
    public void onRecordIllegalArgumentException(IllegalArgumentException e) {
        handleException(e);
    }

}
