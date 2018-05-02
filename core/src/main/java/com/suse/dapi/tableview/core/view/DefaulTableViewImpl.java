package com.suse.dapi.tableview.core.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import com.chillingvan.canvasgl.ICanvasGL;
import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.interfaces.CellInfo;
import com.suse.dapi.tableview.core.view.interfaces.DrawLayer;
import com.suse.dapi.tableview.core.view.interfaces.TableViewInterface;

import java.util.List;

/**
 * Created by YuXin on 2018/4/28.
 *
 *
 * 主要是为了抽取公共代码
 *
 */
public class DefaulTableViewImpl {

    private TableViewInterface tableView;

    public DefaulTableViewImpl(TableViewInterface tableView) {
        this.tableView = tableView;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        int width = widthMeasureSpec == 0 ? 0 : View.MeasureSpec.getSize(widthMeasureSpec);
        if(tableView != null && tableView.getData() != null && tableView.getCellInfo() != null){
            int size = tableView.getData().size();
            CellInfo cellInfo = tableView.getCellInfo();
            int column = cellInfo.getRow() == 0 ? 0 : size % cellInfo.getRow() == 0 ? size / cellInfo.getRow() : size / cellInfo.getRow() + 1;
            width = (cellInfo.getCellWidth() + cellInfo.getBorderWidth()) * column;
        }
        tableView.setMeasuredDimensionX(width,height);
    }


    protected void onDraw(Canvas canvas, ICanvasGL gl) {

        if(tableView == null){
            return;
        }

        CellInfo cellInfo = tableView.getCellInfo();
        List<Object> data = tableView.getData();

        if(cellInfo == null || data == null){
            return;
        }

        int column = cellInfo.getColumn();
        int row = cellInfo.getRow();
        int allColumn = data.size() % row == 0 ? data.size() / row : data.size() / row + 1;
        int startColumn = getStartColumnByOffsetX(tableView.getOffsetX(),cellInfo.getCellWidth());
        int endColumn = startColumn + column + column / 4;
        endColumn = endColumn > allColumn ? allColumn : endColumn;

        for (int i = startColumn; i < endColumn; i++){
            for (int j = 0; j < row; j++){
                int index = i * row + j;
                if(index < data.size()){
                    drawByCell(j ,i ,getRectByRowWithColumn(cellInfo,j,i),
                            index >= data.size() ? null : data.get(index),canvas,gl);
                }
            }
        }
    } // end m


    public Rect getRectByRowWithColumn(CellInfo cellInfo,int row, int column) {
        Rect rect = new Rect();
        rect.left = (cellInfo.getBorderWidth() + cellInfo.getCellWidth()) * column;
        rect.right = rect.left + cellInfo.getCellWidth();
        rect.top = (cellInfo.getBorderWidth() + cellInfo.getCellHeight()) * row;
        rect.bottom = rect.top + cellInfo.getCellHeight();
        return rect;
    }

    private void drawByCell(int row, int column, Rect rect, Object o, Canvas canvas,ICanvasGL gl) {
        for (DrawLayer layer : tableView.getLayers()){
            layer.draw(o,rect,canvas,gl);
        }
    }


    public void scrollFinish(){
        if(tableView == null || tableView.getCellInfo() == null){
            return;
        }
        if(tableView.getOffsetX() > 0 && tableView.getCellInfo() != null){
            int column = tableView.getOffsetX() / (tableView.getCellInfo().getBorderWidth() + tableView.getCellInfo().getCellWidth());
            column = column < 0 ? 0 : column;
            translatToColumn(column);
        }
    }

    private void translatToColumn(int column) {
        if(tableView != null){
            if(tableView.getScrollHandler() != null && tableView.getCellInfo() != null){
                int sx = column * (tableView.getCellInfo().getCellWidth() + tableView.getCellInfo().getBorderWidth());
                tableView.getScrollHandler().scrollTo(sx);
            }
        }

    }

    /**
     *
     * 返回离offset最近的一个 column
     *
     * @param offsetX
     *
     * @return
     *
     */
    private int getStartColumnByOffsetX(int offsetX, int cellWidth) {
        if(tableView == null || tableView.getCellInfo() == null){
            return 0;
        }
        int number = offsetX / (cellWidth + tableView.getCellInfo().getBorderWidth());
        return number <= 0 ? 0 : number - 1;
    }

}
