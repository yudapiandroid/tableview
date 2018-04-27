package com.suse.dapi.tableview.core.view;


import android.graphics.Rect;

/**
 * Created by YuXin on 2018/4/25.
 */

public interface CellAware {

    Rect getRectByRowWithColumn(int row,int column);

    int getCellWidth();

    int getCellHeight();

    int getBorderWidth();


    int getRow();
    int getColumn();

}
