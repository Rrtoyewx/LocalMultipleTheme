package com.idreamo.rrtoyewx.localmultipletheme;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;

/**
 * Created by Rrtoyewx on 16/2/28.
 */
public abstract class MultipleChangeAttr {
    protected View mTargetView;
    protected int mAttrsResId;
    protected Activity mActivity;


    public MultipleChangeAttr(View targetView, int attrResId,Activity activity) {
        mTargetView = targetView;
        mAttrsResId = attrResId;
        mActivity = activity;
    }


    public abstract void changeTheme(int newTheme);
}


