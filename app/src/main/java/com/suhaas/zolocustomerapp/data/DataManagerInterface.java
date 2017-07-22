package com.suhaas.zolocustomerapp.data;

import com.suhaas.zolocustomerapp.data.local.prefs.PreferencesHelper;

/**
 * Created by suhaas on 22/7/17.
 */

public interface DataManagerInterface extends PreferencesHelper{

    void setUserAsLoggedOut();

    void updateUserInfo(
            Long userId,
            LoggedInMode loggedInMode);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
