package com.xbing.com.viewdemo.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhaobing on 2016/8/29.
 */
@TargetApi(18)
public class BluetoothActivity extends Activity implements View.OnClickListener{

    BluetoothAdapter mBluetoothAdapter;
    BluetoothManager mBluetoothManager;
    TextView mTVResult;
    private static final int CLOSE_SCAN = 1;
    private StringBuffer mScanBuffer;
    private String mAddress;
    BluetoothGatt mBluetoothGatt;

    private BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
            final int length = characteristic.getValue().length;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("onCharacteristicChanged","onCharacteristicChanged len" + length);
//                    Toast.makeText(BluetoothActivity.this,"onCharacteristicChanged len:"+ length,Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            final BluetoothGattCharacteristic characteristic1 = characteristic;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if ("0000aaa3-0000-1000-8000-00805f9b34fb".equals(characteristic1.getUuid().toString())) {

                        byte[] data = characteristic1.getValue();
                        int step = data[0]&0xff + data[1]<<8;
                        Toast.makeText(BluetoothActivity.this,"data len:" + data.length+",step:"+step ,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(BluetoothActivity.this,"data failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
            final int status1 = status;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (BluetoothGatt.GATT_SUCCESS == status1) {

//                        Log.i("onCharacteristicWrite",characteristic.getDescriptor(UUID.fromString("0x2800")).getUuid().toString());

//                        Log.i("onCharacteristicWrite1",characteristic.getDescriptor(UUID.fromString("00002800-0000-1000-8000-00805f9b34fb")).getUuid().toString());
                        Toast.makeText(BluetoothActivity.this,"onCharacteristicWrite:success",Toast.LENGTH_SHORT).show();
                        //启动采集标志
                        if("0000aaa7-0000-1000-8000-00805f9b34fb".equals(characteristic.getUuid().toString())){

                            BluetoothGattCharacteristic characteristic1 = mBluetoothGatt.getService(UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb")).getCharacteristic(UUID.fromString("0000aaa1-0000-1000-8000-00805f9b34fb"));

                            BluetoothGattDescriptor descriptor = characteristic1.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"));
                            if(characteristic.getValue()[0] == 1){
                                mBluetoothGatt.setCharacteristicNotification(characteristic1,true);
                                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            }else{
                                mBluetoothGatt.setCharacteristicNotification(characteristic1,false);
                                descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
                            }
                            mBluetoothGatt.writeDescriptor(descriptor);
                        }else{

                        }

                    }else{
                        Toast.makeText(BluetoothActivity.this,"onCharacteristicWrite:failed-",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            final int status1 = newState;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String strToast = "";
                    if (BluetoothProfile.STATE_CONNECTED == status1) {
                        strToast = "onCharacteristicRead:STATE_CONNECTED";
                    } else if (BluetoothProfile.STATE_DISCONNECTED == status1) {
                        strToast = "onCharacteristicRead:STATE_DISCONNECTED";
                    }
                    Toast.makeText(BluetoothActivity.this,strToast,Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BluetoothActivity.this,"onDescriptorRead",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, final BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);

            final String uuid = descriptor.getUuid().toString();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if("00002902-0000-1000-8000-00805f9b34fb".equals(uuid)){
                        if(descriptor.getValue()[0] == 1) {
                            Toast.makeText(BluetoothActivity.this, "onDescriptorWrite:open", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(BluetoothActivity.this,"onDescriptorWrite:close",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(BluetoothActivity.this,"onDescriptorWrite:failed",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BluetoothActivity.this,"onMtuChanged",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BluetoothActivity.this,"onReadRemoteRssi",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BluetoothActivity.this,"onReliableWriteCompleted",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BluetoothActivity.this,"onServicesDiscovered",Toast.LENGTH_SHORT).show();
                }
            });
        }
    };


    private BluetoothAdapter.LeScanCallback mCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(TextUtils.isEmpty(mAddress)){
                        mAddress = device.getAddress();
                    }
                    mScanBuffer.append("address:"+device.getAddress()+";name:"+device.getName()+"\n");
                    mTVResult.setText(mScanBuffer.toString());
                }
            });
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == CLOSE_SCAN){
                mBluetoothAdapter.stopLeScan(mCallback);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_activity);

