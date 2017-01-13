package com.xbing.com.viewdemo.service.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;

import com.xbing.com.viewdemo.ui.activity.MainActivity;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.activity.NotifyActivity;

/**
 * Created by zhaobing on 2016/8/11.
 */
public class NotifyUtils {

    /**
     * 最早方式显示通知
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi")
    public static void showNotifyOld(Context context){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello word";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
//        notification.setLatestEventInfo(context, "title", "msg", pendingIntent);
        manager.notify(1, notification);

    }

    /**
     * api11 之后显示通知的方式  可以使用builder.getNotification();
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi")
    public static void showNotify11(Context context){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("状态栏提示消息")
                .setContentTitle("下拉后的title")
                .setContentText("下拉后的消息文本")
                .setContentIntent(pendingIntent).getNotification();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        manager.notify(2, notification);

    }

    /**
     * api16 之后显示通知的方式,可以使用builder.build();
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi")
    public static void showNotify16(Context context){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("16状态栏提示消息")
                .setContentTitle("16下拉后的title")
                .setContentText("16下拉后的消息文本")
                .setContentIntent(pendingIntent).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        manager.notify(3, notification);

    }

    public static void showNotifyDefine(Context context){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello word";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        notification.contentIntent = pendingIntent;
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.notify_layout);
        //改变布局控件的属性

        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0,
                new Intent(context, NotifyActivity.class), 0);
        rv.setTextViewText(R.id.tv_title,"change hello");
        rv.setOnClickPendingIntent(R.id.iv_image,pendingIntent1);

        notification.contentView = rv;
        manager.notify(4, notification);
    }

    /**
     * 取消这个id的通知
     * @param id
     */
    public static void cancelNotify(Context context,int id){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(id);
    }

    /**
     * 取消全部通知
     */
    public static void cancelAllNotify(Context context){
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }
}
