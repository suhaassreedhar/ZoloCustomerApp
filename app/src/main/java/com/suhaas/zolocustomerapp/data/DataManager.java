package com.suhaas.zolocustomerapp.data;

import android.content.Context;

import com.suhaas.zolocustomerapp.data.local.DbHelper;
import com.suhaas.zolocustomerapp.data.local.prefs.SharedPrefsHelper;
import com.suhaas.zolocustomerapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by suhaas on 20/7/17.
 */

@Singleton
public class DataManager implements DataManagerInterface{

    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mSharedPrefsHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mSharedPrefsHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public Long getCurrentUserId() {
        return mSharedPrefsHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mSharedPrefsHelper.setCurrentUserId(userId);
    }

    @Override
    public void updateUserInfo(
            Long userId,
            LoggedInMode loggedInMode) {

        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
    }
}
