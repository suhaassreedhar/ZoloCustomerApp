package com.suhaas.zolocustomerapp.data.local.prefs;

import com.suhaas.zolocustomerapp.data.DataManager;

/**
 * Created by suhaas on 22/7/17.
 */

public interface PreferencesHelper {

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);
}
