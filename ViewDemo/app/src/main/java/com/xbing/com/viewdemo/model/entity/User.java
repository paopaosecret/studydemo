package com.xbing.com.viewdemo.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bing.zhao on 2017/8/23.
 */

public class User implements Parcelable {

    public int userId;

    public String userName;

    public boolean isMale;

    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
    }

    //实现了反序列化，自动生成
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //内容描述，自动生成
    @Override
    public int describeContents() {
        return 0;
    }

    //实现了序列化，自动生成
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeByte((byte) (isMale ? 1 : 0));
    }
}
