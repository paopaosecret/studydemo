package com.xbing.com.viewdemo.db.dao;

import android.content.Context;

import com.xbing.com.viewdemo.db.RealmDBHelper;
import com.xbing.com.viewdemo.db.model.Student;

import java.util.List;

import io.realm.Realm;

/**
 * Created by zhaobing on 2016/8/23.
 */
public class StudentDao {

    private Realm mRealmHelper;

    public StudentDao(Context context){
        mRealmHelper = RealmDBHelper.getmRealmDBHelper(context);
    }

    public void beginTransaction(){
        mRealmHelper.beginTransaction();
    }

    public void addStudent(Student student){
        mRealmHelper.copyToRealmOrUpdate(student);
    }

    public void commitTransaction(){
        mRealmHelper.commitTransaction();
    }

    public void deleteRealmObject(long id){
        List<Student> student = mRealmHelper.where(Student.class).findAll();
        mRealmHelper.beginTransaction();
        student.clear();
        mRealmHelper.commitTransaction();

    }
}
