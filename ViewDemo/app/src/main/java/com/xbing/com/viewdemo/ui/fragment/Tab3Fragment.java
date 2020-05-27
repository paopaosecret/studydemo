package com.xbing.com.viewdemo.ui.fragment;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,TAG +"->onCreateView");
        View rootView = inflater.inflate(R.layout.tab3_fragment,container,false);

        Button btn = (Button) rootView.findViewById(R.id.btn_call);
        textView = (TextView) rootView.findViewById(R.id.tv_result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(getMissCallStr());
            }
        });

        return rootView;
    }

    public String getMissCallStr(){
//        final Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, new String[] {
//                        CallLog.Calls.NUMBER, CallLog.Calls.CACHED_NAME,
//                        CallLog.Calls.TYPE, CallLog.Calls.DATE }, null, null,
//                CallLog.Calls.DEFAULT_SORT_ORDER);
//        //   final Cursor cursor = cr.query(数据库表名，取得的数据数组（里边包含字段名称），条件，参数组，排序等信息） 这就相当于一条SQL语句//取得所有通话信息  这里边稍微有点复杂
//
//        for (int i = 0; i < cursor.getCount(); i++) {
//            cursor.moveToPosition(i);
//            String str = cursor.getString(0);
//            int type = cursor.getInt(2);
//            if (type == 3) {
//                String tel = str;
//                break;
//            }
//        } //取得值
        String str = "no count";
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE)) {
            //has permission, do operation directly
            ContentResolver cr = getContext().getContentResolver();
            final Cursor cursor = cr.query(CallLog.Calls.CONTENT_URI, new String[] {
                            CallLog.Calls.NUMBER }, "type=3", null,
                    "date desc limit 1");
            /**
             * 呼叫记录有三种类型：
             来电：CallLog.Calls.INCOMING_TYPE （常量值：1）
             已拨：CallLog.Calls.OUTGOING_TYPE（常量值：2）
             未接：CallLog.Calls.MISSED_TYPE（常量值：3）
             其实还有一种类型-拒接 系统未给出常量。但经测试后得出结论为4
             */

            //这个就是取得最近的一次未接来电的电话号码。

            if(cursor.moveToFirst()){
                str = cursor.getString(0);
            }else{
                str = "miss call";
            }

        }else{
            str = "fdsa";
        }
        return str;
    }
}
