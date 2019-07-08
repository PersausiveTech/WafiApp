package com.mobtecnica.wafiapps.customViews;

import android.widget.GridView;

/**
 * Created by SIby on 02-02-2017.
 */


public class MyGridView extends GridView
{
    public MyGridView(android.content.Context context,
                      android.util.AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}