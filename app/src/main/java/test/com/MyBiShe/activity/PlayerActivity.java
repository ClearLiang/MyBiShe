package test.com.MyBiShe.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.VideoView;
import test.com.livetest.R;
import test.com.MyBiShe.base.BaseActivity;
import test.com.MyBiShe.fragment.IMFragment;
import test.com.MyBiShe.interfaces.PlayViewInterface;
import test.com.MyBiShe.presenter.PlayPresenter;


public class PlayerActivity extends BaseActivity<PlayViewInterface,PlayPresenter> implements View.OnClickListener,PlayViewInterface {
    private String path = "";
    private VideoView mVideoView;
    private EditText mEditText;
    private Button mStartBtn,mStopBtn,mBarrageBtn;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        initView();
        initEvent();
        initBarrage();
    }

    @Override
    protected PlayPresenter createPresenter() {
        return new PlayPresenter(this);
    }

    private void initBarrage() {
        IMFragment fragment = new IMFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_player,fragment);
        ft.commit();
    }

    private void initEvent() {
        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mBarrageBtn.setOnClickListener(this);
    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.et_play_url);
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mStartBtn = (Button) findViewById(R.id.btn_play_start);
        mStopBtn = (Button) findViewById(R.id.btn_play_stop);
        mBarrageBtn = (Button) findViewById(R.id.btn_play_barrage);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_player);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_start:
                path = mEditText.getText().toString();
                if (!TextUtils.isEmpty(path)) {
                    mVideoView.setVideoPath(path);
                }
                break;
            case R.id.btn_play_stop:
                mVideoView.stopPlayback();
                break;
            case R.id.btn_play_barrage:
                if(mBarrageBtn.getText().toString().equals("开启弹幕")){
                    mFrameLayout.setVisibility(View.VISIBLE);
                    mBarrageBtn.setText("关闭弹幕");
                }else if(mBarrageBtn.getText().toString().equals("关闭弹幕")){
                    mFrameLayout.setVisibility(View.GONE);
                    mBarrageBtn.setText("开启弹幕");
                }
                break;
        }
    }


}
