package com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.idreamo.rrtoyewx.multiplethemelibrary.MultipleThemeUtils;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class TextColorAttr extends MultipleChangeAttr {


    public TextColorAttr(View targetView, int attrResId, Activity activity) {
        super(targetView, attrResId, activity);
    }

    @Override
    public void changeTheme(int newTheme) {
        Resources.Theme theme = mActivity.getTheme();
        int color = MultipleThemeUtils.getColorByTheme(theme, mAttrsResId);
        ((TextView) mTargetView).setTextColor(color);
    }
}
