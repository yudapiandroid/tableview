package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.suse.dapi.tableview.core.utils.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/4/24.
 */
public class TableView extends View implements TableViewHand,ScrollXChangeListener{

    private List<DrawLayer> layers = new ArrayList<>();
    private Object[][] data;
    private CellAware cellAware;
    private ScrollHandler scrollHandler;

    /**
     *
     * 滑动的时候的 偏移量 这个偏移量是相对 原点的
     *
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

    public void setCellAware(CellAware cellAware){
        this.cellAware = cellAware;
    }

    public void setScrollHandler(ScrollHandler scrollHandler) {
        this.scrollHandler = scrollHandler;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if(cellAware != null && data != null && data[0] != null){
            int column = data[0].length;
            width = (cellAware.getCellWidth() + cellAware.getBorderWidth()) * column;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(cellAware == null || cellAware.loadCells() == null || data == null){
            return;
        }

        int row = cellAware.loadCells().length;
        int column = cellAware.loadCells()[0].length;
        int cellWidth = cellAware.loadCells()[0][0].width();
        int startColumn = getStartColumnByOffsetX(offsetX,cellWidth);
        for (int i = startColumn;i < startColumn + column + column / 3;i++){
            for (int j = 0;j < row;j++){
                drawByCell(j ,i ,cellAware.getRectByRowWithColumn(j,i),data[j][i],canvas);
            }
        }
    }

    /**
     *
     * 返回离offset最近的一个 column
     *
     * @param offsetX
     * @return
     */
    private int getStartColumnByOffsetX(int offsetX, int cellWidth) {
        return offsetX / cellWidth  - 1 < 0 ? 0 : offsetX / cellWidth - 1;
    }


    private void drawByCell(int row, int column, Rect rect, Object o, Canvas canvas) {
        for (DrawLayer layer : layers){
            layer.draw(o,rect,canvas);
        }
    }


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

    /**
     *
     * 滑动结束后需要回滚
     *
     */
    @Override
    public void scrollFinish() {
        int cellWidth = cellAware.loadCells()[0][0].width();
        if(offsetX % cellWidth == 0){
            // 不需要会馆

        }else{
            translatToColumn(getStartColumnByOffsetX(offsetX,cellWidth));
        }
    }

    private void translatToColumn(int startColumnByOffsetX) {
        if(scrollHandler != null){
            scrollHandler.scrollTo(startColumnByOffsetX);
        }
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
    public void setData(Object[][] data) {
        this.data = data;
        notifyDataSetChange();
    }


    @Override
    public void notifyDataSetChange() {
        requestLayout();
    }// end m


}
