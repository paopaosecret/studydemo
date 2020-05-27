package com.xbing.com.viewdemo.db.greenDao.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhaobing04 on 2018/3/20.
 */
@Entity
public class UserBean {
    @Id
    private long uid;
    private String userName;
    private int gender;
    private String phone;
    @Generated(hash = 29795852)
    public UserBean(long uid, String userName, int gender, String phone) {
        this.uid = uid;
        this.userName = userName;
        this.gender = gender;
        this.phone = phone;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public long getUid() {
        return this.uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getGender() {
        return this.gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
