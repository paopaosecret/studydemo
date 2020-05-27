package com.xbing.com.viewdemo.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;

import com.xbing.com.viewdemo.R;

import java.io.IOException;
import java.util.Set;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

//import com.facebook.stetho.common.LogUtil;

/**
 * Created by bing.zhao on 2017/8/14.
 */

public class LifeCycleActivity extends Activity{
    private final static String TAG = LifeCycleActivity.class.getSimpleName() + "-Test";
    private GLSurfaceView videoView;
    private MediaPlayer player;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        videoView = findViewById(R.id.sv_video);
        videoView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                if(player == null){

                    player.setScreenOnWhilePlaying(true);

                    try {
                        player.setDataSource(LifeCycleActivity.this, Uri.parse("https://wos.58cdn.com.cn/QrOnMlKjQWr/phphousecontact/1080663972736184321.mp4"));

                        holder=videoView.getHolder();
                        holder.setFixedSize(360,640);
                        player.prepareAsync();
                        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                player.seekTo(player.getCurrentPosition());
                                player.start();
                                player.setLooping(true);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    player.setDisplay(holder);
                }
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {

            }

            @Override
            public void onDrawFrame(GL10 gl) {

            }
        });
        if(player == null){
            player = new MediaPlayer();
            player.setScreenOnWhilePlaying(true);

            try {
                player.setDataSource(this, Uri.parse("https://wos.58cdn.com.cn/QrOnMlKjQWr/phphousecontact/1080663972736184321.mp4"));

                holder=videoView.getHolder();
                holder.setFixedSize(360,640);
                player.prepareAsync();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.seekTo(player.getCurrentPosition());
                        player.start();
                        player.setLooping(true);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String action = getIntent().getAction();
        Set<String> category = getIntent().getCategories();
        String data = getIntent().getDataString();

        Log.i(TAG, "action:" + action + ",data:" + data);
        for (String c : category) {
            Log.i(TAG, "category:" + c);
        }
        findViewById(R.id.btn_onPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LifeCycleActivity.this, DialogActivity.class));
                player.reset();
                try {
                    player.setDataSource(LifeCycleActivity.this, Uri.parse("https://wos.58cdn.com.cn/QrOnMlKjQWr/phphousecontact/1080663972736184321.mp4"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.seekTo(player.getCurrentPosition());
                player.start();
                player.setLooping(true);
            }
        });

        findViewById(R.id.btn_onStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(LifeCycleActivity.this, MainActivity.class));
                player.pause();
            }
        });
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        } else {
            LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            if(location == null){
                LocationListener listener = new LocationListener() {
                    String tempCityName;

                    public void onLocationChanged(Location location) {
                        Log.i(TAG, "Altitude:" + location.getAltitude() + ",Latitude:" + location.getLatitude() + ",Longitude:" + location.getLongitude());
                    }

                    public void onProviderDisabled(String provider) {

                    }
                    public void onProviderEnabled(String provider) {
                    }
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }
                };
                locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 50,listener);
                locMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30000, 50,listener);
            }else{
                Log.i(TAG, "Altitude:" + location.getAltitude() + ",Latitude:" + location.getLatitude() + ",Longitude:" + location.getLongitude());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            LocationManager locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                Log.i(TAG,"no permission");
                return;
            }
            Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = locMan.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            Log.i(TAG,"Altitude:" + location.getAltitude() + ",Latitude:" + location.getLatitude() + ",Longitude:"  + location.getLongitude());
        }
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }


}
