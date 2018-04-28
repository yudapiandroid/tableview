package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.chillingvan.canvasgl.ICanvasGL;
import com.chillingvan.canvasgl.glview.texture.GLTextureView;
import com.chillingvan.canvasgl.glview.texture.gles.GLThread;
import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.interfaces.CellInfo;
import com.suse.dapi.tableview.core.view.interfaces.DrawLayer;
import com.suse.dapi.tableview.core.view.interfaces.ScrollHandler;
import com.suse.dapi.tableview.core.view.interfaces.TableViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuXin on 2018/4/27.
 */
public class TableSurfaceView extends GLTextureView implements TableViewInterface{

    private List<DrawLayer> layers = new ArrayList<>();
    private List<Object> data;
    private CellInfo cellInfo;
    private ScrollHandler scrollHandler;

    /**
     * 滑动的时候的 偏移量 这个偏移量是相对 原点的
     */
    private int offsetX = 0;
    private DefaulTableViewImpl tableView = new DefaulTableViewImpl(this);

    public TableSurfaceView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setOpaque(false);
    }// end m

    public TableSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    public void notifyDataSetChange() {
        requestLayout();
        post(new Runnable() {
            @Override
            public void run() {
                invalidate(0,0,getMeasuredWidth(),getMeasuredHeight());
                requestRender();
                if(scrollHandler != null){
                    scrollHandler.scrollTo(getMeasuredWidth());
                }
            }
        });
    }// end m

    /**
     *
     * 滑动改变
     *
     * @param xOffSet
     *
     */
    @Override
    public void xOffsetChange(int xOffSet) {
        this.offsetX = xOffSet;
        int left = offsetX;
        int top  = 0;
        int right = offsetX + (cellInfo.getBorderWidth() + cellInfo.getCellWidth()) * cellInfo.getColumn();
        int bottom = getMeasuredHeight();
        invalidate(left,top,right,bottom);
        requestRender();
    }


    @Override
    protected int getRenderMode() {
        return GLThread.RENDERMODE_WHEN_DIRTY;
    }

    public TableSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onGLDraw(ICanvasGL canvas) {
        tableView.onDraw(null,canvas);
    }

    @Override
    public void setScrollHandler(ScrollHandler scrollHandler) {
        this.scrollHandler = scrollHandler;
    }

    @Override
    public ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    @Override
    public List<Object> getData() {
        return data;
    }

    @Override
    public CellInfo getCellInfo() {
        return cellInfo;
    }


    @Override
    public void setMeasuredDimensionX(int width, int height) {
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tableView.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public int getOffsetX() {
        return offsetX;
    }

    /**
     *
     * 滑动结束后需要回滚
     *
     */
    @Override
    public void scrollFinish() {
        tableView.scrollFinish();
    }

    @Override
    public void addDrawLayer(DrawLayer layer) {
        if(layer == null){
            return;
        }
        layers.add(layer);
        notifyDataSetChange();
    } // end m

    @Override
    public List<DrawLayer> getLayers() {
        return layers;
    }


    @Override
    public void setData(List<Object> data) {
        this.data = data;
        notifyDataSetChange();
    }

    @Override
    public void setCellInfo(CellInfo cellInfo) {
        this.cellInfo = cellInfo;
    }

}
