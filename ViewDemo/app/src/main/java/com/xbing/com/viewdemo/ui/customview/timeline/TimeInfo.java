package com.xbing.com.viewdemo.ui.customview.timeline;

/**
 * Created by Administrator on 2016/8/31.
 */
public class TimeInfo {

    private boolean isFrist;

    public boolean isFrist() {
        return isFrist;
    }

    public void setFrist(boolean frist) {
        isFrist = frist;
    }

    private int type;

    private String title;

    private String imgpath;

    private String notes;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
