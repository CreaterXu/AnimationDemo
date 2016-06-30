package com.sky.animationdemo.proprityAnimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.sky.animationdemo.R;
import com.sky.animationdemo.viewAnimation.MyInterpolator;

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    private Button okButton;
    private Button cancelButton;

    Animator animator;
    ObjectAnimator objectAnimator;
    ObjectAnimator objectAnimator1;
    AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        okButton = (Button) findViewById(R.id.sports);
        okButton.setOnClickListener(this);
        cancelButton = (Button) findViewById(R.id.stop);
        cancelButton.setOnClickListener(this);

        imageView=(ImageView)findViewById(R.id.showImageView);

        initAnimation(imageView);
        //View;
        //AnimatorSet
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
            //objectAnimator.start();
            animatorSet.start();
        } else if (v.getId() == R.id.stop) {
            //objectAnimator.cancel();
            animatorSet.cancel();
        }
    }
    /**
     * 初始化动画
     * @param imageView
     * */
    private void initAnimation(ImageView imageView){
        PropertySetGet propertySetGet=new PropertySetGet(imageView);
        //xml形式定义动画
        animatorSet=(AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.property_animation);
        objectAnimator=(ObjectAnimator) animatorSet.getChildAnimations().get(0);
        objectAnimator1=(ObjectAnimator)animatorSet.getChildAnimations().get(1);

        animatorSet.setTarget(propertySetGet);
        animatorSet.playTogether(animatorSet.getChildAnimations());
        //animatorSet.playTogether(animatorSet.getChildAnimations());
        //objectAnimator=(ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.property_animation);
        //objectAnimator.setTarget(propertySetGet);

        /*----以Java代码的形式定义动画-------------------------*/
        //ObjectAnimator作用的对象Target View的属性必须提供set，get方法
        //才能被属性动画使用
        //objectAnimator= ObjectAnimator.ofInt(propertySetGet,"width",500).setDuration(5000);
    }
}
