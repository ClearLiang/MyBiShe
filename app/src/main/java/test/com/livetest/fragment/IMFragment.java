package test.com.livetest.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.AVIMMessageManager;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.util.ArrayList;
import java.util.List;

import test.com.livetest.R;
import test.com.livetest.adapter.MyAdapter;
import test.com.livetest.base.BaseFragment;
import test.com.livetest.entity.MyMessage;
import test.com.livetest.interfaces.IMViewInterface;
import test.com.livetest.presenter.IMPresenter;

import test.com.livetest.tools.MyDate;

/**
 * Created by ClearLiang on 2017/12/19.
 */

public class IMFragment extends BaseFragment<IMViewInterface,IMPresenter> implements IMViewInterface{
    protected static AVObject testObject;

    private Button mBtnIMSubmit;
    private EditText mEtIMInput;
    private RecyclerView mRecyclerView;
    private TextView mTvImHead;
    private RelativeLayout mRelativeLayout;

    private static List<MyMessage> mMessageList = new ArrayList<>();
    private static MyAdapter myAdapter;
    private LinearLayoutManager mLayoutManager;
    private String myName="",toName="";

    IMPresenter mPresenter;

    @Override
    protected IMPresenter createPresenter() {
        return new IMPresenter(this);
    }

    public class CustomMessageHandler extends AVIMMessageHandler {
        //接收到消息后的处理逻辑
        @Override
        public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client){
            if(message instanceof AVIMTextMessage){
                MyMessage remsg = new MyMessage();
                remsg.setName(toName);
                remsg.setContent(((AVIMTextMessage) message).getText());
                remsg.setType(MyMessage.TYPE_RECEIVE);
                remsg.setTime(MyDate.getDate());
                remsg.setIcon(String.valueOf(R.drawable.icon_warning));
                mMessageList.add(remsg);
                myAdapter.notifyItemInserted(mMessageList.size());//当有新消息时，刷新RecyclerView中的显示
                mRecyclerView.scrollToPosition(mMessageList.size() - 1);//将RecyclerView定位到最后一行
            }
        }

        public void onMessageReceipt(AVIMMessage message,AVIMConversation conversation,AVIMClient client){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_im,container,false);
        mPresenter = new IMPresenter(this);
        testObject = mPresenter.initSave("LeanCloud");

        initView(view);
        initEvent(view);
        initAdapter(view);
        return view;
    }

    private void initAdapter(View view) {
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //给视图设置大小变化的监听，用于点击输入框时，文本定位
        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //当点击输入框弹出输入法时，布局大小改变，将RecyclerView定位到最后一行
                mRecyclerView.scrollToPosition(mMessageList.size()-1);
            }
        });
        //创建并设置Adapter
        myAdapter = new MyAdapter(mMessageList);
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.notifyItemInserted(mMessageList.size());//当有新消息时，刷新RecyclerView中的显示
        mRecyclerView.scrollToPosition(mMessageList.size()-1);//将RecyclerView定位到最后一行

    }


    private void initEvent(final View view) {
        mBtnIMSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEtIMInput.getText().toString();
                //信息存储
                mPresenter.saveMessage(testObject,myName,toName,text);
                //界面显示
                if (!isEmptyInput(view)) {
                    MyMessage msg = new MyMessage();
                    msg.setName(myName);
                    msg.setContent(text);
                    msg.setTime(MyDate.getDate());
                    msg.setType(MyMessage.TYPE_SEND);
                    msg.setIcon(String.valueOf(R.drawable.icon_warning));
                    mMessageList.add(msg);
                    myAdapter.notifyItemInserted(mMessageList.size());//当有新消息时，刷新RecyclerView中的显示
                    mRecyclerView.scrollToPosition(mMessageList.size() - 1);//将RecyclerView定位到最后一行
                    mEtIMInput.setText("");

                    //发送到服务器
                    mPresenter.sendMessage(toName,text,false);
                }
            }
        });
    }

    private void initView(View view) {
        mBtnIMSubmit = (Button) view.findViewById(R.id.btn_im_submit);
        mEtIMInput = (EditText) view.findViewById(R.id.et_im_input);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_im);
        mTvImHead = (TextView) view.findViewById(R.id.tv_im_head);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl_im);

        Bundle bundle = getArguments();
        if(bundle != null){
            myName = bundle.getString("myName");
            toName = bundle.getString("toName");
        }

        mTvImHead.setText(toName);

    }

    @Override
    public boolean isEmptyInput(View view) {
        if(TextUtils.isEmpty(mEtIMInput.getText().toString())){
            Toast.makeText(view.getContext(),"输入为空", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());
    }
}
