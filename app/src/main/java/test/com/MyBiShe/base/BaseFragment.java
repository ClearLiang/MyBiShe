package test.com.MyBiShe.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import test.com.MyBiShe.tools.EventBusUtils;


public abstract class BaseFragment<V,T extends BasePresenter<V>> extends Fragment {
    protected Bundle mBundle = new Bundle();
    protected T mPresenter;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(isRegisterEventBus()){
            EventBusUtils.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBusUtils.unregister(this);
    }

    /**
     * Rx事件点击防抖动
     *
     * @param view
     * @param action1
     * */
    protected void setClick(View view, Action1<Void> action1){
        RxView.clicks(view)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(action1);
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
}
