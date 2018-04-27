package com.suse.dapi.tableview.core.view;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.chillingvan.canvasgl.ICanvasGL;

import java.util.List;

/**
 *
 *
 * Created by Administrator on 2018/4/25.
 *
 *
 */
public interface DrawLayer {

    void draw(Object data,Rect rect, Canvas canvas);

    void draw(Object data, Rect rect, ICanvasGL gl);

}
