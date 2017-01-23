package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.yuntongxun.ecsdk.ECChatManager;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.im.ECFileMessageBody;
import com.yuntongxun.ecsdk.im.ECImageMessageBody;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;

/**
 * Created by bing.zhao on 2017/1/15.
 */

public class RLYActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        findViewById(R.id.btn_send_txt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send_txt:
                sendTxt();
                break;
        }
    }

    private void sendTxt() {
        try {
            // 组建一个待发送的ECMessage
            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
            //如果需要跨应用发送消息，需通过appkey+英文井号+用户帐号的方式拼接，发送录音、发送群组消息等与此方式一致。
            //例如：appkey=20150314000000110000000000000010
//            帐号ID=john
//            传入帐号=20150314000000110000000000000010#john
            //msg.setTo(""appkey#John的账号");
            // 设置消息接收者
            msg.setTo("8015306400000003");

            // 创建一个文本消息体，并添加到消息对象中
            ECTextMessageBody msgBody = new ECTextMessageBody("this is a message");
//
//            // 或者创建一个图片消息体 并且设置附件包体（其实图片也是相当于附件）
//            // 比如我们发送SD卡里面的一张Tony_2015.jpg图片
//            ECImageMessageBody msgBody  = new ECImageMessageBody();
//            // 设置附件名
//            msgBody.setFileName("Tony_2015.jpg");
//            // 设置附件扩展名
//            msgBody.setFileExt("jpg");
//            // 设置附件本地路径
//            msgBody.setLocalUrl("../Tony_2015.jpg");
//
//            // 或者创建一个创建附件消息体
//            // 比如我们发送SD卡里面的一个Tony_2015.zip文件
//            ECFileMessageBody msgBody  = new ECFileMessageBody();
//            // 设置附件名
//            msgBody.setFileName("Tony_2015.zip");
//            // 设置附件扩展名
//            msgBody.setFileExt(zip);
//            // 设置附件本地路径
//            msgBody.setLocalUrl("../Tony_2015.zip");
//            // 设置附件长度
//            msgBody.setLength("Tony_2015.zip文件大小");
//
            // 将消息体存放到ECMessage中
            msg.setBody(msgBody);
            // 调用SDK发送接口发送消息到服务器
            ECChatManager manager = ECDevice.getECChatManager();
            manager.sendMessage(msg, new ECChatManager.OnSendMessageListener() {
                @Override
                public void onSendMessageComplete(ECError error, ECMessage message) {
                    // 处理消息发送结果
                    if(message == null) {
                        Log.i("ECSDK_Demo", "send message success");
                        return ;
                    }else{
                        Log.i("ECSDK_Demo", "send message status:" +message.getMsgStatus() +",from:"+ message.getForm()+",to:"+message.getTo());
                    }
                    // 将发送的消息更新到本地数据库并刷新UI
                }

                @Override
                public void onProgress(String msgId, int totalByte, int progressByte) {
                    // 处理文件发送上传进度（尽上传文件、图片时候SDK回调该方法）
                    Log.i("ECSDK_Demo", "send message Progrss");
                }
            });
        } catch (Exception e) {
            // 处理发送异常
            Log.e("ECSDK_Demo", "send message fail , e=" + e.getMessage());
        }

    }
}
