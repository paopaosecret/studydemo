package com.xbing.com.viewdemo.db.greenDao.operate;


import android.text.TextUtils;

import com.xbing.com.viewdemo.db.greenDao.Bean.UserBean;
import com.xbing.com.viewdemo.db.greenDao.DaoManager;
import com.xbing.com.viewdemo.gen.UserBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobing04 on 2018/3/20.
 */

public class UserDaoOperate{
    public static void insertOrReplace(UserBean contactRemark) {
        DaoManager.getInstance().getDaoSession().getUserBeanDao().insertOrReplace(contactRemark);
    }

    public static long queryLastSortId(String userId) {
        WhereCondition conditionUserId = UserBeanDao.Properties.Uid.eq(userId);
        QueryBuilder<UserBean> builder = DaoManager.getInstance().getDaoSession().getUserBeanDao().queryBuilder();
        ArrayList<UserBean> contactList = (ArrayList<UserBean>) builder.where(conditionUserId).orderDesc(UserBeanDao.Properties.Uid).limit(1).list();
        if (contactList != null && contactList.size() > 0) {
            return contactList.get(0).getUid();
        } else {
            return -1;
        }
    }


    public static void deleteByUserId(String userId) {
        WhereCondition conditionUserId = UserBeanDao.Properties.Uid.eq(userId);
        DaoManager.getInstance().getDaoSession().getUserBeanDao().queryBuilder().where(conditionUserId).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public static void insertUserList(List<UserBean> userBeanList){
        DaoManager.getInstance().getDaoSession().getUserBeanDao().insertInTx(userBeanList);
    }
}
