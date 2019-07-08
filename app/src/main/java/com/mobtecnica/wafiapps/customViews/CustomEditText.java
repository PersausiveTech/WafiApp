package com.mobtecnica.wafiapps.customViews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by Jilsha on 26-03-2018.
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener{
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";
    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(attrs);
    }

    /**
     * set cutom font for EditText
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
        setOnFocusChangeListener(this);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void showKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            hideKeyboard(v);
        } else {
            if (v!=null) {
                showKeyboard(v);
            }
        }
    }
}
