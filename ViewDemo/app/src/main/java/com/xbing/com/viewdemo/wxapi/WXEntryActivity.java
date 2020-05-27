package com.xbing.com.viewdemo.wxapi;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelmsg.SendAuth;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xbing.com.viewdemo.service.utils.AppUtils;
import com.xbing.com.viewdemo.ui.activity.MainActivity;

import java.net.URLEncoder;
import java.util.List;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI api;
	private BaseResp resp = null;
	private String WX_APP_ID = "wxbe2b0c5c5ff6d2b1";
	// 获取第一步的code后，请求以下链接获取access_token
	private String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 获取用户个人信息
	private String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	private String WX_APP_SECRET = "创建应用后得到的APP_SECRET";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, WX_APP_ID, false);
		api.handleIntent(getIntent(), this);
	}


	@Override
	public void onReq(BaseReq baseReq) {
		Log.d("WXEntryActivity","onReq(BaseReq baseReq):" + baseReq.getType());
		if(baseReq != null){
			Log.d("WXEntryActivity","WXEntryActivity:" + baseReq.toString());
		}
		if(baseReq.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM){
            if (!AppUtils.isRunningForeground(this)) {
                //获取ActivityManager
                ActivityManager mAm = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                //获得当前运行的task
                List<ActivityManager.RunningTaskInfo> taskList = mAm.getRunningTasks(100);
                for (ActivityManager.RunningTaskInfo rti : taskList) {
                    //找到当前应用的task，并启动task的栈顶activity，达到程序切换到前台
                    if (rti.topActivity.getPackageName().equals(getPackageName())) {
                        mAm.moveTaskToFront(rti.id, 0);
                        return;
                    }
                }
                //若没有找到运行的task，用户结束了task或被系统释放，则重新启动mainactivity
                Intent resultIntent = new Intent(this, MainActivity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(resultIntent);
            }

		}
	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) {
		Log.d("WXEntryActivity","onResp(BaseResp resp) type = " + resp.getType());
		if(resp != null){
			Log.d("WXEntryActivity","WXEntryActivity:" + resp.toString());
		}
		if (resp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
//			WXLaunchMiniProgram.Resp launchMiniProResp = (WXLaunchMiniProgram.Resp) resp;
//			String extraData =launchMiniProResp.extMsg; // 对应JsApi navigateBackApplication中的extraData字段数据
			startActivity(new Intent(this, MainActivity.class));
//			Log.d("WXEntryActivity","onResp: extraData:" + extraData);
		}

		String result = "";
		if (resp != null) {
			resp = resp;
		}
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				result = "发送成功";
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				String code = ((SendAuth.Resp) resp).code;

            /*
             * 将你前面得到的AppID、AppSecret、code，拼接成URL 获取access_token等等的信息(微信)
             */
				String get_access_token = getCodeRequest(code);
//				AsyncHttpClient client = new AsyncHttpClient();
//				client.post(get_access_token, new JsonHttpResponseHandler() {
//					@Override
//					public void onSuccess(int statusCode, JSONObject response) {
//						// TODO Auto-generated method stub
//						super.onSuccess(statusCode, response);
//						try {
//
//
//							if (!response.equals("")) {
//								String access_token = response
//										.getString("access_token");
//								String openid = response.getString("openid");
//								String get_user_info_url = getUserInfo(
//										access_token, openid);
//								getUserInfo(get_user_info_url);
//							}
//
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				});

				finish();
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				result = "发送取消";
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				finish();
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				result = "发送被拒绝";
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				finish();
				break;
			default:
				result = "发送返回";
				Toast.makeText(this, result, Toast.LENGTH_LONG).show();
				finish();
				break;
		}
	}

	/**
	 * 通过拼接的用户信息url获取用户信息
	 *
	 * @param user_info_url
	 */
	private void getUserInfo(String user_info_url) {
//		AsyncHttpClient client = new AsyncHttpClient();
//		client.get(user_info_url, new JsonHttpResponseHandler() {
//			@Override
//			public void onSuccess(int statusCode, JSONObject response) {
//				// TODO Auto-generated method stub
//				super.onSuccess(statusCode, response);
//				try {
//
//					System.out.println("获取用户信息:" + response);
//
//					if (!response.equals("")) {
//						String openid = response.getString("openid");
//						String nickname = response.getString("nickname");
//						String headimgurl = response.getString("headimgurl");
//
//					}
//
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
		finish();
	}

	/**
	 * 获取access_token的URL（微信）
	 *
	 * @param code
	 *            授权时，微信回调给的
	 * @return URL
	 */
	private String getCodeRequest(String code) {
		String result = null;
		GetCodeRequest = GetCodeRequest.replace("APPID",
				urlEnodeUTF8(WX_APP_ID));
		GetCodeRequest = GetCodeRequest.replace("SECRET",
				urlEnodeUTF8(WX_APP_SECRET));
		GetCodeRequest = GetCodeRequest.replace("CODE", urlEnodeUTF8(code));
		result = GetCodeRequest;
		return result;
	}

	/**
	 * 获取用户个人信息的URL（微信）
	 *
	 * @param access_token
	 *            获取access_token时给的
	 * @param openid
	 *            获取access_token时给的
	 * @return URL
	 */
	private String getUserInfo(String access_token, String openid) {
		String result = null;
		GetUserInfo = GetUserInfo.replace("ACCESS_TOKEN",
				urlEnodeUTF8(access_token));
		GetUserInfo = GetUserInfo.replace("OPENID", urlEnodeUTF8(openid));
		result = GetUserInfo;
		return result;
	}

	private String urlEnodeUTF8(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}