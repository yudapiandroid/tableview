package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;

import com.suse.dapi.tableview.core.R;
import com.suse.dapi.tableview.core.view.interfaces.BaseTableViewInterface;
import com.suse.dapi.tableview.core.view.interfaces.DrawLayer;
import com.suse.dapi.tableview.core.view.interfaces.TableViewInterface;

import java.util.List;

/**
 * Created by YuXin on 2018/4/25.
 */
public class CombainTableView extends FrameLayout implements BaseTableViewInterface{

    private TableBgView bgView;
    private HScrollTableView hScrollTableView;
    private TableViewInterface tableViewI;
    private View tableView;

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
        boolean cellFix = arr.getBoolean(R.styleable.CombainTableView_c_use_cell_fix,true);
        boolean useOpengl = arr.getBoolean(R.styleable.CombainTableView_c_use_open_gl,false);
        arr.recycle();

        // 初始化控件
        if(cellFix){
            bgView = new TableBgView(bgColor,borderColor,borderWidth,cellWidth,cellHeight,context,cellFix);
        }else{
            bgView = new TableBgView(context,bgColor,borderColor,borderWidth,row,column,cellFix);
        }

        if(bgView != null){
            FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            bgView.setLayoutParams(params);
            addView(bgView);
        }

        FrameLayout.LayoutParams hsParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hScrollTableView = new HScrollTableView(context);
        hScrollTableView.setLayoutParams(hsParams);
        hScrollTableView.setHorizontalScrollBarEnabled(false);
        addView(hScrollTableView);

        tableView = useOpengl ? new TableSurfaceView(context) : new TableView(context);
        tableViewI = (TableViewInterface) tableView;
        hScrollTableView.setxChangeListener(tableViewI);
        tableViewI.setScrollHandler(hScrollTableView);
        tableViewI.setCellInfo(bgView);

        hScrollTableView.addView(tableView);
        //HScrollTableView.LayoutParams tableParam = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }// end m

    public CombainTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }

    @Override
    public void addDrawLayer(DrawLayer layer) {
        if(tableView != null){
            tableViewI.addDrawLayer(layer);
        }
    }

    @Override
    public void setData(List<Object> data) {
        if(tableView != null){
            tableViewI.setData(data);
        }
    }

    @Override
    public void notifyDataSetChange() {
        if(tableView != null){
            tableViewI.notifyDataSetChange();
        }
    }

}
