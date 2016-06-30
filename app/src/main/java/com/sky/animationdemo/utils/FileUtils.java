package com.sky.animationdemo.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 文件工具类   主要提供增删改查方法，默认的存储路径为sdcard根目录
 * Created by Creater Xu on 2016/6/28.
 *
 * @version v1.0
 */
public class FileUtils {
    //默认设置  sdcard根目录
    private static final File root = Environment.getExternalStorageDirectory();
    //默认的外部存储的路径
    private static final String mPath = "XuFile";

    public static final int FILE_FORMAT_EXT = 1;
    public static final int FILE_FORMAT_JPG = 2;

    /**
     * 最彻底的保存方法
     *
     * @param data,file,path,name,style data类必须具有tostring()
     */
    public static <O> void save(O data, File file, String path, String name, int style)  {
        if (file == null)
            file = root;
        if (path == null)
            path = mPath;
        if (name == null)
            name = data.toString();
        if (style == 0)
            style = FILE_FORMAT_EXT;
        String stylePath=".txt";
        if (style==FILE_FORMAT_JPG)
            stylePath=".jpg";
        if (!file.exists()) {
            file.mkdir();
        }
        String path1 = file + "/" + path;
        File file1 = new File(path1);
        if (!file1.exists()) {
            file1.mkdir();

        }
        try {
            if (data.getClass() == Class.forName("android.graphics.Bitmap")) {
                String path2 = path1 + "/" + name+stylePath;
                FileOutputStream fileOutputStream=new FileOutputStream(path2);
                ((Bitmap)data).compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                fileOutputStream.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 保存任意对象至一种文件格式style
     * @param data,style
     * */
    public static <O> void save(O data,int style){
        save(data,null,null,null,style);
    }

    /**
     * 获取存储剩余空间
     * */
    @TargetApi(18)
    private static long getFreeSize(){
        String path=Environment.getExternalStorageState();
        StatFs statFs=new StatFs(path);
        return statFs.getAvailableBytes();
    }
}
