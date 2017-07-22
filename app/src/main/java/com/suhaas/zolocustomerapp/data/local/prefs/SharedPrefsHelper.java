package com.suhaas.zolocustomerapp.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.suhaas.zolocustomerapp.data.DataManager;
import com.suhaas.zolocustomerapp.di.ApplicationContext;
import com.suhaas.zolocustomerapp.di.PreferenceInfo;
import com.suhaas.zolocustomerapp.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by suhaas on 20/7/17.
 */

@Singleton
public class SharedPrefsHelper implements PreferencesHelper {

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private final SharedPreferences mPrefs;

    @Inject
    public SharedPrefsHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }
}
