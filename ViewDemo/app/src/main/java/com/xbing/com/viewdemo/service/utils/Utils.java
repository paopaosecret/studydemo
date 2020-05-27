package com.xbing.com.viewdemo.service.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.MyTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.moudle.mvp.bean.JsonBusinessDataBean;
import com.xbing.com.viewdemo.moudle.mvp.bean.ShareMoudleBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Type;

/**
 * Created by zhaobing on 2016/7/1.
 */
public class Utils {
    /**
     * @param dip
     * @description: dip转px
     * @return: int
     * @Created
     * @Modify
     */
    public static int dip2Px(Context context, float dip)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + (dip > 0 ? 0.5f : -0.5f));
    }

    /**
     * @param px
     * @description: px转dip
     * @return: int
     * @Created
     * @modify
     */
    public static int px2Dip(Context context, float px)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + (px > 0 ? 0.5f : -0.5f));
    }

    /**
     * @param spValue
     * @description: sp转px
     * @return: int
     * @Created
     * @modify
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getImagePath(String fileName){
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "Pictures" + File.separator;
        String path = rootPath + fileName;
        Log.i("Utils","getImagePath:" + path);
        return path;
    }

    public static void sendMiniApps(Context context,IWXAPI api) {
//        IWXAPI api = WXAPIFactory.createWXAPI(context, "wxbe2b0c5c5ff6d2b1");
//        api.registerApp("wxbe2b0c5c5ff6d2b1");

        WXMiniProgramObject miniProgramObj = new WXMiniProgramObject();
        miniProgramObj.webpageUrl = "http://www.qq.com"; // 兼容低版本的网页链接
        miniProgramObj.userName = "gh_fc06e2ed4f06";     // 小程序原始id
        miniProgramObj.path = "/pages/index?ppu=ppu-id";            //小程序页面路径
        WXMediaMessage msg = new WXMediaMessage(miniProgramObj);
        msg.title = "Title";                    // 小程序消息title
        msg.description = "小程序消息Desc";               // 小程序消息desc
        Bitmap bmp=new BitmapFactory().decodeResource(context.getResources(),R.mipmap.ic_launcher);
        Bitmap thumb=Bitmap.createScaledBitmap(bmp,90,90,true);//注意这里的缩略图大小
        bmp.recycle();
        msg.thumbData= bmpToByteArray(thumb,true);                     // 小程序消息封面图片，小于128k

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;  // 目前支持会话
        api.sendReq(req);
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis())
                :type + System.currentTimeMillis();
    }

    public static void gotoMiniApps(Context context,IWXAPI api){

        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = "gh_fc06e2ed4f06"; // 填小程序原始id（官方实例请填写自己的小程序id）
        req.path = "";  ; //拉起小程序页面的可带参路径，不填默认拉起小程序首页   ///pages/index?ppu=ppu-id

        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;// 可选打开 正式版

        //req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_TEST;// 可选打开 开发版

        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_PREVIEW;// 可选打开 体验版
        api.sendReq(req);
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void sendTxtApps(Context context,IWXAPI api) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "hello";

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObject;
        msg.description = "text";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text" + System.currentTimeMillis();
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }

    public static <T extends ShareMoudleBean> JsonBusinessDataBean<T> fromJson2Array(String json) throws Exception{
        JsonBusinessDataBean<T> bean  = new Gson().fromJson(json,new TypeToken<JsonBusinessDataBean<T>>(){}.getType());
        return bean;
    }

    public static <T extends Object> JsonBusinessDataBean getObject(String json,T t){
        Gson gson = new Gson();
        Type type = new TypeToken<JsonBusinessDataBean<ShareMoudleBean>>() {}.getType();

        JsonBusinessDataBean<T> r = gson.fromJson(json, type);
        return r;
    }
}
