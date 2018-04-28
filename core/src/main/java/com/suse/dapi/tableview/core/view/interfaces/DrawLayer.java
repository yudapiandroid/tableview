package com.suse.dapi.tableview.core.view.interfaces;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.chillingvan.canvasgl.ICanvasGL;


/**
 *
 *
 * Created by Administrator on 2018/4/25.
 *
 *
 */
public interface DrawLayer {

    /**
     *
     * 一个是支持 原生Canvas的 一个是支持 Opengl的
     *
     * @param data
     * @param rect
     * @param canvas
     * @param gl
     */
    void draw(Object data, Rect rect,Canvas canvas, ICanvasGL gl);

}
