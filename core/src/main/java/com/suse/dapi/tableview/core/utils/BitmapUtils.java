package com.suse.dapi.tableview.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;

import java.util.WeakHashMap;

/**
 *
 *
 * Created by YuXin on 2018/4/28.
 *
 *
 * 加载bitmap的时候使用  主要是添加了缓存
 *
 */
public class BitmapUtils {

    private static WeakHashMap<Integer,Bitmap> bitmapPool = new WeakHashMap<>();

    public static Bitmap resizeBitmap(Bitmap bitmap, Rect rect, int padding) {
        if(bitmap == null){
            return null;
        }
        int newWidth = rect.right - rect.left - padding * 2;
        int newHeight = rect.bottom - rect.top - padding * 2;
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth * 1.0f / bitmap.getWidth(),newHeight * 1.0f / bitmap.getHeight());
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


    public static Bitmap loadBitmap(int resourceId, Context context, Rect rect, int pading){
        if(bitmapPool == null){
            bitmapPool = new WeakHashMap<>();
        }
        Integer res = new Integer(resourceId);
        if(bitmapPool.containsKey(res)){
            Bitmap bitmap = bitmapPool.get(res);
            if(bitmap != null){
                return bitmap;
            }
        }
        // 加载bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),res);
        bitmapPool.put(res,resizeBitmap(bitmap,rect,pading));
        bitmap.recycle();
        return bitmapPool.get(res);
    }


    public static void clearCaChe(){
        if(bitmapPool == null){
            return;
        }
        bitmapPool.clear();
    }

}
