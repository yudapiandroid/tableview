package com.suse.dapi.tableview.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.chillingvan.canvasgl.ICanvasGL;
import com.chillingvan.canvasgl.glcanvas.GLPaint;
import com.suse.dapi.tableview.core.view.DrawLayer;
import com.suse.dapi.tableview.entrys.FourPerCellModel;

/**
 * Created by YuXin on 2018/4/27.
 */

public class RightBottomLayer implements DrawLayer {

    private Paint paint;
    private int padding = 1;
    private GLPaint glPaint;


    public RightBottomLayer() {
        this.paint = new Paint();
        paint.setAntiAlias(true);
        glPaint = new GLPaint();
    }

    @Override
    public void draw(Object data, Rect rect, Canvas canvas) {

        if(data == null){
            return;
        }

        if(data instanceof FourPerCellModel){
            FourPerCellModel m = (FourPerCellModel) data;
            drawCell(m,rect,canvas);
        }

    }

    @Override
    public void draw(Object data, Rect rect, ICanvasGL gl) {
        if(data == null){
            return;
        }

        if(data instanceof FourPerCellModel){
            FourPerCellModel m = (FourPerCellModel) data;
            //drawCell(m,rect,gl);
        }
    }

    private void drawCell(FourPerCellModel m, Rect rect, ICanvasGL canvas) {

        int width = rect.width() - padding * 2;
        int height = rect.height() - padding * 2;

        if(m.isDrawLT()){
            int startX = rect.left + padding + width / 2;
            int startY = rect.top + padding;
            int endX = rect.left + padding;
            int endY = rect.top + padding + height / 2;
            paint.setColor(m.getColorLT());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(startX,startY,endX,endY,glPaint);
        }

        if(m.isDrawRT()){
            int startX = rect.left + padding + width;
            int startY = rect.top + padding;
            int endX = rect.left + padding + width / 2;
            int endY = rect.top + padding + height / 2;
            paint.setColor(m.getColorRT());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(startX,startY,endX,endY,glPaint);
        }

        if(m.isDrawLB()){
            int startX = rect.left + padding + width / 2;
            int startY = rect.top + padding + height / 2;
            int endX = rect.left + padding;
            int endY = rect.top + padding + height;
            paint.setColor(m.getColorLB());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(startX,startY,endX,endY,glPaint);
        }

        if(m.isDrawRB()){
            int startX = rect.left + padding + width;
            int startY = rect.top + padding + height / 2;
            int endX = rect.left + padding + width / 2;
            int endY = rect.top + padding + height;
            paint.setColor(m.getColorRB());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(startX,startY,endX,endY,glPaint);
        }

    }

    private void drawCell(FourPerCellModel m, Rect rect, Canvas canvas) {

        int width = rect.width() - padding * 2;
        int height = rect.height() - padding * 2;

        if(m.isDrawLT()){
            int startX = rect.left + padding + width / 2;
            int startY = rect.top + padding;
            int endX = rect.left + padding;
            int endY = rect.top + padding + height / 2;
            paint.setColor(m.getColorLT());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(startX,startY,endX,endY,paint);
        }

        if(m.isDrawRT()){
            int startX = rect.left + padding + width;
            int startY = rect.top + padding;
            int endX = rect.left + padding + width / 2;
            int endY = rect.top + padding + height / 2;
            paint.setColor(m.getColorRT());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(startX,startY,endX,endY,paint);
        }

        if(m.isDrawLB()){
            int startX = rect.left + padding + width / 2;
            int startY = rect.top + padding + height / 2;
            int endX = rect.left + padding;
            int endY = rect.top + padding + height;
            paint.setColor(m.getColorLB());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(startX,startY,endX,endY,paint);
        }

        if(m.isDrawRB()){
            int startX = rect.left + padding + width;
            int startY = rect.top + padding + height / 2;
            int endX = rect.left + padding + width / 2;
            int endY = rect.top + padding + height;
            paint.setColor(m.getColorRB());
            paint.setStrokeWidth(1);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawLine(startX,startY,endX,endY,paint);
        }

    }


}
