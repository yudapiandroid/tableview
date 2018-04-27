package com.suse.dapi.tableview.layer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.DrawLayer;
import com.suse.dapi.tableview.entrys.TypeBitmapModel;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by YuXin on 2018/4/27.
 *
 */
public class TypeBitmapLayer implements DrawLayer {


    /**
     *  type  ==> resource
     */
    private Map<Integer,Integer> bitmapRef;
    private int padding = 0;

    private Map<Integer,Bitmap> bitmapPool = new HashMap<>();
    private Context context;
    private Paint paint;

    public TypeBitmapLayer(Map<Integer, Integer> bitmapRef,Context context) {
        this.bitmapRef = bitmapRef;
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(Object data, Rect rect, Canvas canvas) {
        if(data == null || context == null){
            return;
        }
        if(data instanceof TypeBitmapModel){
            TypeBitmapModel m = (TypeBitmapModel) data;
            drawCell(m,rect,canvas);
        }
    }


    private void drawCell(TypeBitmapModel m, Rect rect, Canvas canvas) {
        if(bitmapRef == null && !bitmapRef.containsKey(Integer.valueOf(m.getCurrentType()))){
            return;
        }
        Integer res = bitmapRef.get(m.getCurrentType());
        if(res == null){
            return;
        }
        if(bitmapPool.get(res) == null){
            // 加载bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
            bitmapPool.put(res,resizeBitmap(bitmap,rect,padding));
        }
        if(bitmapPool.get(res) == null){
            return;
        }
        // 绘制
        canvas.drawBitmap(bitmapPool.get(res),rect.left + padding,rect.top + padding,paint);
    }// end m

    private Bitmap resizeBitmap(Bitmap bitmap, Rect rect, int padding) {
        if(bitmap == null){
            return null;
        }
        int newWidth = rect.right - rect.left - padding * 2;
        int newHeight = rect.bottom - rect.top - padding * 2;
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth * 1.0f / bitmap.getWidth(),newHeight * 1.0f / bitmap.getHeight());
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

}
