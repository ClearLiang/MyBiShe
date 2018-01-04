package test.com.MyBiShe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import test.com.MyBiShe.activity.ManagerUserActivity;
import test.com.MyBiShe.base.BaseFragment;
import test.com.MyBiShe.interfaces.ManagerViewInterface;
import test.com.MyBiShe.presenter.ManagerPresenter;
import test.com.livetest.R;

/**
 * Created by ClearLiang on 2017/12/29.
 */

public class ManagerFragment extends BaseFragment<ManagerViewInterface,ManagerPresenter> implements ManagerViewInterface,View.OnClickListener{
    private Button btnManagerUser,btnManagerAnchor,btnManagerApplication;

    @Override
    protected ManagerPresenter createPresenter() {
        return new ManagerPresenter(this);
    }

    public static ManagerFragment newInstance() {

        Bundle args = new Bundle();

        ManagerFragment fragment = new ManagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager,container,false);

        initButton(view);
        return view;
    }

    private void initButton(View view) {
        btnManagerUser = (Button) view.findViewById(R.id.btn_manager_user);
        btnManagerAnchor = (Button) view.findViewById(R.id.btn_manager_anchor);
        btnManagerApplication = (Button) view.findViewById(R.id.btn_manager_application);

        btnManagerUser.setOnClickListener(this);
        btnManagerAnchor.setOnClickListener(this);
        btnManagerApplication.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_manager_user:
                Intent intent = new Intent();
                intent.setClass(getContext(), ManagerUserActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_manager_anchor:
                Intent intent1 = new Intent();
                intent1.setClass(getContext(), ManagerUserActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_manager_application:
                Intent intent2 = new Intent();
                intent2.setClass(getContext(), ManagerUserActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
