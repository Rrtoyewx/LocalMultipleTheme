package com.idreamo.rrtoyewx.localmultipletheme;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rrtoyewx on 16/2/28.
 */
public class MultipleViewGroup extends MultipleItem {
    private boolean mIsDispacthChildView;

    public MultipleViewGroup(View targetView, MultipleChangeAttr... attrs) {
        super(targetView, attrs);
    }

    public MultipleViewGroup(View targetView, boolean isDispacthChildView, MultipleChangeAttr... attrs) {
        this(targetView, attrs);
        mIsDispacthChildView = isDispacthChildView;
    }

    /**
     * @param newTheme
     * @return true dispatch sub view ,false   just dispatch viewGroup
     */
    @Override
    boolean dispatchChangeThemeEvent(int newTheme) {
        for (MultipleChangeAttr attrs : mMultipleChangeAttrs) {
            attrs.changeTheme(newTheme);
        }
        Log.e("TAG","MultipleViewGroup");
        if (mIsDispacthChildView) {
            ViewGroup targetViewGroup = (ViewGroup) mTargetView;
            for (int i = 0; i < targetViewGroup.getChildCount(); i++) {
                View childView = targetViewGroup.getChildAt(i);
                MultipleItem multipleItem = MultipleTheme.newSingleInstance().getMultipleItem(childView);
                boolean isDispatchChangeThemeEvent = multipleItem.getIsDispatchChangeThemeEvent();
                if (!isDispatchChangeThemeEvent) {
                    isDispatchChangeThemeEvent = multipleItem.dispatchChangeThemeEvent(newTheme);
                    multipleItem.finishDispatchChangeThemeEvent(isDispatchChangeThemeEvent);
                }
            }
            return true;
        }

        return false;
    }


    public void setIsDispacthChildView(boolean dispacthChildView) {
        mIsDispacthChildView = dispacthChildView;
    }


}
