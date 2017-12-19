package test.com.livetest.biz;

import java.util.ArrayList;

/**
 * Created by dhht on 2017/12/7.
 */

public class RequestBiziml implements RequestBiz{
    @Override
    public void requestForData(final OnRequestListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    ArrayList<String> data = new ArrayList<>();
                    for(int i=0;i<8;i++){
                        data.add("item-"+i);
                    }
                    if(listener != null){
                        listener.onSuccess(data);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
