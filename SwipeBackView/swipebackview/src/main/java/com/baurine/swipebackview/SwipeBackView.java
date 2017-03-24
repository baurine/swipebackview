package com.baurine.swipebackview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

public class SwipeBackView extends View {

    public interface SwipeBackListener {
        void onSwipeBack();
    }

    private SwipeBackListener swipeBackListener;

    public void setSwipeBackListener(SwipeBackListener swipeBackListener) {
        this.swipeBackListener = swipeBackListener;
    }

    ///////////////////////////////////////////
    private View rootView;
    private int touchSlop;
    private Scroller scroller;

    private boolean startScroll = false;
    private float lastX;

    public SwipeBackView(Context context) {
        super(context);
        init(context);
    }

    public SwipeBackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        final Activity activity = (Activity) context;
        rootView = activity.getWindow().getDecorView();
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        scroller = new Scroller(context, new DecelerateInterpolator());

        swipeBackListener = new SwipeBackListener() {
            @Override
            public void onSwipeBack() {
                activity.finish();
                activity.overridePendingTransition(
                        R.anim.nothing, R.anim.out_slide_to_right);
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                return isEnabled();
            case MotionEvent.ACTION_MOVE:
                float curX = event.getRawX();
                int delta = (int) (curX - lastX);
                if (delta > touchSlop && !startScroll) {
                    startScroll = true;
                }
                if (startScroll && rootView.getScrollX() - delta <= 0) {
                    rootView.scrollBy(-delta, 0);
                    lastX = curX;
                }
                break;
            case MotionEvent.ACTION_UP:
                startScroll = false;
                if (-rootView.getScrollX() > rootView.getWidth() / 3) {
                    if (swipeBackListener != null) {
                        swipeBackListener.onSwipeBack();
                    }
                } else {
                    scroller.startScroll(rootView.getScrollX(), 0, -rootView.getScrollX(), 0);
                    invalidate();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            rootView.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
