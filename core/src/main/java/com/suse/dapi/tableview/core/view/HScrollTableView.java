package com.suse.dapi.tableview.core.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 *
 *  Created by Administrator on 2018/4/25.
 *
 */
public class HScrollTableView extends HorizontalScrollView implements ScrollHandler{


    private ScrollXChangeListener xChangeListener;

    public HScrollTableView(Context context) {
        super(context);
        initTouchListener();
    }

    public HScrollTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTouchListener();
    }


    public HScrollTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTouchListener();
    }

    private void initTouchListener() {
        setOnTouchListener(new OnTouchListener() {
            private int lastX = 0;
            private int touchEventId = -9983761;

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;

                    if (msg.what == touchEventId) {
                        if (lastX == scroller.getScrollX()) {
                            //停止了，此处你的操作业务
                            if(xChangeListener != null){
                                xChangeListener.scrollFinish();
                            }
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 1);
                            lastX = scroller.getScrollX();
                            if(xChangeListener != null){
                                xChangeListener.xOffsetChange(lastX);
                            }
                        }
                    }
                }
            };


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int eventAction = event.getAction();
                int y = (int) event.getRawY();
                switch (eventAction) {
                    case MotionEvent.ACTION_UP:
                        handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void scrollTo(int offsetX) {
        smoothScrollTo(offsetX,0);
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
