package test.com.MyBiShe.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import test.com.MyBiShe.fragment.IMTitleFragment;

/**
 * Created by ClearLiang on 2017/12/20.
 */

@SuppressLint("ValidFragment")
public class ReplaceFragment extends Fragment{
    private Context mContext;
    private Class mClass;
    private Bundle mBundle;

    public ReplaceFragment(Context context, Class aClass, Bundle bundle) {
        mContext = context;
        mClass = aClass;
        mBundle = bundle;
    }

    public ReplaceFragment(Context context, Class<IMTitleFragment> imTitleFragmentClass) {
        Intent intent = new Intent(mContext,mClass);
        startActivity(intent);
    }


    public void replaceFragment(){
        Intent intent = new Intent(mContext,mClass);
        intent.putExtras(mBundle);
        startActivity(intent);
    }
}
