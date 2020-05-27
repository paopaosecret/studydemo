package com.xbing.com.viewdemo.service;

import android.os.Process;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

/**
 * Created by bing.zhao on 2017/3/29.
 */

public class TcpTools implements Runnable {
    private static final String TAG = TcpTools.class.getSimpleName();
    public static final int DEFAULT_PORT = 5959;//发送时的默认端口
    private Socket mSocket;//连接时的Socket
    private SocketAddress mSocketAddr;//连接的IP和端口地址
    private BufferedReader mReader;//接受数据
    private PrintWriter mWriter;//发送数据
    private Thread mThread;
    private String mSendData;
    private String mReceiveData;
    private boolean isClose;
    private OnReciveDataListener onReciveData;

    public enum E_TYPE_TCP {
        TCPSEND, TCPRECIEVE, OK, TIMEOUT, ERROR
    }

    /**
     * 返回该Socket地址
     * @return SocketAddress [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public SocketAddress getSocketAddr()
    {
        return mSocketAddr;
    }

    /**
     * 根据Ip地址和固定的端口号设置Socket地址
     * @param ip [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public void setSocketAddr(String ip)
    {
        this.mSocketAddr = new InetSocketAddress(ip, DEFAULT_PORT);
    }

    public void setSocketAddr(String ip, int port) {
        this.mSocketAddr = new InetSocketAddress(ip, port);
    }

    /**
     * 得到发送的数据
     *
     * @return String [返回类型说明]
     */
    public String getSendData()
    {
        return mSendData;
    }

    /*
     * 设置发送的数据
     */
    public void setSendData(String sendData)
    {
        this.mSendData = sendData;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean isClose) {
        this.isClose = isClose;
    }

    public TcpTools() {
    }

    public void setOnReciveDataListener(OnReciveDataListener onReciveData) {
        this.onReciveData = onReciveData;
    }

    /**
     *启动TCP线程
     *[参数说明]     
     * @return void
     */
    public synchronized void startConnect() {
        Log.d(TAG, "startConnect()");
        isClose = false;
        mThread = new Thread(this);
        mThread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
        mThread.start();
    }

    private boolean initConnect() {
        Log.d(TAG, "initConnect()");
        int repeatCount = 0;
        //最后一次连接异常
        Exception exception = null;
        //重试三次连接
        while (repeatCount < 3)
        {
            try {
                synchronized (this) {
                    repeatCount ++;
                    mSocket = new Socket();
                    mSocket.connect(mSocketAddr, 3 * 1000);
                    //连接成功 返回
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, e.toString(), e);
                exception = e;
            }
        }
        if (exception instanceof SocketTimeoutException)
        {
            onReciveData.onReciveData(E_TYPE_TCP.TIMEOUT, null);
        }
        else
        {
            onReciveData.onReciveData(E_TYPE_TCP.ERROR, null);
        }
        return false;
    }

    /**
     * 连接成功后发送数据
     */
    private boolean sendDataTmp(String content)
    {
        Log.d(TAG, "sendDataTmp");
        if (mSocket != null && mSocket.isConnected()) {
            if (!mSocket.isOutputShutdown()) {
                mWriter.println(content);
                mWriter.flush();
                Log.d(TAG, content + "\r\n数据发送成功");
                return true;
            }
        }
        return false;
    }

    /**
     * 发送Tcp 线程运行的线程
     */
    @Override
    public void run()
    {
        if (initConnect()) {
            try
            {
                //发送数据
                mSocket.setSoTimeout(20 * 1000);
                mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
                mWriter = new PrintWriter(new OutputStreamWriter(mSocket.getOutputStream()), true);
                if (mSendData != null && !isClose) {
                    Log.i(TAG, "send == " + mSendData);
                    if (sendDataTmp(mSendData)) {
                        mSendData = null;
                    }
                }
                while (!isClose()) {
                    receiveData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        close();
    }

    /**
     * 接受TCP数据
     * @throws IOException
     *
     */
    private void receiveData() throws IOException {
        Log.d(TAG, "receiveData() start");
        if (mSocket != null && mSocket.isConnected()) {
            mReceiveData = readData();
            if (mReceiveData.length() > 0) {
                Log.d(TAG, "receiveData()成功 :" + mReceiveData);
                onReciveData.onReciveData(E_TYPE_TCP.OK, mReceiveData);
            }
        } else {
            Log.d(TAG, "receiveData()中socket为空或断开连接");
            onReciveData.onReciveData(E_TYPE_TCP.ERROR, null);
        }
    }

    private String readData() throws IOException {
        char[] buf = new char[1024];
        Log.d(TAG, "readData");
        int len = mReader.read(buf);
        if (len > 0) {
            return new String(buf, 0, len);
        }
        return "";
    }

    /**
     * 关闭Tcp连接线程线程
     */
    public synchronized void close() {
        if (null != mSocket)
        {
            try {
                mSocket.shutdownOutput();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                mSocket.shutdownInput();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (null != mReader)
        {
            try {
                mReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null != mWriter)
        {
            try {
                mWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (null != mSocket)
        {
            try {
                mSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        isClose = true;
    }

    /**
     *
     * 当收到camera的数据后回调接口
     */
    public interface OnReciveDataListener {
        public void onReciveData(E_TYPE_TCP result, String jsondata);
    }
}
