package com.suse.dapi.tableview.core.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.suse.dapi.tableview.core.R;
import com.suse.dapi.tableview.core.utils.Log;
import com.suse.dapi.tableview.core.view.interfaces.CellInfo;
import com.suse.dapi.tableview.core.view.interfaces.CellInfoChangeLisenter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *
 *
 * Created by YuXin on 2018/4/25.
 *
 *
 */
public class TableBgView extends View implements CellInfo {

    /**
     *
     * 基本的颜色属性
     *
     */
    private int bgColor = DEFAULT_BG_COLOR;
    private int borderColor = DEFAULT_BORDER_COLOR;
    private int borderWidth = DEFAULT_BORDER_WIDTH;

    /**
     *
     * 有row和column 来确定绘制
     *
     */
    private int row = -1;
    private int column = -1;

    /**
     *
     * 由 cell 的宽度和高度来确定绘制
     *
     */
    private int cellWidth = -1;
    private int cellHeight = -1;
    private Paint borderPaint;

    private boolean useFixModel = true;
    private static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;
    private static final int DEFAULT_BORDER_WIDTH = Color.WHITE;


    private List<CellInfoChangeLisenter> lisenters = new ArrayList<>();

    public TableBgView(Context context, int bgColor, int borderColor, int borderWidth, int row, int column,boolean useFixModel) {
        super(context);
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.row = row;
        this.column = column;
        this.useFixModel = useFixModel;
        init(context);
    }

    public TableBgView(int bgColor, int borderColor, int borderWidth, int cellWidth, int cellHeight,Context context,boolean useFixModel) {
        super(context);
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.useFixModel = useFixModel;
        init(context);
    }

    private void init(Context context) {
        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);
    }

    public TableBgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
        init(context);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TableBgView);
        bgColor = array.getColor(R.styleable.TableBgView_bg_color,DEFAULT_BG_COLOR);
        borderColor = array.getColor(R.styleable.TableBgView_border_color,DEFAULT_BORDER_COLOR);
        borderWidth = (int) array.getDimension(R.styleable.TableBgView_border_width,DEFAULT_BORDER_WIDTH);
        cellWidth = (int) array.getDimension(R.styleable.TableBgView_cell_width,cellWidth);
        cellHeight = (int) array.getDimension(R.styleable.TableBgView_cell_height,cellHeight);
        row = array.getInt(R.styleable.TableBgView_row,row);
        column = array.getInt(R.styleable.TableBgView_column,column);
        array.recycle();
    }// end m

    public TableBgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        init(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);

        int oldW = cellWidth;
        int oldH = cellHeight;
        int oldR = row;
        int oldC = column;

        if(!useFixModel){
            int borderW = (row - 1) * borderWidth;
            int borderH = (column - 1) * borderWidth;
            cellWidth = (width - borderW) / column;
            cellHeight = (height - borderH) / row;
            if(cellWidth != oldW || cellHeight != oldH){
                sendCellInfoChange();
            }
        } else {
            row = height / (cellHeight + borderWidth);
            column = width / (cellWidth + borderWidth);
            if(row != oldR || column != oldC){
                sendCellInfoChange();
            }
        }

    } // end m

    private void sendCellInfoChange() {
        if(lisenters != null){
            for (CellInfoChangeLisenter lisenter : lisenters){
                lisenter.onCellInfoChange();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(bgColor);
        if((row <= 0 || column <= 0) && (cellWidth <= 0 || cellHeight <= 0)){
            return;
        }

        if(!useFixModel){
            drawByRowColumn(row,column,canvas);
        }else{
            drawByCellWidthHeight(canvas);
        }
    } // end m


    /**
     *
     * 根据 cell width 和 cell height绘制
     *
     */
    private void drawByCellWidthHeight(Canvas canvas) {
        // 横线
        for (int y = cellHeight;y < getMeasuredHeight();y += cellHeight){
            canvas.drawLine(0,y,getMeasuredWidth(),y,borderPaint);
            y += borderWidth;
        }

        // 竖线
        for (int x = cellWidth;x < getMeasuredWidth();x += cellWidth) {
            canvas.drawLine(x,0,x,getMeasuredHeight(),borderPaint);
            x += borderWidth;
        }

    }


    /**
     *
     * 通过row和column来绘制
     *
     * @param row
     * @param column
     */
    private void drawByRowColumn(int row, int column,Canvas canvas) {

        // 绘制横线
        for (int i = 1;i < row;i++){
            int y = (cellHeight + borderWidth) * i;
            canvas.drawLine(0,y,getMeasuredWidth(),y,borderPaint);
        }

        // 绘制竖线
        for (int i=1; i < column;i++){
            int x = (cellWidth  + borderWidth) * i;
            canvas.drawLine(x,0,x,getMeasuredHeight(),borderPaint);
        }
    }

    @Override
    public int getCellWidth() {
        return cellWidth;
    }

    @Override
    public int getCellHeight() {
        return cellHeight;
    }

    @Override
    public int getBorderWidth() {
        return borderWidth;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }


    public void addCellInfoChangeLisenter(CellInfoChangeLisenter lisenter){
        if(lisenter != null && lisenters != null && !lisenters.contains(lisenter)){
            lisenters.add(lisenter);
        }
    }

}
