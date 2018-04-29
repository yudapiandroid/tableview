package com.suse.dapi.tableview.core.prefab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.chillingvan.canvasgl.ICanvasGL;
import com.chillingvan.canvasgl.glcanvas.GLPaint;
import com.suse.dapi.tableview.core.utils.BitmapUtils;
import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.interfaces.DrawLayer;

/**
 * Created by YuXin on 2018/4/28.
 *
 * 加载多个bitmap对应的 Layer
 *
 */
public abstract class TypeBitmapDrawLayer implements DrawLayer {

    private int padding;
    private Context context;
    private Paint canvasPaint;

    public TypeBitmapDrawLayer(int padding, Context context) {
        this.padding = padding;
        this.context = context;
        canvasPaint = new Paint();
        canvasPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Object data, Rect rect, Canvas canvas, ICanvasGL gl) {

        if(data == null){
            return;
        }

        int res = convertObjToResource(data);
        if(res < 0){
            return;
        }
        Bitmap bitmap = BitmapUtils.loadBitmap(res, context, rect, padding);
        if(bitmap == null){
            return;
        }

        // 绘制
        if(canvas != null){
            drawByCanvas(bitmap,rect,padding,canvas);
        }
        if(gl != null){
            drawByGL(bitmap,rect,padding,gl);
        }
    }

    private void drawByGL(Bitmap bitmap, Rect rect, int padding, ICanvasGL gl) {
        int left = rect.left + padding;
        int top = rect.top + padding;
        gl.drawBitmap(bitmap,left,top);
    }


    private void drawByCanvas(Bitmap bitmap, Rect rect, int padding, Canvas canvas) {
        int left = rect.left + padding;
        int top = rect.top + padding;
        canvas.drawBitmap(bitmap,left,top,canvasPaint);
    }

    /**
     *
     * 转换出 Obj 对应的 bitmap 资源 如果不存在返回 负数
     *   data ==> res
     *
     * @param data
     * @return
     */
    public abstract int convertObjToResource(Object data);

}
