package com.idreamo.rrtoyewx.localmultipletheme;

import android.content.res.Resources;
import android.provider.Settings;
import android.view.View;

/**
 * Created by Rrtoyewx on 16/2/28.
 */
public abstract class MultipleItem {
    protected View mTargetView;
    protected MultipleChangeAttr[] mMultipleChangeAttrs;
    protected boolean mIsDispatched  = false;


    public MultipleItem(View targetView, MultipleChangeAttr... attrs) {
        mTargetView = targetView;
        mMultipleChangeAttrs = attrs;
    }

    abstract boolean dispatchChangeThemeEvent(int themeId);


    public View getTargetView() {
        return mTargetView;
    }

    public void finishDispatchChangeThemeEvent(boolean isDispatched){
        setDispatchChangeThemeEvent(isDispatched);
    }

    public void setDispatchChangeThemeEvent(boolean isDispatched){
        mIsDispatched = isDispatched;
    }

    public boolean getIsDispatchChangeThemeEvent(){
        return mIsDispatched;
    }





}
