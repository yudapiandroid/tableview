package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.suse.dapi.tableview.core.R;

import java.util.List;

/**
 *
 *  Created by Administrator on 2018/4/25.
 *
 */
public class HScrollTableView extends HorizontalScrollView implements ScrollHandler{


    private ScrollXChangeListener xChangeListener;

    public HScrollTableView(Context context) {
        super(context);
    }

    public HScrollTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public HScrollTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void scrollTo(int offsetX) {
        scrollTo(offsetX,0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_CANCEL){
            if(xChangeListener != null){
                xChangeListener.scrollFinish();
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(xChangeListener != null){
            xChangeListener.xOffsetChange(l);
        }
    }


    public void setxChangeListener(ScrollXChangeListener xChangeListener) {
        this.xChangeListener = xChangeListener;
    }
}
