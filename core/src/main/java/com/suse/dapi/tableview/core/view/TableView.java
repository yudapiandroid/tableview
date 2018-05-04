package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.interfaces.CellInfo;
import com.suse.dapi.tableview.core.view.interfaces.CellInfoChangeLisenter;
import com.suse.dapi.tableview.core.view.interfaces.DrawLayer;
import com.suse.dapi.tableview.core.view.interfaces.ScrollHandler;
import com.suse.dapi.tableview.core.view.interfaces.TableViewInterface;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/4/24.
 */
public class TableView extends View implements TableViewInterface,CellInfoChangeLisenter{

    private List<DrawLayer> layers = new ArrayList<>();
    private List<Object> data;
    private CellInfo cellInfo;
    private ScrollHandler scrollHandler;

    private DefaulTableViewImpl tableView = new DefaulTableViewImpl(this);

    /**
     * 滑动的时候的 偏移量 这个偏移量是相对 原点的
     */
    private int offsetX = 0;

    public TableView(Context context) {
        super(context);
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    protected void onDraw(Canvas canvas) {
        tableView.onDraw(canvas,null);
    } // end m

    /**
     *
     * 滑动改变
     *
     * @param xOffSet
     */
    @Override
    public void xOffsetChange(int xOffSet) {
        this.offsetX = xOffSet;
        invalidate();
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
    public void notifyDataSetChange() {
        requestLayout();
        post(new Runnable() {
            @Override
            public void run() {
                invalidate();
                if(scrollHandler != null){
                    scrollHandler.scrollTo(getMeasuredWidth());
                    tableView.scrollFinish();
                }
            }
        });
    }// end m

    @Override
    public void setCellInfo(CellInfo cellInfo) {
        this.cellInfo = cellInfo;
    }

    @Override
    public void onCellInfoChange() {
        if(getMeasuredWidth() > 0){
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChange();
            }
        });
    }
}
