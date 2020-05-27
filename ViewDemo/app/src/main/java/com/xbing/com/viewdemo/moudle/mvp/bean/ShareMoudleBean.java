package com.xbing.com.viewdemo.moudle.mvp.bean;

import java.io.Serializable;

/**
 * Created by zhaobing04 on 2018/4/18.
 */

public class ShareMoudleBean implements Serializable{
    private String title;
    private String describle;
    private String url;
    private String path;
    private String userName;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int[] getPlatformType() {
        return PlatformType;
    }

    public void setPlatformType(int[] platformType) {
        PlatformType = platformType;
    }

    private int[] PlatformType;
}
