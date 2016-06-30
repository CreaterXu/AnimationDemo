package com.sky.animationdemo.proprityAnimation;

import android.support.annotation.FloatRange;
import android.view.View;

/**
 * Created by Administrator on 2016/6/28.
 * 一些属性的set，get包装类
 */
public class PropertySetGet {
    private View mview;
    public PropertySetGet(View view){
            this.mview=view;
    }
    /**
     * 设置宽度
     * @param width
     * */
    public void  setWidth(int width){
        mview.getLayoutParams().width=width;
        mview.requestLayout();
    }
    /**
     * 获取宽度
     * @return int
     * */
    public int  getWidth(){
        return mview.getLayoutParams().width;
    }
    /**
     * 设置透明度
     * @param alpha
     * */
    public void setAlpha(@FloatRange(from=0.0, to=1.0)float alpha){
       mview.setAlpha(alpha);
    }
    /**
     * 获取透明度
     * @return alpha
     * */
    public float getAlpha(){
        return mview.getAlpha();
    }

}
