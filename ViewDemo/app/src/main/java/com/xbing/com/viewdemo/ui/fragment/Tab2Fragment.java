package com.xbing.com.viewdemo.ui.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbing.com.viewdemo.AIDLService;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.aidl.AIDLServiceImp;

/**
 * Created by zhaobing on 2016/10/25.
 */

public class Tab2Fragment extends Fragment {

    private final static String TAG = Tab2Fragment.class.getSimpleName();

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
        getActivity().unbindService(connection);
        super.onStop();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,TAG +"->onCreateView");
        View rootView = inflater.inflate(R.layout.tab2_fragment,container,false);
        Intent intent = new Intent(getActivity(),AIDLServiceImp.class);
        getActivity().bindService(intent,connection,Context.BIND_AUTO_CREATE);
        rootView.findViewById(R.id.btn_aidl_method).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mAidlService != null){
                    try {
                        mAidlService.printString();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return rootView;
    }

    IBinder mBinder = null;
    AIDLService mAidlService;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"onServiceConnected:" + name.toString());
            mBinder = service;
            mAidlService = AIDLService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"onServiceDisconnected:" + name.toString());
        }
    };
}
