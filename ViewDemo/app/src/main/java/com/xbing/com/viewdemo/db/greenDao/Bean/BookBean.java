package com.xbing.com.viewdemo.db.greenDao.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhaobing04 on 2018/3/20.
 */
@Entity   //标识实体类，GreenDao会映射成sqlite中的一个表
public class BookBean {
    @Id
    private long bid;
    private String bookName;
    private int price;
    private long publishDate;
    @Generated(hash = 1808989586)
    public BookBean(long bid, String bookName, int price, long publishDate) {
        this.bid = bid;
        this.bookName = bookName;
        this.price = price;
        this.publishDate = publishDate;
    }
    @Generated(hash = 269018259)
    public BookBean() {
    }
    public long getBid() {
        return this.bid;
    }
    public void setBid(long bid) {
        this.bid = bid;
    }
    public String getBookName() {
        return this.bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public long getPublishDate() {
        return this.publishDate;
    }
    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }
}
