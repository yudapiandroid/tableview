package com.suse.dapi.tableview.entrys;

/**
 *
 *
 * Created by YuXin on 2018/4/27.
 *
 *
 * 一个格子画四个的模型
 *
 */
public class FourPerCellModel {

    /**
     *
     * 画左上
     *
     */
    private boolean drawLT;
    private int colorLT;

    /**
     * 右上
     */
    private boolean drawRT;
    private int colorRT;

    /**
     *
     * 左下
     *
     */
    private boolean drawLB;
    private int colorLB;

    /**
     *
     * 右下
     *
     */
    private boolean drawRB;
    private int colorRB;

    public boolean isDrawLT() {
        return drawLT;
    }

    public void setDrawLT(boolean drawLT) {
        this.drawLT = drawLT;
    }

    public int getColorLT() {
        return colorLT;
    }

    public void setColorLT(int colorLT) {
        this.colorLT = colorLT;
    }

    public boolean isDrawRT() {
        return drawRT;
    }

    public void setDrawRT(boolean drawRT) {
        this.drawRT = drawRT;
    }

    public int getColorRT() {
        return colorRT;
    }

    public void setColorRT(int colorRT) {
        this.colorRT = colorRT;
    }

    public boolean isDrawLB() {
        return drawLB;
    }

    public void setDrawLB(boolean drawLB) {
        this.drawLB = drawLB;
    }

    public int getColorLB() {
        return colorLB;
    }

    public void setColorLB(int colorLB) {
        this.colorLB = colorLB;
    }

    public boolean isDrawRB() {
        return drawRB;
    }

    public void setDrawRB(boolean drawRB) {
        this.drawRB = drawRB;
    }

    public int getColorRB() {
        return colorRB;
    }

    public void setColorRB(int colorRB) {
        this.colorRB = colorRB;
    }

    @Override
    public String toString() {
        return "FourPerCellModel{" +
                "drawLT=" + drawLT +
                ", colorLT=" + colorLT +
                ", drawRT=" + drawRT +
                ", colorRT=" + colorRT +
                ", drawLB=" + drawLB +
                ", colorLB=" + colorLB +
                ", drawRB=" + drawRB +
                ", colorRB=" + colorRB +
                '}';
    }
}
