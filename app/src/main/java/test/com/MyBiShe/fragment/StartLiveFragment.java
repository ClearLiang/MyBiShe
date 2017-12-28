package test.com.MyBiShe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

import java.util.Arrays;
import java.util.Collections;

import test.com.MyBiShe.activity.CameraActivity;
import test.com.MyBiShe.activity.SaveVideoActivity;
import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.entity.User;
import test.com.MyBiShe.interfaces.StartLiveInterface;
import test.com.MyBiShe.presenter.StartLivePresenter;
import test.com.livetest.R;

/**
 * Created by ClearLiang on 2017/12/25.
 */

public class StartLiveFragment extends BaseFragment<StartLiveInterface,StartLivePresenter> implements StartLiveInterface ,View.OnClickListener{
    private Button mPushStreamBtn,mSaveBtn;

    public static Fragment newInstance() {
        return new StartLiveFragment();
    }

    @Override
    protected StartLivePresenter createPresenter() {
        return new StartLivePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_choose,container,false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        mPushStreamBtn = (Button) view.findViewById(R.id.btn_push_stream);
        mSaveBtn = (Button) view.findViewById(R.id.btn_save_video);
    }

    private void initEvent() {
        mPushStreamBtn.setOnClickListener(this);
        mSaveBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_push_stream:
                mPushStreamBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), CameraActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.btn_save_video:
                mPushStreamBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), SaveVideoActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }
}
