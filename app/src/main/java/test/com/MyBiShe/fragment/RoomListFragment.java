package test.com.MyBiShe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import test.com.MyBiShe.activity.PlayerActivity;
import test.com.MyBiShe.entity.User;
import test.com.livetest.R;
import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.entity.RoomInfo;
import test.com.MyBiShe.interfaces.RoomListViewInterface;
import test.com.MyBiShe.presenter.RoomListPresenter;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class RoomListFragment extends BaseFragment<RoomListViewInterface,RoomListPresenter> implements RoomListViewInterface {
    private ListView mListView;
    @Override
    protected RoomListPresenter createPresenter() {
        return new RoomListPresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_roomlist,container,false);

        initView(view);
        initEvent(view);
        initAdapter(view);
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_roomlist);
    }

    private void initEvent(View view) {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.tv_roomlist_name);
                String roomNum = textView.getText().toString();
                // TODO: 2017/12/20 这里根据房间名查询推流地址(未完成)
                RoomInfo.getRoomInfo().setId(i);
                RoomInfo.getRoomInfo().setName(roomNum);
                RoomInfo.getRoomInfo().setPushUrl("");

                Intent intent = new Intent(view.getContext(),PlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initAdapter(View view){
        //1.准备数据
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>() ;
        for(int i=0;i<5;i++){
            Map<String,Object> map = new HashMap<String,Object>() ;
            map.put("iv_roomlist_head", R.drawable.icon_warning) ;
            map.put("tv_roomlist_name", String.valueOf("tom"+i)) ;
            list.add(map) ;
        }
        Map<String,Object> map1 = new HashMap<String,Object>() ;
        map1.put("iv_roomlist_head", R.drawable.icon_warning) ;
        map1.put("tv_roomlist_name", "Jerry") ;
        list.add(map1) ;
        Map<String,Object> map2 = new HashMap<String,Object>() ;
        map2.put("iv_roomlist_head", R.drawable.icon_warning) ;
        map2.put("tv_roomlist_name", "1") ;
        list.add(map2);
        Map<String,Object> map3 = new HashMap<String,Object>() ;
        map3.put("iv_roomlist_head", R.drawable.icon_warning) ;
        map3.put("tv_roomlist_name", "2") ;
        list.add(map3);
        Map<String,Object> map4 = new HashMap<String,Object>() ;
        map4.put("iv_roomlist_head", R.drawable.icon_warning) ;
        map4.put("tv_roomlist_name", "3") ;
        list.add(map4);

        //2.准备SimpleAdapter对象
        // 准白
        String[] from = { "iv_roomlist_head", "tv_roomlist_name" };
        // 对应 Item 子样式 里面控件的id
        int[] to = { R.id.iv_roomlist_head, R.id.tv_roomlist_name } ;
        SimpleAdapter simpleAdapter = new SimpleAdapter(view.getContext(), list,R.layout.item_roomlist, from, to) ;
        //3.添加到listView里面
        mListView.setAdapter(simpleAdapter) ;
    }

    public static Fragment newInstance() {
        return new RoomListFragment();
    }
}
