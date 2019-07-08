package com.mobtecnica.wafiapps.customViews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by Praveen on 3/25/2018.
 */

public class CustomTextView extends AppCompatTextView {
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setTypeface(attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(attrs);
    }

    /**
     * * set custom font for TextView
     * @param attrs
     */
    public void setTypeface(AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface normalTypeface = Utils.getTypefaceLatoRegular(getContext());
        Typeface boldTypeface = Utils.getTypefaceLatoBold(getContext());
        if (textStyle == Typeface.BOLD) {
            super.setTypeface(boldTypeface/*, -1*/);
        } else {
            super.setTypeface(normalTypeface/*, -1*/);
        }
    }

}
