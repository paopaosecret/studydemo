package com.example;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by zhaobing04 on 2018/4/18.
 */

public class MyTask<T> {
    public final String json = "{\"body\":{\"error\":\"eeee\",\"isCustom\":\"False\"},"

            + "\"header\":{\"responseCode\":\"0000\",\"responseMessage\":\"�ɹ�\"}}";

    public static interface MyTaskCallback<T> {
        public void onStart();
        public void onError(String error);
        public void onSuccessed(T body);

    }



    public static class Header {

        @Expose
        public String responseCode;

        @Expose
        public String responseMessage;

    }

    public static class Body {

        @Override
        public String toString() {
            return "Body [error=" + error + ", isCustom=" + isCustom + "]";
        }

        @Expose
        public String error;

        @Expose
        public String isCustom;

    }
    public static class Response<T> {

        @Expose
        public Header header;

        @Expose
        public T body;

    }

    private MyTaskCallback<T> callback;

    private final Type type;
    public MyTask(MyTaskCallback<T> callback, Type type) {

        this.callback = callback;
        this.type = type;
    }

    public void start() {
        run();
    }

    protected void run() {
        if (callback != null) {
            callback.onStart();
        }
        T body = null;
        Gson gson = new Gson();
        Type t = new TypeToken<Response<T>>() {
        }.getRawType();

        System.out.println(type);
        System.out.println(t);
        System.out.println(new TypeToken<Response<T>>() {
        }.getType());
        Response<T> r = gson.fromJson(json, type);
        body = r.body;
        if (callback != null) {
            callback.onSuccessed(body);
        }

    }

    public static void main(String[] args) {
        MyTaskCallback<Body> c = new MyTaskCallback<Body>() {
            @Override
            public void onSuccessed(Body body) {
                System.out.println(body);
            }
            @Override
            public void onStart() {

            }
            @Override
            public void onError(String error) {

            }

        };

        new MyTask<Body>(c, new TypeToken<Response<Body>>() {
        }.getType()).start();
    }

}

