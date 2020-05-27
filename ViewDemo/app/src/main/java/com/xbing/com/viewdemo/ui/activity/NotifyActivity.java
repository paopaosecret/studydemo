package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.NotifyUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobing on 2016/8/11.
 */
public class NotifyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity);

        findViewById(R.id.btn_showNOtifyOld).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotifyOld(getApplicationContext());
            }
        });

        findViewById(R.id.btn_showNOtify11).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotify11(getApplicationContext());

            }
        });

        findViewById(R.id.btn_showNOtify16).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotify16(getApplicationContext());

            }
        });

        findViewById(R.id.btn_showNOtifyDefine).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotifyDefine(getApplicationContext());

            }
        });

        findViewById(R.id.btn_cancelNotifyById).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.cancelNotify(getApplicationContext(), 1);

            }
        });

        findViewById(R.id.btn_cancelNotifyAll).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.cancelAllNotify(getApplicationContext());

            }
        });

        findViewById(R.id.btn_getNotifyAll).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> list=new ArrayList<String>();
                list.add("a");
                list.add("b");
                list.add("c");
                list.add("d");
                //传统方式进行外部迭代
                for (String s : list) {
                    System.out.print(s);
                }

                //java8内部迭代，用lambda处理
//                list.forEach(s ->System.out.print(s));
//                NotifyUtils.getAllNotify(getApplicationContext());
//                String ret = stringexecShellStr("dumpsys notification");
//                Log.e("notification", "notification:" + ret);
//                String[] commands = {"dumpsys notification"};
//                Process process = null;
//                DataOutputStream dataOutputStream = null;
//
//                try {
//                    process = Runtime.getRuntime().exec("su");
//                    dataOutputStream = new DataOutputStream(process.getOutputStream());
//                    int length = commands.length;
//                    for (int i = 0; i < length; i++) {
//                        Log.e("notification", "commands[" + i + "]:" + commands[i]);
//                        dataOutputStream.writeBytes(commands[i] + "\n");
//                    }
//                    dataOutputStream.writeBytes("exit\n");
//                    dataOutputStream.flush();
//
//                    process.waitFor();
//
//                    BufferedReader reader = null;
//                    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//                    String line = "";
//                    List<String> lineList = new ArrayList<String>();
//                    final StringBuilder log = new StringBuilder();
//                    String separator = System.getProperty("line.separator");
//                    Pattern pattern = Pattern.compile("pkg=[^\\s]+");
//                    while ((line = reader.readLine()) != null) {
//                        if(line != null && line.trim().startsWith("NotificationRecord")){
//                            Matcher matcher = pattern.matcher(line);
//                            if(matcher.find()){
//                                lineList.add(matcher.group());
//                            }else{
//                                Log.e("notification", "what's this?!");
//                            }
//                        }
//
//                        log.append(line);
//                        log.append(separator);
//                    }
//                    Log.v("notification", "log:" + log.toString());
//
//                    int size = lineList.size();
//                    for (int i = 0; i < size; i++) {
//                        Log.i("notification", "app:" + lineList.get(i));
//                    }
//                } catch (Exception e) {
//                    Log.e("notification", "copy fail", e);
//                } finally {
//                    try {
//                        if (dataOutputStream != null) {
//                            dataOutputStream.close();
//                        }
//                        process.destroy();
//                    } catch (Exception e) {
//                    }
//                }
//                Log.v("notification", "finish");
            }
        });
    }


    /** 执行 shell 命令之后返回 String 类型的结果 */
    public String stringexecShellStr(String cmd)
    {
        String[] cmdStrings = new String[] {"sh", "-c", cmd};
        String retString = "";

        try
        {
            Process process = Runtime.getRuntime().exec(cmdStrings);
            BufferedReader stdout =
                    new BufferedReader(new InputStreamReader(
                            process.getInputStream()), 7777);
            BufferedReader stderr =
                    new BufferedReader(new InputStreamReader(
                            process.getErrorStream()), 7777);

            String line = null;

            while ((null != (line = stdout.readLine()))
                    || (null != (line = stderr.readLine())))
            {
                if (!TextUtils.isEmpty(line))
                {
                    retString += line + "\n";
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return retString;
    }
}

