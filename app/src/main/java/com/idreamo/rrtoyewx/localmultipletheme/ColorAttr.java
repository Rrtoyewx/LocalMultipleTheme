package com.idreamo.rrtoyewx.localmultipletheme;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class ColorAttr extends MultipleChangeAttr {


    public ColorAttr(View targetView, int attrResId, Activity activity) {
        super(targetView, attrResId, activity);
    }

    @Override
    public void changeTheme(int newTheme) {
        Resources.Theme theme = mActivity.getTheme();
        int color = MultipleThemeUtils.getColorByTheme(theme, mAttrsResId);
        mTargetView.setBackgroundColor(color);
    }


}
