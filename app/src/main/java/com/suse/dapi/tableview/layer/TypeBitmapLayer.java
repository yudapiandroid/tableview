package com.suse.dapi.tableview.layer;

import android.content.Context;

import com.suse.dapi.tableview.R;
import com.suse.dapi.tableview.core.prefab.TypeBitmapDrawLayer;
import com.suse.dapi.tableview.entrys.TypeBitmapModel;


/**
 *
 * Created by YuXin on 2018/4/27.
 *
 */
public class TypeBitmapLayer extends TypeBitmapDrawLayer {


    public TypeBitmapLayer(int padding, Context context) {
        super(padding, context);
    }

    @Override
    public int convertObjToResource(Object data) {
        if(data instanceof TypeBitmapModel){
            TypeBitmapModel m = (TypeBitmapModel) data;
            if(m.getCurrentType() == TypeBitmapModel.TYPE_01){
                return R.mipmap.icon_01;
            }
            if(m.getCurrentType() == TypeBitmapModel.TYPE_02){
                return R.mipmap.icon_02;
            }
            if(m.getCurrentType() == TypeBitmapModel.TYPE_03){
                return R.mipmap.icon_03;
            }
        }
        return -1;
    }

}
