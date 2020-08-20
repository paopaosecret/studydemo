package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.GifView;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bing.zhao on 2016/12/19.
 */

public class GitActivity extends Activity {

    private GifView mGifView;
    private GifView mGifViewPro;
    private TextView mShow;
    private WebView webView;
    private ImageView ivGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        mGifView = (GifView)findViewById(R.id.gv_gif);
        mGifView.setMovieResource(R.raw.walkingboy);
        mGifViewPro= (GifView)findViewById(R.id.gv_gif_pro);
        ivGif = findViewById(R.id.iv_gif);
        String gifFilePath = "http://dl.58cdn.com.cn/shangjiatong/sjt/test/gameenter1.gif";

        Glide.with(this).load(gifFilePath).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivGif);
//        Glide.with(this).load(R.raw.gameenter1).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivGif);

        mGifViewPro.setMovieResource(R.raw.finish_goal);
        mShow = (TextView)findViewById(R.id.tv_show);
        webView = (WebView)findViewById(R.id.webview);
        webView.setVerticalScrollBarEnabled(false); //垂直滚动条不显示
        webView.setHorizontalScrollBarEnabled(false);//水平不显示
        WebSettings webSettings=webView.getSettings();
        webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
        webSettings.setDefaultTextEncodingName("utf-8") ;
        webSettings.setJavaScriptEnabled(false);
        webSettings.setUseWideViewPort(true);//屏幕适配:设置webview推荐使用的窗口，设置为true
        webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式，也设置为true
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(false);//是否支持缩放
        webSettings.setBuiltInZoomControls(false);//添加对js功能的支持
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm);
        webView.setWebViewClient(new WebViewClient());
//        webView.setBackgroundColor(getResources().getColor(R.color.white));
        webView.setBackgroundColor(0); // 设置背景色
        webView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255


//        webView.loadUrl(gifPath);

        String gifPath = "\"http://dl.58cdn.com.cn/shangjiatong/sjt/test/gameenter1.gif\"";
        String data = "<html><div><img width= \"100%\"  height= \"100%\" src=" + gifPath + "/></div></html>";
        webView.loadDataWithBaseURL(gifFilePath, data, "text/html", "utf-8", null);
        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGifView.isPaused()){
                    mGifView.setPaused(false);
                    mGifViewPro.setPaused(false);
                }else{
                    mGifView.setPaused(true);
                    mGifViewPro.setPaused(true);
                }
            }
        });

    }


}
