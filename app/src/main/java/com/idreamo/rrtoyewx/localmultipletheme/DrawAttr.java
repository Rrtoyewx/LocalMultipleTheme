package com.idreamo.rrtoyewx.localmultipletheme;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class DrawAttr extends MultipleChangeAttr {

    public DrawAttr(View targetView, int attrResId, Activity activity) {
        super(targetView, attrResId, activity);
    }

    @Override
    public void changeTheme(int newTheme) {
        Resources.Theme theme = mActivity.getTheme();
        Drawable drawable = MultipleThemeUtils.getDrawableByTheme(theme,mAttrsResId);
        mTargetView.setBackgroundDrawable(drawable);

    }

}
