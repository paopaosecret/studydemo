package com.xbing.com.viewdemo.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.javatest.threadtest.executor.MyExecutor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bing.zhao on 2017/1/11.
 */

public class ImageLoader {

    LruCache<String,Bitmap> mImageCache;
    MyExecutor mExecutor;

    public ImageLoader(){
        initImageLoader();
    }

    private void initImageLoader() {
        mExecutor = MyExecutor.getInstance();
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() /1024;
            }
        };
    }

    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void displayImage(final String url, final ImageView v){
        v.setTag(url);
        mExecutor.executeTask(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if(bitmap == null){
                    return;
                }
                if(v.getTag().equals(url)){
                    v.setImageBitmap(bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }

}
