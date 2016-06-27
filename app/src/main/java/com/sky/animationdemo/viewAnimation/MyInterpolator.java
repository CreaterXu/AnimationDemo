package com.sky.animationdemo.viewAnimation;


import android.view.animation.Interpolator;

/**
 * Created by Administrator on 2016/6/23.
 * 自定义插值器类
 *
 */
public class MyInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        //input为动画的执行时间的位置  输出当前时间的速率
        return (float)(Math.cos((input + 1) * Math.PI) / 2.0f) + 0.5f;
    }
}
