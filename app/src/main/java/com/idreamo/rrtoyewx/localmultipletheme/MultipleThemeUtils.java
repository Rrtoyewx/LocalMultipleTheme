package com.idreamo.rrtoyewx.localmultipletheme;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class MultipleThemeUtils {
    private MultipleThemeUtils() {
    }

    public static Drawable getDrawableByTheme(Resources.Theme newTheme, int attrsResId) {
        TypedArray ta = newTheme.obtainStyledAttributes(new int[]{attrsResId});
        Drawable drawable = ta.getDrawable(0);
        return drawable;
    }

    public static int getColorByTheme(Resources.Theme newTheme, int attrsResId) {
        TypedValue value = new TypedValue();
        newTheme.resolveAttribute(attrsResId, value, true);
        return value.data;
    }


    public static float getSizeByTheme(Resources.Theme newTheme, int attrsResId,Context context) {
        TypedArray ta = newTheme.obtainStyledAttributes(new int[]{attrsResId});
        float dimension = ta.getDimensionPixelSize(0, -1);

        if (dimension != -1) {
             return px2sp(context,dimension);
        }
        return dimension;
    }


    public static int dp2px(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
