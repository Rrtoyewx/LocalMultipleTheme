package com.idreamo.rrtoyewx.localmultipletheme;

import android.app.Application;

/**
 * Created by Rrtoyewx on 16/2/29.
 */
public class LocalMultipleThemeApplication extends Application {

    @Override
    public void onCreate() {
        MultipleTheme.init();
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        MultipleTheme.newSingleInstance().clearAll();
        super.onTerminate();
    }


}
