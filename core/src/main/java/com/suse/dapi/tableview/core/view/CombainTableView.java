package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.suse.dapi.tableview.core.R;

import java.util.List;

/**
 * Created by YuXin on 2018/4/25.
 */
public class CombainTableView extends FrameLayout implements TableViewHand{

    private TableBgView bgView;
    private HScrollTableView hScrollTableView;
    private TableView tableView;

    public CombainTableView(Context context) {
        super(context);
    }

    public CombainTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CombainTableView);
        int bgColor = arr.getColor(R.styleable.CombainTableView_c_bg_color, Color.WHITE);
        int borderColor = arr.getColor(R.styleable.CombainTableView_c_border_color,Color.WHITE);
        int borderWidth = (int) arr.getDimension(R.styleable.CombainTableView_c_border_width,1);
        int row = (int) arr.getInt(R.styleable.CombainTableView_c_row,-1);
        int column = arr.getInt(R.styleable.CombainTableView_c_column,-1);
        int cellWidth = (int) arr.getDimension(R.styleable.CombainTableView_c_cell_width,-1);
        int cellHeight = (int) arr.getDimension(R.styleable.CombainTableView_c_cell_height,-1);
        arr.recycle();
        // 初始化控件
        if(row > 0){
            bgView = new TableBgView(context,bgColor,borderColor,borderWidth,row,column);
        }
        if(cellWidth > 0){
            bgView = new TableBgView(bgColor,borderColor,borderWidth,cellWidth,cellHeight,context);
        }
        if(bgView != null){
            FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bgView.setLayoutParams(params);
            addView(bgView);
        }

        FrameLayout.LayoutParams hsParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hScrollTableView = new HScrollTableView(context);
        hScrollTableView.setLayoutParams(hsParams);
        addView(hScrollTableView);

        tableView = new TableView(context);
        HScrollTableView.LayoutParams tableParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableView.setLayoutParams(tableParams);
        hScrollTableView.addView(tableView);

        hScrollTableView.setxChangeListener(tableView);
        tableView.setScrollHandler(hScrollTableView);
        tableView.setCellAware(bgView);

    }// end m

    public CombainTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }

    @Override
    public void addDrawLayer(DrawLayer layer) {
        if(tableView != null){
            tableView.addDrawLayer(layer);
        }
    }

    @Override
    public void setData(List<Object> data) {
        if(tableView != null){
            tableView.setData(data);
        }
    }

    @Override
    public void notifyDataSetChange() {
        if(tableView != null){
            tableView.notifyDataSetChange();
        }
    }

}
