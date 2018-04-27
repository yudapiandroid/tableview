package com.suse.dapi.tableview.entrys;

/**
 * Created by YuXin on 2018/4/27.
 *
 * 左上角 多种bitmap
 *
 */
public class TypeBitmapModel {

    /**
     *
     * 对应不同的bitmap类型
     *
     */
    public static final int TYPE_01 = 0x0011;
    public static final int TYPE_02 = 0x0012;
    public static final int TYPE_03 = 0x0013;

    /**
     *
     * 没有bitmap的类型
     *
     */
    public static final int TYPE_04 = 0x0014;

    /**
     * 当前的类型
     */
    private int currentType = TYPE_04;


    /**
     * 其他数据
     */
    private String name;
    private String number;
    private String xxx;

    public int getCurrentType() {
        return currentType;
    }

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }
}
