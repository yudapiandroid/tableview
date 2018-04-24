package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.suse.dapi.tableview.core.R;
import com.suse.dapi.tableview.core.utils.DimensUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/24.
 *
 */
public abstract class BaseTableView extends View implements TableViewHand{

    /**
     *
     *  每个表格的宽度和高度
     *
     */
    private int pWidth;
    private int pHeight;

    /**
     * 分割线的宽度
     */
    private int borderWidth;

    /**
     * 背景颜色 和 分割线的颜色
     *
     */
    private int bgColor;
    private int borderColor;

    // 有多少行
    private int row;
    private List<Rect> cells = new ArrayList<>();
    private List<DrawLayer> drawLayers = new ArrayList<>();
    private List<Object> data;

    private Paint bgPaint;
    private Paint borderPaint;

    /**
     *
     * 默认的表格的宽度 和 高度
     *
     */
    private static final int DEFAULT_PER_WIDTH = 40;
    private static final int DEFAULT_PER_HEIGHT = DEFAULT_PER_WIDTH;

    /**
     *
     * 分割线的宽度和背景
     *
     */
    private static final int DEFAULT_BORDER_WIDTH = 1;
    private static final int DEFAULT_BG_COLOR = Color.WHITE;
    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;
    private static final int DEFAULT_ROW = 1;

    public static final String LOG_TAG = "BaseTableView";

    public BaseTableView(Context context) {
        super(context);
        pWidth = DimensUtils.dp2px(context,DEFAULT_PER_WIDTH);
        pHeight = DimensUtils.dp2px(context,DEFAULT_PER_HEIGHT);
        borderWidth = DimensUtils.dp2px(context,DEFAULT_BORDER_WIDTH);
        bgColor = DEFAULT_BG_COLOR;
        borderColor = DEFAULT_BORDER_COLOR;
        row = DEFAULT_ROW;
        init(context);
    }// end m


    public BaseTableView(Context context, int pWidth, int pHeight, int borderWidth, int bgColor, int borderColor, int row) {
        super(context);
        this.pWidth = pWidth;
        this.pHeight = pHeight;
        this.borderWidth = borderWidth;
        this.bgColor = bgColor;
        this.borderColor = borderColor;
        this.row = row;
        init(context);
    }

    private void init(Context context) {
        bgPaint = new Paint();
        bgPaint.setColor(bgColor);
        borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);
    }

    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseTableView);
        pWidth = (int) array.getDimension(R.styleable.BaseTableView_pWidth,DEFAULT_PER_WIDTH);
        pHeight = (int) array.getDimension(R.styleable.BaseTableView_pHeight,DEFAULT_PER_HEIGHT);
        borderWidth = (int) array.getDimension(R.styleable.BaseTableView_borderWidth,DEFAULT_BORDER_WIDTH);
        bgColor = array.getColor(R.styleable.BaseTableView_bgColor,DEFAULT_BG_COLOR);
        borderColor = array.getColor(R.styleable.BaseTableView_borderColor,DEFAULT_BORDER_COLOR);
        row = array.getInt(R.styleable.BaseTableView_row,DEFAULT_ROW);
        array.recycle();
    }

    public BaseTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
        init(context);
    }

    public BaseTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        init(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawBg(canvas);
        for (DrawLayer layer : drawLayers){
            layer.draw(cells,data,canvas);
        }
    }// end m

    @Override
    public void addDrawLayer(DrawLayer layer){
        if(layer == null){
            return;
        }
        drawLayers.add(layer);
    }


    private void drawBg(Canvas canvas) {
        canvas.drawColor(bgColor);
        int count = getCount();
        if(count < 0){
            return;
        }
        int column = count % row == 0 ? count / row : count / row + 1;
        // 竖线
        int minColumn = getWidth() / pWidth;
        int drawColumn = column < minColumn ? minColumn : column;
        for (int i = 0; i < drawColumn;i++){
            int startX = i * pWidth + i * borderWidth;
            canvas.drawLine(startX,0,startX,getHeight(),borderPaint);
        }
        // 横线
        for (int i = 0;i < row ;i++){
            int startY = i * pHeight + i * borderWidth;
            canvas.drawLine(0,startY,getWidth(),startY,borderPaint);
        }
        initCells(row,column);
    }// end m

    private void initCells(int row, int column) {
        cells.clear();
        for (int i = 0;i < getCount();i++){
            int left = i / row * pWidth + i / row * borderWidth + borderWidth / 2;
            int top = i % row * pHeight + i % row *borderWidth + borderWidth / 2;
            int right = left + pWidth;
            int bottom = top + pHeight;
            cells.add(new Rect(left,top,right,bottom));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = row * pHeight + (row - 1) * borderWidth;
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int column = getCount() % row == 0 ? getCount() / row : getCount() / row + 1;
        int calWidth = column * pWidth + column * borderWidth;
        setMeasuredDimension(calWidth < selfWidth ? selfWidth : calWidth,height);
    } // end m


    @Override
    public void setData(List<Object> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }
}
