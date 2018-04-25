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


/**
 *
 *
 *
 * Created by YuXin on 2018/4/25.
 *
 *
 */
public class TableBgView extends View implements CellAware {

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

    private Rect[][] cells;

    private static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;
    private static final int DEFAULT_BORDER_WIDTH = Color.WHITE;


    public TableBgView(Context context, int bgColor, int borderColor, int borderWidth, int row, int column) {
        super(context);
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.row = row;
        this.column = column;
        init(context);
    }

    public TableBgView(int bgColor, int borderColor, int borderWidth, int cellWidth, int cellHeight,Context context) {
        super(context);
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
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

        if(row > 0 && column > 0){
            int borderW = (row - 1) * borderWidth;
            int borderH = (column - 1) * borderWidth;
            cellWidth = (width - borderW) / column;
            cellHeight = (height - borderH) / row;
        } else {
            row = height / cellHeight;
            column = width / cellWidth;
        }

        initCells(cellWidth,cellHeight,row,column);
    } // end m

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(bgColor);
        if((row <= 0 || column <= 0) && (cellWidth <= 0 || cellHeight <= 0)){
            return;
        }
        drawByRowColumn(row,column,canvas);
    } // end m



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
            int y = cellHeight * i + borderWidth * i;
            canvas.drawLine(0,y,getMeasuredWidth(),y,borderPaint);
        }

        // 绘制竖线
        for (int i=1; i < column;i++){
            int x = cellWidth * i + borderWidth * i;
            canvas.drawLine(x,0,x,getMeasuredHeight(),borderPaint);
        }
    }


    /**
     *
     * 初始化 cell 的边界
     *
     */
    private void initCells(int cellW, int cellH, int row, int column) {
        cells = new Rect[row][column];
        for(int i = 0;i < row;i++){
            for (int j = 0;j < column;j++){
                Rect temp = new Rect();
                temp.left = column * borderWidth + cellW * column;
                temp.right = temp.left + cellW;
                temp.top = row * (borderWidth + cellH);
                temp.bottom = temp.top + cellH;
                cells[i][j] = temp;
            }
        }
    } // end m


    @Override
    public Rect[][] loadCells() {
        return cells;
    }

    @Override
    public Rect getRectByRowWithColumn(int row, int column) {
        Rect rect = new Rect();
        rect.left = (borderWidth + cellWidth) * column;
        rect.right = rect.left + cellWidth;
        rect.top = (borderWidth + cellHeight) * row;
        rect.bottom = rect.top + cellHeight;
        return rect;
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

}
