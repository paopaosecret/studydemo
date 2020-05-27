package com.xbing.com.viewdemo.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.cache.DiskLruCache;
import com.xbing.com.viewdemo.cache.LoadAsyncTask;
import com.xbing.com.viewdemo.cache.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhaobing04 on 2018/3/17.
 */

public class JavaJsInteractiveActivity extends Activity {

    private static final String TAG = JavaJsInteractiveActivity.class.getSimpleName();

    private WebView wvContainer;

    private DiskLruCache diskLruCache;
    private static final int MAX_SIZE = 100 * 1024 * 1024;;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_js_interactive);
        initDiskLruCache();
        initWebView();
        findViewById(R.id.btn_java_use_js).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iccid = "java hello word";
                String showICCID = "javascript:setICCID('"+iccid+"')";
                wvContainer.loadUrl(showICCID);
            }
        });

    }

    private void initDiskLruCache() {
        if (diskLruCache == null || diskLruCache.isClosed()) {
            try {
                File cacheDir = Util.getDiskCacheDir(this, "CacheDir");
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                diskLruCache = DiskLruCache.open(cacheDir, 1, 1, MAX_SIZE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        wvContainer = (WebView) findViewById(R.id.wv_root);
        WebSettings webSettings = wvContainer.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
//        wvContainer.loadUrl("file:///android_asset/test.html");
        wvContainer.loadUrl("http://www.zbpaoapo.cn/");
        // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
        wvContainer.addJavascriptInterface(this, "jswritecard");
        //相当于添加一个js回调接口，然后给这个起一个别名，
        //我这里起的名字jswritecard（js写卡）。
        wvContainer.setWebViewClient(new WebViewClient(){
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final String url = request.getUrl().toString();
                    if(!TextUtils.isEmpty(url)){
                        if(url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg")){
                            String key = Util.hashKeyForDisk(url);
                            DiskLruCache.Snapshot snapshot = null;
                            try {
                                snapshot = diskLruCache.get(key);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return super.shouldInterceptRequest(view, request);
                            }
                            if (snapshot != null) {
                                Log.e("DiskLruCache","从缓存返回：key:" + key );
                                InputStream in = snapshot.getInputStream(0);
                                return (new WebResourceResponse("image/png", "UTF-8", in));
                            }

                            new LoadAsyncTask(new LoadAsyncTask.ILoadStatus() {
                                @Override
                                public void OnBeforeLoad() {

                                }

                                @Override
                                public void OnDataTransfer() {

                                }

                                @Override
                                public void OnDataLoaded() {

                                }
                            }).execute(diskLruCache,url);
                            Log.e("BaseHybridActivity","webView请求：" + request.getUrl().toString());
                        }
                    }
                }
                return super.shouldInterceptRequest(view, request);
            }
        });
    }

    //@android.webkit.JavascriptInterface为了解
    //决addJavascriptInterface漏洞的，在4.2以后才有的。
    @android.webkit.JavascriptInterface
    public void getICCID(){
        //js 调用Java 方法  无参
        Log.e(TAG, "js 调用 Java 的 getICCID！！");
        //js若想更改Activity 需要使其运行在主线程
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                getCardICCID();
            }
        });

    }

    private void getCardICCID() {
        Toast.makeText(this,"js 调用 java method getCarddCCID()",Toast.LENGTH_SHORT).show();
        Log.e(TAG,"js 调用 java method getCarddCCID()");
    }

    @android.webkit.JavascriptInterface
    public void get2F99(){
        Log.e(TAG, "js 调用 Java de get2F99！！");
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                getCard2F99();
            }
        });
    }

    private void getCard2F99() {
        Toast.makeText(this,"js 调用 java method getCard2F99()",Toast.LENGTH_SHORT).show();
        Log.e(TAG,"js 调用 java method getCard2F99()");
    }

    @android.webkit.JavascriptInterface
    public void writeCard(final String indata){
        //js调用Java 有参
        Log.e(TAG, "js 调用 Java de writeCard ,indata : "+indata);
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                writeJavaCrad(indata);
            }
        });
    }

    private void writeJavaCrad(String indata) {
        Toast.makeText(this,"js 调用 java method writeJavaCrad()",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "js 调用 Java method writeJavaCrad() ,param : "+indata);
    }

}
