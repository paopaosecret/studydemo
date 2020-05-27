package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.share.QzoneShare;
//import com.tencent.mm.opensdk.constants.ConstantsAPI;
//import com.tencent.mm.opensdk.modelmsg.SendAuth;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.open.utils.ApkExternalInfoTool;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.net.URI;

/**
 * Created by bing.zhao on 2017/4/1.
 */

public class QQLoginActivity extends Activity  implements IUiListener {

    private Tencent mTencent;

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_login);
        api = WXAPIFactory.createWXAPI(this, "wxbe2b0c5c5ff6d2b1");
        api.registerApp("wxbe2b0c5c5ff6d2b1");
        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        String APP_ID = "1106060896";
        mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());

        // 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取
        // 初始化视图
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String Scope = "all"; //get_user_info,add_t
                if (!mTencent.isSessionValid())
                {
                    mTencent.login(QQLoginActivity.this, Scope, QQLoginActivity.this);
                }
            }
        });


        findViewById(R.id.btn_wxlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String WX_APP_ID = "wxbe2b0c5c5ff6d2b1";
                IWXAPI api = WXAPIFactory.createWXAPI(QQLoginActivity.this,WX_APP_ID,false);
                api.registerApp(WX_APP_ID);
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                api.sendReq(req);
            }
        });

        findViewById(R.id.btn_wxshare_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.sendTxtApps(QQLoginActivity.this,api);
            }
        });

        findViewById(R.id.btn_wxshare_minipro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.sendMiniApps(QQLoginActivity.this,api);
            }
        });
        findViewById(R.id.btn_wxgoto_minipro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.gotoMiniApps(QQLoginActivity.this,api);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode,resultCode,data,this);
    }

    @Override
    public void onComplete(Object o) {
        Log.i("loginThread",o.toString());

        Toast.makeText(getApplicationContext(), "登录成功,看log", Toast.LENGTH_SHORT).show();
        /**到此已经获得OpneID以及其他你想获得的内容了
         QQ登录成功了，我们还想获取一些QQ的基本信息，比如昵称，头像什么的，这个时候怎么办？
         sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
         如何得到这个UserInfo类呢？  */
        String openidString = "";
        String accessToken = "";
        String expires ="";
        try {
            openidString = ((JSONObject)o).getString("openid");
            accessToken = ((JSONObject)o).getString("access_token");
            expires = ((JSONObject)o).getString("expires_in");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("loginThread","openId:" + openidString);
        mTencent.setOpenId(openidString);
        mTencent.setAccessToken(accessToken, expires);
        QQToken qqToken = mTencent.getQQToken();

        Log.i("loginThread","qqtoken_openId:" + qqToken.getOpenId());
        Log.i("loginThread","appId:" + qqToken.getAppId());
        Log.i("loginThread","access_token:" + qqToken.getAccessToken());
        UserInfo info = new UserInfo(getApplicationContext(), qqToken);
        //这样我们就拿到这个类了，之后的操作就跟上面的一样了，同样是解析
        info.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Log.i("loginThread",o.toString());
                try {
                    String bitmap = ((JSONObject)o).getString("figureurl_qq_2");
                    Log.i("loginThread","head_image: " + bitmap );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                Log.i("loginThread",uiError.toString() + "message:"+ uiError.errorMessage + ",code:" +uiError.errorCode);
            }

            @Override
            public void onCancel() {
                Log.i("loginThread","onCancel");
            }
        });
    }

    @Override
    public void onError(UiError uiError) {
        Log.i("loginThread",uiError.toString() + "message:"+ uiError.errorMessage + ",code:" +uiError.errorCode);
    }

    @Override
    public void onCancel() {
        Log.i("loginThread","onCancel");
    }

}
