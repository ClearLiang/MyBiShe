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

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.com.MyBiShe.GlideImageLoader;
import test.com.MyBiShe.activity.PlayerActivity;
import test.com.livetest.R;
import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.entity.RoomInfo;
import test.com.MyBiShe.interfaces.RoomListViewInterface;
import test.com.MyBiShe.presenter.RoomListPresenter;

/**
 * Created by ClearLiang on 2017/12/20.
 */

public class RoomListFragment extends BaseFragment<RoomListViewInterface,RoomListPresenter> implements RoomListViewInterface {
    private Banner mBanner;
    List<String> mImgs = new ArrayList<>();
    List<String> mTitles = new ArrayList<>();
    private ListView mListView;
    private int[] headList = {R.drawable.head_1,R.drawable.head_2,R.drawable.head_3,R.drawable.head_4,R.drawable.head_5,R.drawable.head_6,R.drawable.head_7,R.drawable.head_8};
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
        initBanner(view);
        return view;
    }

    private void initBanner(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        mImgs.clear();
        mImgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514868961854&di=c0a3b8f481499dc8f997dba9662af1ad&imgtype=0&src=http%3A%2F%2Fimg3.3lian.com%2F2013%2Fc1%2F84%2Fd%2F36.jpg");
        mImgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514868988407&di=347aa071bcb79a4bb1432282a2db5461&imgtype=jpg&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20170815%2F4378832cf54642a7870f546a89a7d164.jpeg");
        mImgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514868961854&di=bcd82e981fe53b19b3468e1787c70f48&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F130331%2F240460-13033106243430.jpg");
        mImgs.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514868961854&di=965ec1847e095b138bdb97e979def553&imgtype=0&src=http%3A%2F%2Fpic32.photophoto.cn%2F20140711%2F0011024086081224_b.jpg");
        mTitles.add("图片1");mTitles.add("图片2");mTitles.add("图片3");mTitles.add("图片4");
        mBanner.setImageLoader(new GlideImageLoader()).start();
        mBanner.setBannerTitles(mTitles);
        mBanner.update(mImgs);
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
            map.put("iv_roomlist_head", headList[i]) ;
            map.put("tv_roomlist_name", String.valueOf("tom"+i)) ;
            list.add(map) ;
        }
        Map<String,Object> map1 = new HashMap<String,Object>() ;
        map1.put("iv_roomlist_head", R.drawable.head_6) ;
        map1.put("tv_roomlist_name", "Jerry") ;
        list.add(map1) ;
        Map<String,Object> map2 = new HashMap<String,Object>() ;
        map2.put("iv_roomlist_head", R.drawable.head_7) ;
        map2.put("tv_roomlist_name", "1") ;
        list.add(map2);
        Map<String,Object> map3 = new HashMap<String,Object>() ;
        map3.put("iv_roomlist_head", R.drawable.head_8) ;
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
