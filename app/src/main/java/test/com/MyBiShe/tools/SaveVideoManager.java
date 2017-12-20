package test.com.MyBiShe.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jiangdg.mediacodec4mp4.RecordMp4;
import com.jiangdg.mediacodec4mp4.bean.EncoderParams;
import com.jiangdg.mediacodec4mp4.model.AACEncodeConsumer;
import com.jiangdg.mediacodec4mp4.model.H264EncodeConsumer;
import com.jiangdg.mediacodec4mp4.model.SaveYuvImageTask;
import com.jiangdg.mediacodec4mp4.utils.CameraManager;

import java.io.File;

/**
 * Created by ClearLiang on 2017/12/19.
 */

public class SaveVideoManager {
    private static final String TAG = "SavaVideoManager信息：";

    private RecordMp4 mRecordMp4;
    private Context mContext;

    public SaveVideoManager(Context context) {
        mContext = context;
    }

    /**
     * 开始录制
     * */
    public  void StartSaveVideo(){
        // 1. 初始化引擎
        mRecordMp4 = RecordMp4.getRecordMp4Instance();
        mRecordMp4.init(mContext.getApplicationContext());
        // 2. 配置参数
        mRecordMp4.setEncodeParams(getEncodeParams());
        // 3. 开始录制
        mRecordMp4.startRecord();
    }

    /**
     * 结束录制
     * */
    public void StopSaveVideo(){
        // 4. 停止录制
        mRecordMp4.stopRecord();
    }

    /**
     * 设置存储视频的参数
     * */
    private EncoderParams getEncodeParams() {
        EncoderParams mParams = new EncoderParams();
        mParams.setVideoPath(RecordMp4.ROOT_PATH+ File.separator + MyDate.getYMDString() + ".mp4");    // 视频文件路径
        mParams.setFrameWidth(CameraManager.PREVIEW_WIDTH);             // 分辨率
        mParams.setFrameHeight(CameraManager.PREVIEW_HEIGHT);
        mParams.setBitRateQuality(H264EncodeConsumer.Quality.MIDDLE);   // 视频编码码率
        mParams.setFrameRateDegree(H264EncodeConsumer.FrameRate._30fps);// 视频编码帧率
//        mParams.setFrontCamera((mRecMp4!=null && mRecMp4.isFrontCamera()) ? true:false);       // 摄像头方向
//        mParams.setPhoneHorizontal(false);  // 是否为横屏拍摄
        mParams.setAudioBitrate(AACEncodeConsumer.DEFAULT_BIT_RATE);        // 音频比特率
        mParams.setAudioSampleRate(AACEncodeConsumer.DEFAULT_SAMPLE_RATE);  // 音频采样率
        mParams.setAudioChannelConfig(AACEncodeConsumer.CHANNEL_IN_MONO);// 单声道
        mParams.setAudioChannelCount(AACEncodeConsumer.CHANNEL_COUNT_MONO);       // 单声道通道数量
        mParams.setAudioFormat(AACEncodeConsumer.ENCODING_PCM_16BIT);       // 采样精度为16位
        mParams.setAudioSouce(AACEncodeConsumer.SOURCE_MIC);                // 音频源为MIC
        return mParams;
    }

    /**
     * 对焦
     * */
    public void enableFocus(){
        if(mRecordMp4 != null){
            mRecordMp4.enableFocus(new CameraManager.OnCameraFocusResult() {
                @Override
                public void onFocusResult(boolean result) {
                    if(result){
                        Toast.makeText(mContext.getApplicationContext(),"对焦成功",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    /**
     * 拍照
     * */
    public void takePicture(){
        String picPath = RecordMp4.ROOT_PATH+ File.separator+MyDate.getYMDString()+".jpg";
        if(mRecordMp4 != null)
            mRecordMp4.capturePicture(picPath, new SaveYuvImageTask.OnSaveYuvResultListener() {
                @Override
                public void onSaveResult(boolean result, String savePath) {
                    Log.i(TAG,"抓拍结果："+result+"保存路径："+savePath);
                }
            });
    }


}
