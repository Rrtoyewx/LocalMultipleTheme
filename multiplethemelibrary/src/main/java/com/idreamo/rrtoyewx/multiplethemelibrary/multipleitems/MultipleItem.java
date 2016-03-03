package com.idreamo.rrtoyewx.multiplethemelibrary.multipleitems;

import android.view.View;
import android.view.ViewGroup;

import com.idreamo.rrtoyewx.multiplethemelibrary.multipleattrs.MultipleChangeAttr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rrtoyewx on 16/2/28.
 */
public abstract class MultipleItem {
    protected View mTargetView;
    protected MultipleChangeAttr[] mMultipleChangeAttrs;
    protected boolean mIsDispatched = false;


    public MultipleItem(View targetView, MultipleChangeAttr... attrs) {
        mTargetView = targetView;
        mMultipleChangeAttrs = attrs;
    }

    public abstract boolean dispatchChangeThemeEvent(int themeId);


    public View getTargetView() {
        return mTargetView;
    }

    public void finishDispatchChangeThemeEvent(boolean isDispatched) {
        setDispatchChangeThemeEvent(isDispatched);
    }

    public void setDispatchChangeThemeEvent(boolean isDispatched) {
        mIsDispatched = isDispatched;
    }

    public boolean getIsDispatchChangeThemeEvent() {
        return mIsDispatched;
    }


    //针对一个由多个属性需要修改的view 简易创建
    public static class MultipleItemBuilder {
        public static final String PARAMETER_TARGETVIEW = "PARAMETER_TARGETVIEW";
        public static final String PARAMETER_ATTRS = "PARAMETER_ATTRS";
        public static final String PARAMETER_DISPATCH_CHILD = "PARAMETER_DISPATCH_CHILD";

        public Map<String, Object> mParameterMap = new HashMap<>();


        public MultipleItemBuilder() {
            mParameterMap.clear();
        }

        public MultipleItemBuilder setTargetView(View targetView) {
            mParameterMap.put(PARAMETER_TARGETVIEW, targetView);
            return this;
        }

        public MultipleItemBuilder addAttrs(MultipleChangeAttr attr) {
            if (mParameterMap.containsKey(PARAMETER_ATTRS)) {
                Set<MultipleChangeAttr> attrLinkList = (HashSet<MultipleChangeAttr>) mParameterMap.get(PARAMETER_ATTRS);
                attrLinkList.add(attr);
            } else {
                Set<MultipleChangeAttr> attrLinkList = new HashSet<>();
                attrLinkList.add(attr);
                mParameterMap.put(PARAMETER_ATTRS, attrLinkList);
            }
            return this;
        }


        //针对  MultipleViewGroup
        public MultipleItemBuilder setIfDispatchChild(boolean dispatchChild) {
            mParameterMap.put(PARAMETER_DISPATCH_CHILD, dispatchChild);
            return this;
        }


        public MultipleItem create() {
            View targetView = null;
            Set<MultipleChangeAttr> attrLinkList = null;
            boolean dispatchChild = false;


            try {
                targetView = getValueFromMap(PARAMETER_TARGETVIEW, View.class);
                attrLinkList = getValueFromMap(PARAMETER_ATTRS, Set.class);
                MultipleChangeAttr[] multipleChangeAttrs = attrLinkList.toArray(new MultipleChangeAttr[attrLinkList.size()]);
                if (targetView instanceof ViewGroup) {
                    try {
                        dispatchChild = getValueFromMap(PARAMETER_DISPATCH_CHILD, Boolean.class);
                    } catch (Exception e) {

                    }

                    return new MultipleViewGroup(targetView, dispatchChild, multipleChangeAttrs);
                } else {
                    return new MultipleView(targetView, multipleChangeAttrs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        private <T> T getValueFromMap(String key, Class<T> clazz) throws Exception {
            T t = null;

            if (mParameterMap.containsKey(key)) {
                t = (T) mParameterMap.get(key);
            }
            if (t == null) {
                throw new Exception("参数值为" + key + ",并没有传值");
            }

            return t;
        }
    }


}
