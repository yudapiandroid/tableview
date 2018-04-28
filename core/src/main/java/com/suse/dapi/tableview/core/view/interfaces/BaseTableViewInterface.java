package com.suse.dapi.tableview.core.view.interfaces;

import java.util.List;

/**
 * Created by YuXin on 2018/4/28.
 */

public interface BaseTableViewInterface {

    void addDrawLayer(DrawLayer layer);

    void notifyDataSetChange();

    void setData(List<Object> data);

}
