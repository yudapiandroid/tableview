package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24.
 */
public class TableView extends BaseTableView{

    public TableView(Context context) {
        super(context);
    }

    public TableView(Context context, int pWidth, int pHeight, int borderWidth, int bgColor, int borderColor, int row) {
        super(context, pWidth, pHeight, borderWidth, bgColor, borderColor, row);
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setData(List<Object> data) {
        super.setData(data);
        notifyDataSetChange();
    }

    /**
     *
     * 数据改变的时候调用
     *
     */
    @Override
    public void notifyDataSetChange() {
        requestLayout();
    }

}
