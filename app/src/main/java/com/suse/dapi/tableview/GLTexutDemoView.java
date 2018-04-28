package com.suse.dapi.tableview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.chillingvan.canvasgl.ICanvasGL;
import com.chillingvan.canvasgl.glcanvas.GLPaint;
import com.chillingvan.canvasgl.glview.texture.GLTextureView;

/**
 * Created by YuXin on 2018/4/27.
 */

public class GLTexutDemoView extends GLTextureView {

    public GLTexutDemoView(Context context) {
        super(context);
    }

    public GLTexutDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOpaque(false);
    }

    public GLTexutDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOpaque(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onGLDraw(ICanvasGL canvas) {

        //circle
        GLPaint circlePaint = new GLPaint();
        circlePaint.setColor(Color.parseColor("#88FF0000"));
        circlePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(430, 30, 30, circlePaint);

        GLPaint strokeCirclePaint = new GLPaint();
        strokeCirclePaint.setColor(Color.parseColor("#88FF0000"));
        strokeCirclePaint.setLineWidth(4);
        strokeCirclePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(490, 30, 30, strokeCirclePaint);
    }

}
