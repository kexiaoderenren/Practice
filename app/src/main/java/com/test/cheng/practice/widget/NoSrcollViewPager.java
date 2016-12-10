package com.test.cheng.practice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.test.cheng.practice.R;

/**
 * The special viewpager which gestures are prohibited
 * 禁止手势ViewPager
 * Created by kexiaoderenren on 2016/12/10.
 */
public class NoSrcollViewPager extends ViewPager {

    private boolean scrollable;

    public NoSrcollViewPager(Context context) {
        this(context, null);
    }

    public NoSrcollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.NoSrcollViewPager, 0, 0);
        scrollable = a.getBoolean(R.styleable.NoSrcollViewPager_scrollable, false);
        a.recycle();
    }


    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!scrollable)
            return false;
        else
            return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollable)
            return false;
        else
            return super.onTouchEvent(ev);
    }

}