        mTVResult = (TextView)findViewById(R.id.tv_result);
        mScanBuffer = new StringBuffer("");
        findViewById(R.id.btn_open).setOnClickListener(this);
        findViewById(R.id.btn_open_prompt).setOnClickListener(this);
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_scan_device).setOnClickListener(this);
        findViewById(R.id.btn_connect_device).setOnClickListener(this);
        findViewById(R.id.btn_disconnect_device).setOnClickListener(this);
        findViewById(R.id.btn_read_characteristic).setOnClickListener(this);
        findViewById(R.id.btn_find_server).setOnClickListener(this);
        findViewById(R.id.btn_write_characteristic).setOnClickListener(this);
        findViewById(R.id.btn_notify_open).setOnClickListener(this);
        findViewById(R.id.btn_notify_close).setOnClickListener(this);
        findViewById(R.id.btn_get_state).setOnClickListener(this);
        mBluetoothManager = (BluetoothManager)getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,"打开成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"打开失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_open){
            openBluetooth();
        }else if(v.getId() == R.id.btn_close){
            closeBluetooth();
        }else if(v.getId() == R.id.btn_open_prompt){
            openBluetoothPrompt();
        }else if(v.getId() == R.id.btn_scan_device){
            scanBluetoothdevice();
        }else if(v.getId() == R.id.btn_connect_device){
            connectDevice();
        }else if(v.getId() == R.id.btn_disconnect_device){
            disconnectDevice();
        }else if(v.getId() == R.id.btn_read_characteristic){
            readCharacteristic();
        }else if(v.getId() == R.id.btn_find_server){
            discoverServer();
        }else if(v.getId() == R.id.btn_write_characteristic){
            writeCharacteristic();
        }else if(v.getId() == R.id.btn_notify_open){
            openBluetoothNotify();
        }else if(v.getId() == R.id.btn_notify_close){
            closeBluetoothNotify();
        }else if(v.getId() == R.id.btn_get_state){
            getConnectionState();
        }
    }

    /**
     * public static final int STATE_CONNECTED
     *
     Added in API level 11
     The profile is in connected state

     Constant Value: 2 (0x00000002)
     public static final int STATE_CONNECTING

     Added in API level 11
     The profile is in connecting state

     Constant Value: 1 (0x00000001)
     public static final int STATE_DISCONNECTED

     Added in API level 11
     The profile is in disconnected state

     Constant Value: 0 (0x00000000)
     public static final int STATE_DISCONNECTING

     Added in API level 11
     The profile is in disconnecting state

     Constant Value: 3 (0x00000003)
     */
    private void getConnectionState() {
        int state = mBluetoothManager.getConnectionState(mBluetoothAdapter.getRemoteDevice(mAddress),BluetoothProfile.GATT);
        Toast.makeText(this,"连接状态为：" + state,Toast.LENGTH_SHORT).show();
    }

    private void closeBluetoothNotify() {
        BluetoothGattCharacteristic characteristic = mBluetoothGatt.getService(UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb")).getCharacteristic(UUID.fromString("0000aaa7-0000-1000-8000-00805f9b34fb"));
        characteristic.setValue(new byte[] {0});
        if(characteristic == null){
            Toast.makeText(this,"没有获取到特征值",Toast.LENGTH_SHORT).show();
        }else{
            mBluetoothGatt.writeCharacteristic(characteristic);
        }
    }

    private void openBluetoothNotify() {

        if(mBluetoothGatt!=null){
            BluetoothGattCharacteristic characteristic = mBluetoothGatt.getService(UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb")).getCharacteristic(UUID.fromString("0000aaa7-0000-1000-8000-00805f9b34fb"));
            characteristic.setValue(new byte[]{1});

            if(characteristic == null){
                Toast.makeText(this,"没有获取到特征值",Toast.LENGTH_SHORT).show();
            }else{
                mBluetoothGatt.writeCharacteristic(characteristic);
            }
        }else{
            Toast.makeText(this,"ble链路不通",Toast.LENGTH_SHORT).show();
        }

    }

    private void writeCharacteristic() {
        if(mBluetoothGatt!=null){

            Calendar calendar = Calendar.getInstance();
            Date now = new Date();
            calendar.setTime(now);
            byte hour = (byte)calendar.get(Calendar.HOUR_OF_DAY);
            byte minute = (byte)calendar.get(Calendar.MINUTE);
            byte second = (byte)calendar.get(Calendar.SECOND);

            BluetoothGattCharacteristic characteristic = mBluetoothGatt.getService(UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb")).getCharacteristic(UUID.fromString("0000aaa5-0000-1000-8000-00805f9b34fb"));
            characteristic.setValue(new byte[]{hour, minute, second});


            if(characteristic == null){
                Toast.makeText(this,"没有获取到特征值",Toast.LENGTH_SHORT).show();
            }else{
                mBluetoothGatt.writeCharacteristic(characteristic);
            }

        }else{
            Toast.makeText(this,"ble链路不通",Toast.LENGTH_SHORT).show();
        }
    }

    private void discoverServer() {
        if(mBluetoothGatt != null){
            mBluetoothGatt.discoverServices();
        }else{
            Toast.makeText(this,"先去扫描并且连接蓝牙",Toast.LENGTH_SHORT).show();
        }
    }

    private void readCharacteristic() {
        if(mBluetoothGatt!=null){
            BluetoothGattCharacteristic characteristic = mBluetoothGatt.getService(UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb")).getCharacteristic(UUID.fromString("0000aaa3-0000-1000-8000-00805f9b34fb"));
            if(characteristic == null){
                Toast.makeText(this,"没有获取到特征值",Toast.LENGTH_SHORT).show();
            }else{
                mBluetoothGatt.readCharacteristic(characteristic);
            }

        }else{
            Toast.makeText(this,"ble链路不通",Toast.LENGTH_SHORT).show();
        }

    }

    private void disconnectDevice() {
        mBluetoothGatt.disconnect();
    }

    private void connectDevice() {
        if(mBluetoothGatt == null){
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mAddress);
            if(device != null){
                mHandler.removeMessages(CLOSE_SCAN);
                mBluetoothAdapter.stopLeScan(mCallback);
                mTVResult.setText("name:"+device.getName());
                mBluetoothGatt = device.connectGatt(this,false,mBluetoothGattCallback);
//            if(mBluetoothGatt.)
            }else{
                Toast.makeText(this,"获取设备失败",Toast.LENGTH_SHORT).show();
            }
        }else{
            mBluetoothGatt.connect();
        }
    }


    private void openBluetoothPrompt() {
        if(mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()){
            Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(bluetoothIntent,100);
        }
    }

    private void scanBluetoothdevice() {
        mScanBuffer.setLength(0);
        mBluetoothAdapter.startLeScan(mCallback);
        mHandler.sendEmptyMessageDelayed(CLOSE_SCAN,5000);
    }

    private void closeBluetooth(){

        if(mBluetoothGatt!=null){
            mBluetoothGatt.close();
        }
        if(mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.stopLeScan(mCallback);
            mBluetoothAdapter.disable();
            Toast.makeText(this,"关闭蓝牙",Toast.LENGTH_SHORT).show();
        }
    }

    private void openBluetooth() {
        if(mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()){
            mBluetoothAdapter.enable();//强制打开蓝牙，不提醒用户
            Toast.makeText(this,"打开蓝牙",Toast.LENGTH_SHORT).show();
        }
    }
}
