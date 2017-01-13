package com.xbing.com.viewdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbing.com.viewdemo.R;

/**
 * Created by zhaobing on 2016/10/25.
 */

public class Tab3Fragment extends Fragment {

    private final static String TAG = Tab3Fragment.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        Log.i(TAG,TAG +"->onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,TAG +"->onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG,TAG +"->onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.i(TAG,TAG +"->onDetach");
        super.onDetach();
    }

    @Override
    public void onPause() {
        Log.i(TAG,TAG +"->onPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i(TAG,TAG +"->onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.i(TAG,TAG +"->onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.i(TAG,TAG +"->onStop");
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,TAG +"->onCreateView");
        return inflater.inflate(R.layout.tab3_fragment,container,false);
    }
}
