package com.xbing.com.viewdemo.service.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaobing on 2016/10/14.
 */

public class NetRequest {
    private static final String TAG = NetRequest.class.getSimpleName();

    private ExecutorService mThreadPool;

    private static NetRequest mInstance;

    private NetRequest()
    {
        mThreadPool = Executors.newFixedThreadPool(2);
    }

    public static NetRequest getInstance()
    {
        if (null == mInstance)
        {
            mInstance = new NetRequest();
        }
        return mInstance;
    }

    public synchronized void uploadFile(Handler handler, int what, String path)
    {
        if (path == null || path.length() == 0)
        {
            return;
        }
        final Handler tmpHandler = handler;
        final int tmpWhat = what;
        final String tmpPath = path;
        mThreadPool.submit(new Runnable()
        {
            @Override
            public void run()
            {
                String ret = upload(tmpPath);
                sendMessage(tmpHandler, tmpWhat, ret);
            }
        });
    }

    public synchronized void downloadFile(Handler handler, int what, String url, String savePath)
    {
        if (url == null || url.length() == 0 || savePath == null
                || savePath.length() == 0)
        {
            sendMessage(handler, what, false);
            return;
        }
        final Handler tmpHandler = handler;
        final int tmpWhat = what;
        final String tmpUrl = url;
        final String tmpSavePath = savePath;
        mThreadPool.submit(new Runnable()
        {

            @Override
            public void run()
            {
                Boolean ret = download(tmpUrl, tmpSavePath);
                sendMessage(tmpHandler, tmpWhat, ret);
            }
        });
    }

    private void sendMessage(Handler handler, int what, Object obj)
    {
        Message msg = handler.obtainMessage();
        msg.what = what;
        msg.obj = obj;
        handler.sendMessage(msg);
    }

    private String upload(String path)
    {
        String ret = null;
        File file = new File(path);
        if (!file.exists())
        {
            return null;
        }
        try
        {
            String split = "--";
            String end = "\r\n";
            String name = "avatar";
            String boundary = UUID.randomUUID().toString();
            String tokenString = "8e47a3fe79ca4baeb2619a78e60f792e";
            String url = "http://cdn.landinghome.net/uploadAvatar.do" + "?" + "access_token" + "="
                    + tokenString;
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "multipart/form-data;;boundary=" + boundary);

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            DataInputStream in = new DataInputStream(new FileInputStream(file));

            StringBuilder sb = new StringBuilder();
            sb.append(split);
            sb.append(boundary);
            sb.append(end);

            sb.append("Content-Disposition: form-data; name=\"" + name
                    + "\"; filename=\"" + file.getName() + "\"" + end);
            sb.append("Content-Type:  application/x-zip-compressed;" + end);
            sb.append(end);

            //构建头部
            out.write(sb.toString().getBytes());
            //数据
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1)
            {
                out.write(bufferOut, 0, bytes);
            }
            out.write(end.getBytes());

            out.write((split + boundary + split + end).getBytes());

            in.close();
            out.close();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStream input = conn.getInputStream();
                StringBuffer sb2 = new StringBuffer();
                int line;
                while ((line = input.read()) != -1)
                {
                    sb2.append((char) line);
                }
                ret = sb2.toString();
                input.close();
            }
            conn.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Log.e(TAG, "ret:" + ret);
        return ret;
    }

    private boolean download(String urlPath,String path){
        boolean ret=false;
        File file=new File(path);

        URL url;
        try
        {
            url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            if (conn.getResponseCode() == 200) {

                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                if(fos!=null){
                    fos.close();
                }
                if(is!=null){
                    is.close();
                }
                if(conn!=null){
                    conn.disconnect();
                }
                ret=true;
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ret;

    }

    private boolean downloadFile(String strUri, String path)
    {
        boolean ret = false;
        HttpURLConnection conn=null;
        BufferedOutputStream out=null;
        InputStream in=null;
        File file = new File(path);
        if (file.exists() && file.length() > 0)
        {
            return true;
        }
        try
        {
            final URL url = new URL(strUri);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                out = new BufferedOutputStream(
                        new FileOutputStream(file));
                in = conn.getInputStream();

                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1)
                {
                    out.write(bufferOut, 0, bytes);
                }

                out.flush();
                ret = true;

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            if(out!=null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                conn.disconnect();
            }

        }
        return ret;
    }
}
