package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by bing.zhao on 2017/1/9.
 */

public class HelloOpenGlEsActivity extends Activity {

    GLSurfaceView mGlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGlView = new MyGlSurfaceView(this);
        setContentView(mGlView);
    }


    class MyRenderer implements GLSurfaceView.Renderer{

        @Override
        public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

            //设置背景颜色 rgba
            GLES20.glClearColor(1.0f, 0f, 0f, 1.0f);
        }

        @Override
        public void onSurfaceChanged(GL10 gl10, int width, int height) {


            GLES20.glViewport(0, 0, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl10) {

            //重绘背景色
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        }
    }


    class MyGlSurfaceView extends GLSurfaceView{
        public MyGlSurfaceView(Context context){
            super(context);

            try
            {
                // Create an OpenGL ES 2.0 context
                setEGLContextClientVersion(2);

                // Set the Renderer for drawing on the GLSurfaceView
                setRenderer(new MyRenderer());

                // Render the view only when there is a change in the drawing
                // data
                setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

                // 注意上面语句的顺序，反了可能会出错

            }
            catch (Exception e)
            {
                e.printStackTrace();

            }
        }
    }
}
