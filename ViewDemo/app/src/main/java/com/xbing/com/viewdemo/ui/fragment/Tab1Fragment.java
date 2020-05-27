package com.xbing.com.viewdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.CustomDialog;

import rx.functions.Func1;

/**
 * Created by zhaobing on 2016/10/25.
 */

public class Tab1Fragment extends Fragment implements View.OnClickListener{

    private final static String TAG = Tab1Fragment.class.getSimpleName();

    private FragmentManager mFragmentManager;

    /*第一步*/
    @Override
    public void onAttach(Context context) {
        Log.i(TAG,TAG +"->onAttach");
        super.onAttach(context);
    }


    /*第二步*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,TAG +"->onCreate");
        super.onCreate(savedInstanceState);
    }

    /*第三步*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,TAG +"->onCreateView");
        mFragmentManager = getActivity().getSupportFragmentManager();
        View contentView = inflater.inflate(R.layout.tab1_fragment,container,false);
        contentView.findViewById(R.id.btn_showdialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CustomDialog customDialog = new CustomDialog(view.getContext());
                customDialog.showDialog("title", "are you speak english?", "ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        customDialog.dismiss();
                    }
                });
            }
        });

        contentView.findViewById(R.id.btn_showdialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog customDialog = new CustomDialog(v.getContext());
                customDialog.showDialog("title", "are you speak english are you speak english are you speak englishare you speak english are you speak english are you speak english are you speak english are you speak english are you speak english",
                        "ok ok ok ok ok ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customDialog.dismiss();
                            }
                        }, "cancel cancel cancel cancel cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customDialog.dismiss();
                            }
                        });
            }
        });

        contentView.findViewById(R.id.btn_showdialog3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog customDialog = new CustomDialog(v.getContext());
                customDialog.showDialog("are you speak english are you speak english are you speak englishare you speak english are you speak english are you speak english are you speak english are you speak english are you speak english",
                        "ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                customDialog.dismiss();
                            }
                        }, "cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customDialog.dismiss();
                            }
                        });
            }
        });


        contentView.findViewById(R.id.btn_loadfragment).setOnClickListener(this);

        return  contentView;

    }

    /*第四步*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG,TAG +"->onActivityCreated");
    }

    /*第五步*/
    @Override
    public void onStart() {
        Log.i(TAG,TAG +"->onStart");
        super.onStart();
    }

    /*第六步*/
    @Override
    public void onResume() {
        Log.i(TAG,TAG +"->onResume");
        super.onResume();
    }

    /*第7步*/
    @Override
    public void onPause() {
        Log.i(TAG,TAG +"->onPause");
        super.onPause();
    }

    /*第八步*/
    @Override
    public void onStop() {
        Log.i(TAG,TAG +"->onStop");
        super.onStop();
    }

    /*第九步*/
    @Override
    public void onDestroyView() {
        Log.i(TAG,TAG +"->onDestroyView");
        super.onDestroyView();
    }

    /*第十步*/
    @Override
    public void onDetach() {
        Log.i(TAG,TAG +"->onDetach");
        super.onDetach();
    }

    /*第十一步*/
    @Override
    public void onDestroy() {
        Log.i(TAG,TAG +"->onDestroy");
        super.onDestroy();
    }




    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_loadfragment:
                AddFragment fragment = new AddFragment();
                mFragmentManager.beginTransaction()
                        .add(R.id.fl_content,fragment)
                        .hide(this)
                        .show(fragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
