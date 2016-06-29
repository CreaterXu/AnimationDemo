package com.sky.animationdemo.proprityAnimation;

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
     * */
    public void  setWidth(int width){
        mview.getLayoutParams().width=width;
        mview.requestLayout();
    }
    /**
     * 获取宽度
     * */
    public int  getWidth(){
        return mview.getLayoutParams().width;
    }
}
