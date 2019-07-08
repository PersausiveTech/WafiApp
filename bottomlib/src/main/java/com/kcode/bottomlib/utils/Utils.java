package com.kcode.bottomlib.utils;

import android.content.Context;
import android.graphics.Typeface;
import com.kcode.bottomlib.R;


public class Utils {

    private static Typeface typefaceLatoRegular,typefaceLatoBold;


    /**
     * return Lato-Regular TypeFace.
     * @param context
     * @return Typeface
     */
    public static Typeface getTypefaceLatoRegular(Context context) {
        if (typefaceLatoRegular == null) {
            typefaceLatoRegular = getTypeface(context, context.getString(R.string.font_path_lato_regular));
        }
        return typefaceLatoRegular;
    }

    /**
     * create TypeFace with corresponding font path.
     * @param context
     * @param font_path
     * @return TypeFace
     */
    private static Typeface getTypeface(Context context, String font_path) {
        return Typeface.createFromAsset(context.getAssets(), font_path);
    }

    /**
     * return Lato-Bold TypeFace.
     * @param context
     * @return Typeface
     */
    public static Typeface getTypefaceLatoBold(Context context) {
        if (typefaceLatoBold == null) {
            typefaceLatoBold = getTypeface(context, context.getString(R.string.font_path_lato_bold));
        }
        return typefaceLatoBold;
    }



}
