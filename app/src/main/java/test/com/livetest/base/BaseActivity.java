package test.com.livetest.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import test.com.livetest.tools.AppManager;
import test.com.livetest.tools.EventBusUtils;
import test.com.livetest.tools.LeanCloudManager;


public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {
    protected Bundle mBundle = new Bundle();
    protected T mPresenter;
    protected static final String TAG = "信息：";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LeanCloudManager.getInstance().initIM(getApplicationContext(),"zNuggiKPXeQDfostTM7O8i7R-gzGzoHsz","LIrsg2Fic3YAMgCMd32BxPH6");
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isRegisterEventBus()){
            EventBusUtils.register(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.datachView();
        AppManager.getAppManager().finishActivity(this);
    }

    protected abstract T createPresenter();

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    /**
     * Rx点击事件防抖动
     *
     * @param view
     * @param action1
     * */
    protected void setClick(View view, Action1<Void> action1){
        RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).subscribe(action1);
    }
}
