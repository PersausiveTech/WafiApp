package com.mobtecnica.wafiapps.customViews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by Jilsha on 26-03-2018.
 */

public class CustomButton extends AppCompatButton {
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(attrs);
    }

    /**
     * set custom font for button
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
