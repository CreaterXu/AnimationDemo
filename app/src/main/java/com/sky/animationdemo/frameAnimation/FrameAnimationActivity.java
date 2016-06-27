package com.sky.animationdemo.frameAnimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.sky.animationdemo.R;

public class FrameAnimationActivity extends AppCompatActivity implements View.OnClickListener{
    AnimationDrawable animationDrawable;
    private ImageView imageView;
    private Button okButton;
    private Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView=(ImageView)findViewById(R.id.showImageView);
        okButton=(Button)findViewById(R.id.sports);
        cancelButton=(Button)findViewById(R.id.stop);
        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        initAnimation(imageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.sports){
            //start() 方法不能在 Activity 的 onCreate(Bundle savedInstanceState) 方法中执行，
            // 这是因为动画还没有完全的填充到窗口上。如果你想立即执行动画而不需要和用户进行交互，那么你可以在 Activity 的
            // onWindowFocusChanged() 方法中来调用 start() 方法。这样当你的应用在获得窗口焦点的时候 start() 就会马上被调用。
            animationDrawable.start();
        }else if(v.getId()==R.id.stop){
            animationDrawable.stop();
        }
    }
    /**
     * 初始化动画
     * @param imageView
     * */
    public void initAnimation(ImageView imageView){
        imageView.setBackgroundResource(R.drawable.drawable_animation);
        animationDrawable=(AnimationDrawable)imageView.getBackground();
    }
}
