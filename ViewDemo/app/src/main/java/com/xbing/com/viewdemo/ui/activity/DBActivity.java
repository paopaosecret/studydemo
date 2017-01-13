package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.db.MySqliteDBHelper;
import com.xbing.com.viewdemo.db.dao.StudentDao;
import com.xbing.com.viewdemo.db.model.Student;

/**
 * Created by zhaobing on 2016/8/26.
 */
public class DBActivity extends Activity {

    StudentDao dao;
    private TextView mResult;
    private EditText mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity);
        dao = new StudentDao(DBActivity.this);

        mResult = (TextView)findViewById(R.id.tv_result);
        mCount = (EditText)findViewById(R.id.et_count);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String str = mCount.getText().toString().trim();
                int count = 0;
                if(TextUtils.isEmpty(str)){
                    Toast.makeText(DBActivity.this,"请输入操作数据量",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    count = Integer.parseInt(str);
                }
                long t1 = System.currentTimeMillis();
                Log.e("test","start time:" + t1);
                Student student = new Student();
                dao.beginTransaction();
                for(int i = 1;i<=count;i++){
                    student.setId(1);
                    student.setGender("男");
                    student.setName("张三");
                    student.setMobile("13510266952");
                    dao.addStudent(student);
                }
                dao.commitTransaction();

                long t2 = System.currentTimeMillis();
                Log.e("test","end time:" + t2+",interval:"+(t2-t1));
                mResult.setText("本次操作耗时：" + (t2-t1)+"ms");



            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long t1 = System.currentTimeMillis();
                Log.e("test","start time:" + t1);
                dao.deleteRealmObject(1l);
                long t2 = System.currentTimeMillis();
                Log.e("test","end time:" + t2+",interval:"+(t2-t1));
                mResult.setText("本次操作耗时：" + (t2-t1)+"ms");
            }
        });

        findViewById(R.id.btn_sqlite_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MySqliteDBHelper mySqliteDBHelper = new MySqliteDBHelper(DBActivity.this);
                        SQLiteDatabase db = mySqliteDBHelper.getDatabase();

                        long t1 = System.currentTimeMillis();
                        Log.e("test","start time:" + t1);

                        String str = mCount.getText().toString().trim();
                        int count = 0;
                        if(TextUtils.isEmpty(str)){
                            Toast.makeText(DBActivity.this,"请输入操作数据量",Toast.LENGTH_SHORT).show();
                            return;
                        }else{
                            count = Integer.parseInt(str);
                        }
                        db.beginTransaction();
                        Log.i("log","count:"+count);
                        for(int i=1;i<count;i++){
                            db.execSQL("insert into student values("+i+",'zhangsan','男','12352366987')");
                            if(i%5000==0){
                                db.setTransactionSuccessful();
                                db.endTransaction();
                                db.beginTransaction();
                            }
                        }
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        db.close();
                        long t2 = System.currentTimeMillis();
                        Log.e("test","end time:" + t2+",interval:"+(t2-t1));
                        final long ivterval = t2-t1;
                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        mResult.setText("本次操作耗时：" + ivterval + "ms");
                                    }
                                }

                        );
                    }
                }).start();

            }
        });

        findViewById(R.id.btn_sqlite_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySqliteDBHelper mySqliteDBHelper = new MySqliteDBHelper(DBActivity.this);
                SQLiteDatabase db = mySqliteDBHelper.getDatabase();
                long t1 = System.currentTimeMillis();
                Log.e("test","start time:" + t1);
//                db.beginTransaction();
               db.execSQL("DELETE FROM student");
//                db.endTransaction();

                db.close();
                long t2 = System.currentTimeMillis();
                Log.e("test","end time:" + t2+",interval:"+(t2-t1));
                mResult.setText("本次操作耗时：" + (t2-t1)+"ms");
            }
        });
    }
}
