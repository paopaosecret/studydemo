package com.xbing.com.viewdemo.ui.activity.twolabel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandActivity extends Activity {


    private TextView tvShow;
    private EditText etCmd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        etCmd = findViewById(R.id.et_cmd);
        tvShow = findViewById(R.id.tv_show);
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cmd = etCmd.getText().toString().trim();
                exeCommand(cmd);
            }
        });
    }

    public void exeCommand(String cmd){
        try {

            Process process = new ProcessBuilder(cmd).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String s;
            StringBuffer sb = new StringBuffer("");
            while((s = reader.readLine()) != null){
                sb.append(s).append("\n");
            }
            tvShow.setText(sb.toString());
            process.destroy();
            if(reader != null){
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
