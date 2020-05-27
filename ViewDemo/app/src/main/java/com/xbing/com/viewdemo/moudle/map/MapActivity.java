package com.xbing.com.viewdemo.moudle.map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.xbing.com.viewdemo.R;

/**
 * Created by bing.zhao on 2017/8/22.
 */

public class MapActivity extends Activity implements View.OnClickListener{

    public MapView mMapView;
    private BaiduMap mMap;
    public LocationClient mLocationClient = null;

    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现

        setContentView(R.layout.activity_map);
        findViewById(R.id.btn_start_location).setOnClickListener(this);
        mMapView = (MapView)findViewById(R.id.mv_map);
        mMap = mMapView.getMap();
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        mMapView.setVisibility(View.VISIBLE);
        mMapView.onResume();
        super.onResume();
    }
    @Override
    protected void onPause() {
        mMapView.setVisibility(View.INVISIBLE);
        mMapView.onPause();
        super.onPause();
    }

    private void initLocation(){

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死


        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_location:
                mLocationClient.start();
                break;
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            LatLng cenpt = new LatLng(location.getLatitude(),location.getLongitude());
            //定义地图状态
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(cenpt)
                    .zoom(18)
                    .build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            //改变地图状态
            mMap.setMapStatus(mMapStatusUpdate);
            //获取定位结果
            Log.i(TAG,"getTime:"+
            location.getTime()+":LocationID:" +   //获取定位时间
            location.getLocationID()+",LocType:" +    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType()+",Latitude:" +    //获取定位类型
            location.getLatitude()+",Longitude:" +    //获取纬度信息
            location.getLongitude()+",Radius:" +    //获取经度信息
            location.getRadius()+",AddrStr:" +   //获取定位精准度
            location.getAddrStr()+",Country:" +    //获取地址信息
            location.getCountry()+",CountryCode:" +    //获取国家信息
            location.getCountryCode()+",City:" +    //获取国家码
            location.getCity()+",CityCode:" +   //获取城市信息
            location.getCityCode()+",District:" +    //获取城市码
            location.getDistrict()+",Street:" +   //获取区县信息
            location.getStreet()+",StreetNumber:" +   //获取街道信息
            location.getStreetNumber()+",LocationDescribe:" +    //获取街道码
            location.getLocationDescribe()+",PoiList:" +    //获取当前位置描述信息
            location.getPoiList()+",BuildingID:" +   //获取当前位置周边POI信息

            location.getBuildingID()+",BuildingName:" +   //室内精准定位下，获取楼宇ID
            location.getBuildingName()+",Floor:" +    //室内精准定位下，获取楼宇名称
            location.getFloor());    //室内精准定位下，获取当前位置所处的楼层信息
            Log.i(TAG,"");
            if (location.getLocType() == BDLocation.TypeGpsLocation){

                //当前为GPS定位结果，可获取以下信息
                location.getSpeed();    //获取当前速度，单位：公里每小时
                location.getSatelliteNumber();    //获取当前卫星数
                location.getAltitude();    //获取海拔高度信息，单位米
                location.getDirection();    //获取方向信息，单位度
                Log.i(TAG,"ocation.getAltitude():" + location.getAltitude());
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                //当前为网络定位结果，可获取以下信息
                location.getOperators();    //获取运营商信息
                Log.i(TAG,"location.getOperators():"+ location.getOperators());
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                //当前为网络定位结果
                Log.i(TAG,"当前为网络定位结果");
            } else if (location.getLocType() == BDLocation.TypeServerError) {

                //当前网络定位失败
                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
                Log.i(TAG,"可将定位唯一ID、IMEI、定位失败时间");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                //当前网络不通
                Log.i(TAG,"当前网络不通");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                //可进一步参考onLocDiagnosticMessage中的错误返回码
                Log.i(TAG,"当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限");
            }
        }

        /**
         * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
         * 自动回调，相同的diagnosticType只会回调一次
         *
         * @param locType           当前定位类型
         * @param diagnosticType    诊断类型（1~9）
         * @param diagnosticMessage 具体的诊断信息释义
         */
        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {

            if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {
                Log.i(TAG,"//建议打开GPS");

            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

                //建议打开wifi，不必连接，这样有助于提高网络定位精度！
                Log.i(TAG,"建议打开wifi，不必连接，这样有助于提高网络定位精度！");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

                //定位权限受限，建议提示用户授予APP定位权限！
                Log.i(TAG,"定位权限受限，建议提示用户授予APP定位权限！");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

                //网络异常造成定位失败，建议用户确认网络状态是否异常！
                Log.i(TAG,"网络异常造成定位失败，建议用户确认网络状态是否异常！");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

                //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
                Log.i(TAG,"手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

                //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
                Log.i(TAG,"无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {

                //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
                Log.i(TAG,"无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {

                //百度定位服务端定位失败
                //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
                Log.i(TAG,"百度定位服务端定位失败");
            } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {

                Log.i(TAG,"法获取有效定位依据，但无法确定具体原因");//无法获取有效定位依据，但无法确定具体原因
                //建议检查是否有安全软件屏蔽相关定位权限
                //或调用LocationClient.restart()重新启动后重试！

            }
        }

    }
    public static final String TAG = MapActivity.class.getSimpleName();
}
