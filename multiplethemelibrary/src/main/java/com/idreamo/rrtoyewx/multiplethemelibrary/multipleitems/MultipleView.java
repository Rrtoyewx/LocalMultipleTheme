package com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.MultipleChangeAttr;

/**
 * Created by Rrtoyewx on 16/2/28.
 */
public class MultipleView extends MultipleItem {


    public MultipleView(View targetView, MultipleChangeAttr... attrs) {
        super(targetView, attrs);
    }

    @Override
    public boolean dispatchChangeThemeEvent(int newTheme) {
        for (int i = 0; i < mMultipleChangeAttrs.length; i++) {
            mMultipleChangeAttrs[i].changeTheme(newTheme);
        }
        return true;
    }
}
