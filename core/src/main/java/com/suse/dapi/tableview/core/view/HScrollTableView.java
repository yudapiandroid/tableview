package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.suse.dapi.tableview.core.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */
public class HScrollTableView extends HorizontalScrollView implements TableViewHand{

    private TableView tableView;

    public HScrollTableView(Context context) {
        super(context);
        initTableView(context);
    }

    private void initTableView(Context context) {
        tableView = new TableView(context);
        addView(tableView);
    }

    public HScrollTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTableView(context,attrs);
    }

    private void initTableView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HScrollTableView);
        int pWidth = (int) array.getDimension(R.styleable.HScrollTableView_h_pWidth,50);
        int pHeight = (int) array.getDimension(R.styleable.HScrollTableView_h_pHeight,50);
        int borderWidth = (int) array.getDimension(R.styleable.HScrollTableView_h_borderWidth,1);
        int row = array.getInt(R.styleable.HScrollTableView_h_row,1);
        int borderColor = array.getColor(R.styleable.HScrollTableView_h_borderColor, Color.WHITE);
        int bgColor = array.getColor(R.styleable.HScrollTableView_h_bgColor,Color.WHITE);
        array.recycle();
        tableView = new TableView(context,pWidth,pHeight,borderWidth,bgColor,borderColor,row);
        addView(tableView);
    }


    public HScrollTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTableView(context,attrs);
    }


    @Override
    public int getCount() {
        return tableView != null ? tableView.getCount() : 0;
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
        notifyDataSetChange();
    }

    @Override
    public void notifyDataSetChange() {
        if(tableView != null){
            tableView.notifyDataSetChange();
            tableView.post(new Runnable() {
                @Override
                public void run() {
                    smoothScrollTo(tableView.getMeasuredWidth(),0);
                }
            });
        }
    }

}
