package test.com.livetest.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.clearliang.leancloud.R;
import com.example.clearliang.leancloud.base.BaseActivity;
import com.example.clearliang.leancloud.interfaces.IMTitleViewInterface;
import com.example.clearliang.leancloud.presenter.IMTitlePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressLint("Registered")
public class IMTitleActivity extends BaseActivity<IMTitleViewInterface,IMTitlePresenter> implements IMTitleViewInterface {
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_title);

        initView();
        initAdapter();
        initEvent();
    }

    @Override
    protected IMTitlePresenter createPresenter() {
        return new IMTitlePresenter(this);
    }


    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = getIntent().getExtras();
                TextView textView = view.findViewById(R.id.tv_im_name);
                String myName = bundle.getString("myName");
                String toName = textView.getText().toString();

                final Intent i = new Intent(IMTitleActivity.this,IMActivity.class);
                i.putExtra("toName",toName);
                i.putExtra("myName",myName);
                startActivity(i);
            }
        });
    }

    private void initAdapter() {
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
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,R.layout.item, from, to) ;
        //3.添加到listView里面
        mListView.setAdapter(simpleAdapter) ;
    }

    private void initView() {
        mListView = findViewById(R.id.lv_im_title);
    }


}
