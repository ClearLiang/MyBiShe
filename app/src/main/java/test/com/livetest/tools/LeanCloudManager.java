package test.com.livetest.tools;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationsQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationMemberCountCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class LeanCloudManager {
    private static final int ConversationType_OneOne = 0; // 两个人之间的单聊
    private static final int ConversationType_Group = 1;  // 多人之间的群聊
    private static LeanCloudManager sLeanCloudManager;
    private String mMyName;
    private String mToName;
    private Context mContext;
    private AVIMClient mClient;
    private static final String TAG = "信息：";

    public LeanCloudManager() {
    }

    public static synchronized LeanCloudManager getInstance() {
        if (null == sLeanCloudManager) {
            sLeanCloudManager = new LeanCloudManager();
        }
        return sLeanCloudManager;
    }

    /**
     * 初始化 ，此函数要在 Application 的 onCreate 中调用
     * @param context   上下文
     * @param APP_ID    LeanCloud中项目的Id
     * @param APP_KEY   LeanCloud中项目的Key
     */
    public static void initIM(Context context, String APP_ID, String APP_KEY){
        if (TextUtils.isEmpty(APP_ID)) {
            throw new IllegalArgumentException("appId can not be empty!");
        }
        if (TextUtils.isEmpty(APP_KEY)) {
            throw new IllegalArgumentException("appKey can not be empty!");
        }
        AVOSCloud.initialize(context.getApplicationContext(),APP_ID,APP_KEY);
        //todo 在正式运行时，去掉logo日志
        AVOSCloud.setDebugLogEnabled(false);
    }
    /**
     * 创建对话
     * */
    public void initClient(String myName){
        mMyName = myName;
        AVIMClient avimClient = AVIMClient.getInstance(myName);
        mClient = avimClient;
        mClient.open(new AVIMClientCallback(){
            @Override
            public void done(AVIMClient client,AVIMException e){
                if(e==null){
                    //登录成功后的逻辑
                    Log.i(TAG,mMyName+"登陆成功");
                }
            }
        });
    }

    /**
     * 发送信息
     * @param text      发送的内容
     * @param isTransient   是否是聊天室
     * */
    public void sendMessage(String toName, final String text, final boolean isTransient) {
        if(TextUtils.isEmpty(toName) ||toName == null){
            return;
        }
        mToName = toName;
        // 与服务器连接
        mClient.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    // 创建与其他人之间的对话
                    client.createConversation(Arrays.asList(mToName), mMyName+"和"+mToName+"的对话", null, isTransient, true,
                            new AVIMConversationCreatedCallback() {
                                @Override
                                public void done(AVIMConversation conversation, AVIMException e) {
                                    if (e == null) {
                                        AVIMTextMessage msg = new AVIMTextMessage();
                                        msg.setText(text);
                                        // 发送消息
                                        conversation.sendMessage(msg, new AVIMConversationCallback() {
                                            @Override
                                            public void done(AVIMException e) {
                                                if (e == null) {
                                                    Log.d(mMyName+" & "+mToName, "发送成功！");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });

    }

    /**
     * 关闭实时聊天
     * @param callback
     */
    public void close(final AVIMClientCallback callback) {
        mClient.close(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                Log.i(TAG,"关闭成功");
            }
        });
    }

    /**
     * 初始化存储class
     * @param objectName
     * */
    public AVObject save(String objectName){
        AVObject testObject = new AVObject(objectName);//后台创建一个表名为testObject
        return  testObject;
    }
    public void save(AVObject object, String myName, String toName, String content){
        //信息存储
        object.put("myName",myName);//向表中添加一列，列名为myName
        object.put("toName",toName);
        object.put("message",content);
        object.saveInBackground();//在后台进行保存
    }

    /**
     * 创建聊天室
     * */
    public void CreateChatroom(){
        AVIMClient tom = AVIMClient.getInstance(mMyName);
        tom.open(new AVIMClientCallback(){

            @Override
            public void done(AVIMClient client,AVIMException e){
                if(e==null){
                    //登录成功
                    //创建一个 名为 "聊天室1" 的暂态对话
                    client.createConversation(Collections.<String>emptyList(),"聊天室1",null,true,
                            new AVIMConversationCreatedCallback(){
                                @Override
                                public void done(AVIMConversation conv,AVIMException e){
                                    Log.i(TAG,"聊天室创建成功");
                                }
                            });
                }
            }
        });
    }
    /**
     * 查询聊天室在线人数
     * */
    public int QueryWithLimit() {
        final int[] number = {0};
        mClient.open(new AVIMClientCallback() {

            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    //登录成功
                    AVIMConversationsQuery query = mClient.getConversationsQuery();
                    query.setLimit(1);
                    //获取第一个对话
                    query.findInBackground(new AVIMConversationQueryCallback() {
                        @Override
                        public void done(List<AVIMConversation> convs, AVIMException e) {
                            if (e == null) {
                                if (convs != null && !convs.isEmpty()) {
                                    AVIMConversation conv = convs.get(0);
                                    //获取第一个对话的
                                    conv.getMemberCount(new AVIMConversationMemberCountCallback() {

                                        @Override
                                        public void done(Integer count, AVIMException e) {
                                            if (e == null) {
                                                Log.d("Tom & Jerry", "conversation got " + count + " members");
                                                number[0] = count;
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
        return number[0];
    }

    /**
     * 查找聊天室
     * */
    public AVIMConversation QueryChatroom(){
        final AVIMConversation[] conv = new AVIMConversation[1];
        mClient.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    //登录成功
                    //查询 attr.topic 为 "奔跑吧，兄弟" 的暂存聊天室
                    AVIMConversationsQuery query = client.getConversationsQuery();
                    query.whereEqualTo("attr.topic", "聊天室1");
                    query.whereEqualTo("tr", true);
                    //获取第一个对话
                    query.findInBackground(new AVIMConversationQueryCallback() {
                        @Override
                        public void done(List<AVIMConversation> convs, AVIMException e) {
                            if (e == null) {
                                if (convs != null && !convs.isEmpty()) {
                                    conv[0] = convs.get(0);
                                    //获取第一个对话的
                                    conv[0].getMemberCount(new AVIMConversationMemberCountCallback() {
                                        @Override
                                        public void done(Integer count, AVIMException e) {
                                            if (e == null) {
                                                Log.d(TAG,"这个聊天室有"+count+"个人");

                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
        return conv[0];
    }

    /**
     * 获取当前的实时聊天的用户
     * @return
     */
    public String getCurrentUserId() {
        return mMyName;
    }

    /**
     * 获取当前的 AVIMClient 实例
     * @return
     */
    public AVIMClient getClient() {
        if (!TextUtils.isEmpty(mMyName)) {
            return AVIMClient.getInstance(mMyName);
        }
        return null;
    }
}
