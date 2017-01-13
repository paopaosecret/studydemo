package com.xbing.com.viewdemo;

import android.app.Application;
import android.util.Log;
import android.util.MutableBoolean;
import android.view.LayoutInflater;

import com.xbing.com.viewdemo.db.MySqliteDBHelper;
import com.xbing.com.viewdemo.db.dao.StudentDao;
import com.xbing.com.viewdemo.db.model.Student;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zhaobing on 2016/8/23.
 */
public class MyApplication extends Application {

    protected static MyApplication instance = new MyApplication();

    private MyActivityManager mActivityManager;

    public static  MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        StudentDao dao = new StudentDao(this);

        Student student = new Student();

        student.setId(1);
        student.setGender("男");
        student.setName("张三");
        student.setMobile("13510266952");
        dao.beginTransaction();
        dao.addStudent(student);
        dao.commitTransaction();

        MySqliteDBHelper dbHelper = new MySqliteDBHelper(this);
        dbHelper.getWritableDatabase();
        mActivityManager = MyActivityManager.getInstance();
        this.registerActivityLifecycleCallbacks(mActivityManager);
    }
}
