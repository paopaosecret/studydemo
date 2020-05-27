package com.xbing.com.viewdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bing.zhao on 2017/8/23.
 */

public class Book implements Parcelable{
    public Book(Parcel in) {
        bookName = in.readString();
        bookId = in.readInt();
    }

    public Book(){

    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    private String bookName;
    private int    bookId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookName);
        dest.writeInt(bookId);
    }
}
