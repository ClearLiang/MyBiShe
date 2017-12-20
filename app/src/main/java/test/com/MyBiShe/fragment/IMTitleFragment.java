package test.com.MyBiShe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.com.livetest.R;
import test.com.MyBiShe.activity.PlayerActivity;
import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.interfaces.IMTitleViewInterface;
import test.com.MyBiShe.presenter.IMTitlePresenter;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class IMTitleFragment extends BaseFragment<IMTitleViewInterface,IMTitlePresenter> implements IMTitleViewInterface {
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_im_title,container,false);

        initView(view);
        initAdapter(view);
        initEvent(view);

        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_im_title);
    }

    private void initEvent(final View rootview) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_im_name);
                String toName = textView.getText().toString();
                mBundle.putString("toName",toName);
                Intent i = new Intent(rootview.getContext(),PlayerActivity.class);
                startActivity(i);
            }
        });
    }

    private void initAdapter(View view) {
        //1.准备数据
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>() ;

        for(int i=0;i<5;i++){
            Map<String,Object> map = new HashMap<String,Object>() ;
            map.put("iv_im_head", R.drawable.icon_warning) ;
            map.put("tv_im_name", "lu"+i) ;
            list.add(map) ;
        }

        Map<String,Object> map = new HashMap<String,Object>() ;
        map.put("iv_im_head", R.drawable.icon_warning) ;
        map.put("tv_im_name", "Jerry") ;
        list.add(map) ;


        //2.准备SimpleAdapter对象
        // 准白
        String[] from = { "iv_im_head", "tv_im_name" };
        // 对应 Item 子样式 里面控件的id
        int[] to = { R.id.iv_im_head, R.id.tv_im_name } ;
        SimpleAdapter simpleAdapter = new SimpleAdapter(view.getContext(), list,R.layout.item, from, to) ;
        //3.添加到listView里面
        mListView.setAdapter(simpleAdapter) ;
    }

    @Override
    protected IMTitlePresenter createPresenter() {
        return new IMTitlePresenter(this);
    }

    public static IMTitleFragment newInstance() {
        return new IMTitleFragment();
    }


}
