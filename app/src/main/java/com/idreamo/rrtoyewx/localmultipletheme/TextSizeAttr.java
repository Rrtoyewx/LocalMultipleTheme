package com.idreamo.rrtoyewx.localmultipletheme;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class TextSizeAttr extends MultipleChangeAttr {


    public TextSizeAttr(View targetView, int attrResId, Activity activity) {
        super(targetView, attrResId, activity);
    }

    @Override
    public void changeTheme(int newTheme) {
        Resources.Theme theme = mActivity.getTheme();
        float textSize = MultipleThemeUtils.getSizeByTheme(theme, mAttrsResId,mActivity);
        ((TextView) mTargetView).setTextSize(textSize);
    }
}
