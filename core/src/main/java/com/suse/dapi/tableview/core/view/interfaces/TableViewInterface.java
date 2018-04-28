package com.suse.dapi.tableview.core.view.interfaces;

import java.util.List;

/**
 * Created by YuXin on 2018/4/28.
 */

public interface TableViewInterface extends BaseTableViewInterface{

    void xOffsetChange(int xOffSet);
    int getOffsetX();

    void scrollFinish();

    List<DrawLayer> getLayers();

    void setScrollHandler(ScrollHandler scrollHandler);

    ScrollHandler getScrollHandler();

    List<Object> getData();

    CellInfo getCellInfo();

    void setCellInfo(CellInfo cellInfo);

    void setMeasuredDimensionX(int width,int height);


}

