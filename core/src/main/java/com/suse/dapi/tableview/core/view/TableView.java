package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/4/24.
 */
public class TableView extends View implements TableViewHand,ScrollXChangeListener,TableViewUI{

    private List<DrawLayer> layers = new ArrayList<>();
    private List<Object> data;
    private CellAware cellAware;
    private ScrollHandler scrollHandler;

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
        if(cellAware != null && data != null){
            int column = data.size() % cellAware.getRow() == 0 ? data.size() / cellAware.getRow() : data.size() / cellAware.getRow() + 1;
            width = (cellAware.getCellWidth() + cellAware.getBorderWidth()) * column;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(cellAware == null || data == null){
            return;
        }
        int column = cellAware.getColumn();
        int row = cellAware.getRow();
        int allColumn = data.size() % row == 0 ? data.size() / row : data.size() / row + 1;
        int startColumn = getStartColumnByOffsetX(offsetX,cellAware.getCellWidth());
        int endColumn = startColumn + column + column / 4;
        endColumn = endColumn > allColumn ? allColumn : endColumn;

        for (int i = startColumn; i < endColumn; i++){
            for (int j = 0; j < row; j++){
                int index = i * row + j;
                if(index < data.size()){
                    drawByCell(j ,i ,cellAware.getRectByRowWithColumn(j,i),
                            index >= data.size() ? null : data.get(index),canvas);
                }
            }
        }
    } // end m

    /**
     *
     * 返回离offset最近的一个 column
     *
     * @param offsetX
     * @return
     *
     */
    private int getStartColumnByOffsetX(int offsetX, int cellWidth) {
        if(cellAware == null){
            return 0;
        }
        int number = offsetX / (cellWidth + cellAware.getBorderWidth());
        return number <= 0 ? 0 : number - 1;
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
        if(offsetX > 0 && cellAware != null){
            int column = offsetX / (cellAware.getBorderWidth() + cellAware.getCellWidth());
            column = column < 0 ? 0 : column;
            translatToColumn(column);
        }
    }

    private void translatToColumn(int column) {
        if(scrollHandler != null && cellAware != null){
            int sx = column * (cellAware.getCellWidth() + cellAware.getBorderWidth());
            scrollHandler.scrollTo(sx);
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
    public void setData(List<Object> data) {
        this.data = data;
        notifyDataSetChange();
    }


    @Override
    public void notifyDataSetChange() {
        // requestLayout();
        invalidate();
        post(new Runnable() {
            @Override
            public void run() {
                if(scrollHandler != null){
                    scrollHandler.scrollTo(getMeasuredWidth());
                }
            }
        });
    }// end m


}
