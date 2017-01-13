package com.xbing.com.viewdemo.db;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zhaobing on 2016/8/23.
 */
public class RealmDBHelper {

    public static Realm getmRealmDBHelper(Context context) {
        if(mRealmDB == null){
            mRealmDB = Realm.getInstance(
                    new RealmConfiguration.Builder(context)
                            .name("myRealm.realm")
                            .build()
            );
        }
        return mRealmDB;

    }
    private static Realm mRealmDB;

}
