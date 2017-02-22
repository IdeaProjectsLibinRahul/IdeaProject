package tech.libin.rahul.ideaproject.views.utils;

import android.content.Context;

/**
 * Created by 10945 on 10/27/2016.
 */

public class ApplicationContextHolder {

    private static ApplicationContextHolder _instance;

    private Context mContext;

    public static ApplicationContextHolder getInstance() {
        if (_instance == null) {
            _instance = new ApplicationContextHolder();
        }
        return _instance;
    }

    /**
     * gets application context
     *
     * @return Context
     */
    public static Context getApplicationContext() {
        if (getInstance().mContext == null) {
            throw new IllegalArgumentException("application context can not be null. Use setContext() method to set Context.");
        }
        return getInstance().mContext;
    }

    /**
     * sets application context
     *
     * @param appContext
     */
    public void setContext(Context appContext) {
        this.mContext = appContext;
    }
}
