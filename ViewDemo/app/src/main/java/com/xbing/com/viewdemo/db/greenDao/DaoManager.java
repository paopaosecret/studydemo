package com.xbing.com.viewdemo.db.greenDao;

/**
 * Created by zhaobing04 on 2018/3/20.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xbing.com.viewdemo.gen.DaoMaster;
import com.xbing.com.viewdemo.gen.DaoSession;

/**
 * Created by 58 on 2016/12/1.
 */

public class DaoManager extends DaoMaster.OpenHelper {

    private static DaoManager mDbManager;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static Context mContext;


    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    /**
     * 储存通话信息的数据库
     */
    public static final String DATABASE_CALL = "viewdemo.db";

    private DaoManager(Context context, String name) {
        super(context, name);
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static DaoManager getInstance() {
        if (mDbManager == null) {
            synchronized (DaoManager.class) {
                if (mDbManager == null) {
                    mDbManager = new DaoManager(mContext, DATABASE_CALL);
                    mDaoMaster = new DaoMaster(mDbManager.getWritableDatabase());
                    mDaoSession = mDaoMaster.newSession();
                }
            }
        }
        return mDbManager;
    }

    /**
     * 覆盖原有的数据库版本更新方法，兼容老数据，每次添加表都要修改该方法
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
    }
}
