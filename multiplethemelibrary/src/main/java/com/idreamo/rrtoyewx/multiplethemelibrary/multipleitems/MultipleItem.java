package com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems;

import android.view.View;

import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.MultipleChangeAttr;

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

    public abstract boolean dispatchChangeThemeEvent(int themeId);


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
