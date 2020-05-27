package com.xbing.com.viewdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.fm.openinstall.OpenInstall;
import com.xbing.com.viewdemo.db.MySqliteDBHelper;
import com.xbing.com.viewdemo.db.dao.StudentDao;
import com.xbing.com.viewdemo.db.greenDao.DaoManager;
import com.xbing.com.viewdemo.db.model.Student;
import com.xbing.com.viewdemo.ui.service.FloatService;
import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECError;
import com.yuntongxun.ecsdk.ECInitParams;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.OnChatReceiveListener;
import com.yuntongxun.ecsdk.SdkErrorCode;
import com.yuntongxun.ecsdk.im.ECMessageNotify;
import com.yuntongxun.ecsdk.im.group.ECGroupNoticeMessage;

import java.util.List;

//import com.facebook.stetho.Stetho;
//import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * Created by zhaobing on 2016/8/23.
 */
public class MyApplication extends Application {

    private final String TAG = MyApplication.class.getSimpleName();

    protected static MyApplication instance = new MyApplication();

    private MyActivityManager mActivityManager;

    public static  MyApplication getInstance(){
        return instance;
    }

    public static Context getContext(){
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

//        Stetho.initialize(//Stetho初始化
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                        .build()
//        );
        SDKInitializer.initialize(getApplicationContext());
        StudentDao dao = new StudentDao(this);

        Student student = new Student();

        student.setId(1);
        student.setGender("男");
        student.setName("张三");
        student.setMobile("13510266952");
        dao.beginTransaction();
        dao.addStudent(student);
        dao.commitTransaction();

        MySqliteDBHelper dbHelper = new MySqliteDBHelper(this);
        dbHelper.getWritableDatabase();
        mActivityManager = MyActivityManager.getInstance();

        MySharedPreferences.getInstances(this).putBoolean(MySharedPreferences.SP_KEY_DEBUG_SWITCH,true);

        this.registerActivityLifecycleCallbacks(mActivityManager);
//        Intent intent = new Intent(this, MyService.class);
//        startService(intent);
//        init();

        initGreenDao();
//        startService(new Intent(this, HeartService.class));
        if (isMainProcess()) {
            OpenInstall.init(this);
        }

    }


    public void stopFloatView(){
        Intent intent = new Intent(this, FloatService.class);
        stopService(intent);
    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }

    private void initGreenDao() {
        DaoManager.init(this);
    }

    private void init() {
        //判断SDK是否已经初始化
        if(!ECDevice.isInitialized()) {
 /*  initial: ECSDK 初始化接口
            * 参数：
            *     inContext - Android应用上下文对象
            *     inListener - SDK初始化结果回调接口，ECDevice.InitListener
            *
            * 说明：示例在应用程序创建时初始化 SDK引用的是Application的上下文，
            *       开发者可根据开发需要调整。
            */
            ECDevice.initial(getApplicationContext(), new ECDevice.InitListener() {
                @Override
                public void onInitialized() {
                    // SDK已经初始化成功
                    Log.i("","初始化SDK成功");
                    //设置登录参数，可分为自定义方式和通讯账号验密方式，详情点此查看>>
                    //设置通知回调监听包含登录状态监听，接收消息监听，呼叫事件回调监听和
                    //设置接收来电事件通知Intent等，详情点此查看>>
                    //验证参数是否正确，登陆SDK，详情点此查看>>
                    login();
                }



                @Override
                public void onError(Exception exception) {
                    //在初始化错误的方法中打印错误原因
                    Log.i("","初始化SDK失败"+exception.getMessage());
                }
            });
        }
// 已经初始化成功，后续开发业务代码。
        Log.i(TAG, "初始化SDK及登陆代码完成");
    }

    private void login() {

        initListener();

        ECInitParams params = ECInitParams.createParams();
        params.setUserid("13510577981");//推荐使用客户项目APP的登录帐号，测试阶段Userid可以填写手机号
        params.setAppKey("8aaf07085635aae50156538d3ea2138f");//应用ID；登陆官网查看控制台→应用列表→应用管理→APP ID
        params.setToken("d040e70974484720b7b463a0fe7a7e63"); //应用Token；登陆官网查看控制台→应用列表→应用管理→APP TOKEN
        //设置登陆验证模式：自定义登录方式
        params.setAuthType(ECInitParams.LoginAuthType.NORMAL_AUTH);
        //LoginMode（强制上线：FORCE_LOGIN  默认登录：AUTO。使用方式详见注意事项）
        params.setMode(ECInitParams.LoginMode.FORCE_LOGIN);
        //验证参数是否正确
        if(params.validate()) {
            // 登录函数
            ECDevice.login(params);
        }

    }

    private void initListener() {

        //设置登录回调监听
        ECDevice.setOnDeviceConnectListener(new ECDevice.OnECDeviceConnectListener() {
            public void onConnect() {
                //兼容旧版本的方法，不必处理
            }

            @Override
            public void onDisconnect(ECError error) {
                //兼容旧版本的方法，不必处理
            }

            @Override
            public void onConnectState(ECDevice.ECConnectState state, ECError error) {
                if(state == ECDevice.ECConnectState.CONNECT_FAILED ){
                    if(error.errorCode == SdkErrorCode.SDK_KICKED_OFF) {
                        Log.i("","==帐号异地登陆");
                    }
                    else
                    {
                        Log.i("","==其他登录失败,错误码："+ error.errorCode);
                    }
                    return ;
                }
                else if(state == ECDevice.ECConnectState.CONNECT_SUCCESS) {
                    Log.i("","==登陆成功");
                }
            }
        });

        //IM接收消息监听，使用IM功能的开发者需要设置。
        ECDevice.setOnChatReceiveListener(new OnChatReceiveListener() {
            @Override
            public void OnReceivedMessage(ECMessage msg) {
                Log.i("","==收到新消息");
            }

            @Override
            public void onReceiveMessageNotify(ECMessageNotify ecMessageNotify) {

            }

            @Override
            public void OnReceiveGroupNoticeMessage(ECGroupNoticeMessage notice) {
                //收到群组通知消息,可以根据ECGroupNoticeMessage.ECGroupMessageType类型区分不同消息类型
                Log.i("","==收到群组通知消息（有人加入、退出...）");
            }

            @Override
            public void onOfflineMessageCount(int count) {
                // 登陆成功之后SDK回调该接口通知帐号离线消息数
            }

            @Override
            public int onGetOfflineMessage() {
                return 0;
            }

            @Override
            public void onReceiveOfflineMessage(List msgs) {
                // SDK根据应用设置的离线消息拉取规则通知应用离线消息
            }

            @Override
            public void onReceiveOfflineMessageCompletion() {
                // SDK通知应用离线消息拉取完成
            }

            @Override
            public void onServicePersonVersion(int version) {
                // SDK通知应用当前帐号的个人信息版本号
            }

            @Override
            public void onReceiveDeskMessage(ECMessage ecMessage) {

            }

            @Override
            public void onSoftVersion(String s, int i) {

            }
        });

    }


}
