package test.com.livetest.biz;

import java.util.List;

/**
 * Created by dhht on 2017/12/7.
 */

public interface OnRequestListener {
    void onSuccess(List<String> data);
    void onFailed();
}
