package com.idreamo.rrtoyewx.multiplethemelibrary;

import android.view.View;
import android.view.ViewGroup;

import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.MultipleChangeAttr;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems.MultipleItem;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems.MultipleView;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems.MultipleViewGroup;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class MultipleTheme {
    public static final int DISPATCH_CHILD_VIEW = Integer.MAX_VALUE;


    private Set<MultipleItem> mMultipleItems = new HashSet<>();
    private static MultipleTheme mInstance;

    public MultipleTheme() {
        mMultipleItems.clear();
    }


    public static void init() {
        if (mInstance == null) {
            synchronized (MultipleTheme.class) {
                if (mInstance == null) {
                    mInstance = new MultipleTheme();
                }
            }
        }
    }


    public static synchronized MultipleTheme newSingleInstance() {
        if (mInstance == null) {
            init();
        }
        return mInstance;
    }


    public MultipleTheme addMultipleItem(MultipleItem item) {
        mMultipleItems.add(item);
        return this;
    }


    public MultipleTheme addMultipleItem(ViewGroup targetView, boolean isDispatchChild, MultipleChangeAttr... attrs) {
        targetView.setTag(DISPATCH_CHILD_VIEW, isDispatchChild);
        mMultipleItems.add(new MultipleViewGroup(targetView, attrs));
        return this;
    }


    public MultipleTheme addMultipleItem(View targetView, MultipleChangeAttr... attrs) {
        mMultipleItems.add(new MultipleView(targetView, attrs));
        return this;
    }


    public MultipleTheme addMultipleItem(ViewGroup targetView, MultipleChangeAttr... attrs) {
        addMultipleItem(targetView, false, attrs);
        return this;
    }


    public void removeMultipleItem(View removeView) {
        for (MultipleItem item : mMultipleItems) {
            if (item.getTargetView() == removeView) {
                mMultipleItems.remove(item);
            }
        }
    }

    public void removeMultipleItem(MultipleItem item) {
        removeMultipleItem(item.getTargetView());
    }

    public boolean checkViewIsMultipleItems(View checkView) {

        for (MultipleItem item : mMultipleItems) {
            if (item.getTargetView() == checkView) {
                return true;
            }
        }
        return false;
    }

    public MultipleItem getMultipleItem(View checkView) {

        for (MultipleItem item : mMultipleItems) {
            if (item.getTargetView() == checkView) {
                return item;
            }
        }

        return null;
    }


    public void clearAll() {
        mMultipleItems.clear();
        mInstance = null;
    }

    public void startDispatchChangeTheme(int newTheme) {
        for (MultipleItem item : mMultipleItems) {
            boolean isDispatchChangeThemeEvent = item.getIsDispatchChangeThemeEvent();
            if (!isDispatchChangeThemeEvent) {
                isDispatchChangeThemeEvent = item.dispatchChangeThemeEvent(newTheme);
                item.finishDispatchChangeThemeEvent(isDispatchChangeThemeEvent);
            }
        }

        for (MultipleItem item : mMultipleItems) {
            item.setDispatchChangeThemeEvent(false);
        }
    }
}
