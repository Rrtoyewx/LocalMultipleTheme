package com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.idreamo.rrtoyewx.multiplethemelibrary.MultipleTheme;
import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.MultipleChangeAttr;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public boolean dispatchChangeThemeEvent(int newTheme) {
        //clear cache
        clearAbListViewRecyclerBin(mTargetView);
        clearRecyclerViewRecyclerBin(mTargetView);

        //change self
        for (MultipleChangeAttr attrs : mMultipleChangeAttrs) {
            attrs.changeTheme(newTheme);
        }

        // change child
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

    private void clearAbListViewRecyclerBin(View rootView) {
        if (rootView instanceof AbsListView) {
            try {
                Field localField = AbsListView.class
                        .getDeclaredField("mRecycler");
                localField.setAccessible(true);
                Method localMethod = Class.forName(
                        "android.widget.AbsListView$RecycleBin")
                        .getDeclaredMethod("clear", new Class[0]);
                localMethod.setAccessible(true);
                localMethod.invoke(localField.get(rootView), new Object[0]);
                Log.e("", "### 清空AbsListView的RecycerBin ");
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }
    }


    private void clearRecyclerViewRecyclerBin(View rootView) {
        if (rootView instanceof RecyclerView) {
            try {
                Field localField = RecyclerView.class
                        .getDeclaredField("mRecycler");
                localField.setAccessible(true);
                Method localMethod = Class.forName(
                        "android.support.v7.widget.RecyclerView$Recycler")
                        .getDeclaredMethod("clear", new Class[0]);
                localMethod.setAccessible(true);
                localMethod.invoke(localField.get(rootView), new Object[0]);
                Log.e("", "### 清空RecyclerView的Recycer ");
                rootView.invalidate();
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }
    }






}
