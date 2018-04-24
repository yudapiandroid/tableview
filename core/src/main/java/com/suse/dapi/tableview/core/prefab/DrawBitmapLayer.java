package com.suse.dapi.tableview.core.prefab;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.suse.dapi.tableview.core.view.DrawLayer;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */
public class DrawBitmapLayer implements DrawLayer {

    private Bitmap bitmap;
    private int padding;
    private Paint paint;
    private Bitmap reSizeBitmap;

    public DrawBitmapLayer(Bitmap bitmap, int padding) {
        this.bitmap = bitmap;
        this.padding = padding < 0 ? 0 : padding;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(List<Rect> cells, List<Object> data, Canvas canvas) {
        if(bitmap == null){
            return;
        }
        for (int i = 0;i < cells.size();i++){
            drawCell(cells.get(i),data.get(i),canvas);
        }
    }// end m

    private void drawCell(Rect rect, Object o, Canvas canvas) {
        Rect newRect = new Rect();
        newRect.left = rect.left + padding;
        newRect.top = rect.top + padding;
        newRect.right = rect.right - padding;
        newRect.bottom = rect.bottom - padding;
        if(reSizeBitmap == null){
            reSize(newRect);
        }
        canvas.drawBitmap(reSizeBitmap,newRect.left,newRect.top,paint);
    }

    private void reSize(Rect newRect) {
        if(bitmap == null){
            return;
        }
        int newWidth = newRect.right - newRect.left;
        int newHeight = newRect.bottom - newRect.top;
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth * 1.0f / bitmap.getWidth(),newHeight * 1.0f / bitmap.getHeight());
        reSizeBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

}
