package com.sky.animationdemo.viewAnimation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.sky.animationdemo.R;

public class ViewAnimationActivity extends AppCompatActivity implements View.OnClickListener{
    Animation animation;
    private Button okButton;
    private Button cancelButton;
    private TextView moveTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        okButton = (Button) findViewById(R.id.sports);
        okButton.setOnClickListener(this);
        cancelButton = (Button) findViewById(R.id.stop);
        cancelButton.setOnClickListener(this);
        moveTxt = (TextView) findViewById(R.id.hello_world);
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
        if (v.getId() == R.id.sports) {
            //通过xml形式加载view animation
            animation = AnimationUtils.loadAnimation(this, R.anim.viewanimation);
            //通过代码形式创造动画
            RotateAnimation rotateAnimation = new RotateAnimation(0, 359,moveTxt.getWidth()/2,moveTxt.getHeight()/2);
            rotateAnimation.setDuration(8000);
            rotateAnimation.setInterpolator(new MyInterpolator());
            rotateAnimation.setRepeatCount(-1);

            moveTxt.startAnimation(rotateAnimation);
        } else if (v.getId() == R.id.stop) {
            moveTxt.clearAnimation();
        }

    }
}
