package com.xbing.com.viewdemo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

/**
 * Created by zhaobing on 2016/6/21.
 */
public class AnimatorActivity extends Activity {

    TextView mView;
    RadioButton mSelect;
    ImageView mImageView;

    public int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animator_activity);
        mView = (TextView) findViewById(R.id.tv_animator);
        mSelect = (RadioButton)findViewById(R.id.rb_select);
        mImageView = (ImageView) findViewById(R.id.iv_power) ;
        Animation powerAnimator = AnimationUtils.loadAnimation(this,R.anim.power_anim);

        findViewById(R.id.btn_bottom_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.startAnimation(powerAnimator);
            }
        });

        findViewById(R.id.btn_bottom_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageView.clearAnimation();
            }
        });

        findViewById(R.id.btn_move).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = new ObjectAnimator();
                switch (index%7){
                    case 0:
                        animator = ObjectAnimator.ofFloat(mView,"alpha",1f,0f,1f);
                        break;
                    case 1:
                        animator = ObjectAnimator.ofFloat(mView,"translationX",0,100,0);
                        break;
                    case 2:
                        animator = ObjectAnimator.ofFloat(mView,"rotation",0,180,0);
                        break;
                    case 3:
                        animator = ObjectAnimator.ofFloat(mView,"rotationY",0,180,0);
                        break;
                    case 4:
                        animator = ObjectAnimator.ofFloat(mView,"translationY",0,100,0);
                        break;
                    case 5:
                        animator = ObjectAnimator.ofFloat(mView,"scaleY",0,3,1);
                        break;
                    case 6:
                        animator = ObjectAnimator.ofFloat(mView,"alpha",1f,0f,1f);
                        ValueAnimator animator1 = ObjectAnimator.ofFloat(mView,"rotation",0,180,0);
                        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mView,"scaleY",0,3,1);
                        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mView,"scaleX",0,3,1);

                        AnimatorSet animSet = new AnimatorSet();
                        animSet.play(animator1).with(animator).with(animator2).with(animator3);
                        animSet.setDuration(2000);
                        animSet.start();
                        break;
                }

                if(index%7<6){
                    animator.setDuration(1000);
                    animator.start();
                }

                if(!mSelect.isChecked()){
                    index++;
                }
            }
        });
    }
}
