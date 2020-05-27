package com.xbing.com.viewdemo.moudle.mvp.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.moudle.mvp.BaseMvpActivity;
import com.xbing.com.viewdemo.moudle.mvp.bean.JsonBusinessDataBean;
import com.xbing.com.viewdemo.moudle.mvp.bean.ShareMoudleBean;
import com.xbing.com.viewdemo.moudle.mvp.presenter.LoginPresenter;
import com.xbing.com.viewdemo.moudle.mvp.view.ILoginView;
import com.xbing.com.viewdemo.service.utils.Utils;

import java.lang.reflect.Type;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;

/**
 * Created by zhaobing04 on 2018/3/19.
 */

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements ILoginView,View.OnClickListener {

    private TextView tvShow;
    IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wxbe2b0c5c5ff6d2b1");
        if(api == null){
            Log.e("wxlogin","api == null");
        }
        api.registerApp("wxbe2b0c5c5ff6d2b1");

        if (!api.isWXAppInstalled()) {
            //提醒用户没有按照微信
            Log.e("wxlogin","no install wx");
        }
        initView();
        initData();
        initListener();

    }

    @Override
    protected LoginPresenter initPresenter() {
        if(mPresenter == null){
            mPresenter = new LoginPresenter(this,this);
        }
        return mPresenter;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        tvShow = (TextView) this.findViewById(R.id.tv_show);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_add_user).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void showToast(String msg) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
//                mPresenter.login("zhangsan","12345678");
//                sendMiniApps(this,"title","content","url",R.drawable.ic_launcher);
                String data = "{\n" +
                        "\t\"function\": \"showShareModule\",\n" +
                        "\t\"params\": {\n" +
                        "\t\t\"title\": \"商家通小程序\",\n" +
                        "\t\t\"describle\": \"商家通小程序测试\",\n" +
                        "\t\t\"url\": \"https://m.58.com\",\n" +
                        "\t\t\"PlatformType\": [5],\n" +
                        "\t\t\"path\": \"/vendors/im/pages/sessions/sessions\",\n" +
                        "\t\t\"userName\": \"gh_76d722e7571e\"\n" +
                        "\t}\n" +
                        "}";
                Log.d("GSON","data:" + data);
                JsonBusinessDataBean<ShareMoudleBean> bean = null;
                try {
                    Type type = new TypeToken<JsonBusinessDataBean<ShareMoudleBean>>() {}.getType();
                    bean = new Gson().fromJson(data,type);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("GSON","data:exception");
                }
                ShareMoudleBean shareMoudleBean = bean.getParams();
                Log.d("GSON","data:" + shareMoudleBean.getTitle());
                break;
            case R.id.btn_add_user:
                mPresenter.addUser();
                break;
        }
    }

    @Override
    public void updateTextView() {
        tvShow.setText("Login Success");
    }

//    public void sendMiniApps(Context context, String title, String content,
//                                    String url, int resource) {
//
//        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
//        //低版本微信打开 URL
//        miniProgram.webpageUrl = url;
//        //跳转的小程序的原始 ID
//        miniProgram.userName = "gh_fc06e2ed4f06";
//        //小程序的 Path
//        miniProgram.path = "pages/index/index";
//
//        WXMediaMessage msg = new WXMediaMessage(miniProgram);
//        final String shareTitle = title;
//        if (!TextUtils.isEmpty(shareTitle)) {
//            msg.title = title;
//        }
//
//        final String shareDescription = content;
//        if (!TextUtils.isEmpty(shareDescription)) {
//            msg.description = shareDescription;
//        }
//
////        Bitmap icon = BitmapFactory.decodeResource(Resources.getSystem(),resource);
////        if (icon != null) {
////            msg.setThumbImage(icon);
////        } else {
////            Bitmap temp = icon;
////            msg.setThumbImage(temp);
////        }
//
//
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("webpage");
//        req.message = msg;
//        req.scene = WXSceneSession;
//
//        boolean ret = api.sendReq(req);
//        Log.e("wxlogin","ret = " + ret);
//    }
//
//    private String buildTransaction(final String type) {
//        return (type == null) ? String.valueOf(System.currentTimeMillis())
//                :type + System.currentTimeMillis();
//    }

}
