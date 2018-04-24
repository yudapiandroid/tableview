package com.suse.dapi.tableview.core.view;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public interface TableViewHand {
    int getCount();
    void addDrawLayer(DrawLayer layer);
    void setData(List<Object> data);
    void notifyDataSetChange();
}
