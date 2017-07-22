package com.suhaas.zolocustomerapp.ui.login;

import com.suhaas.zolocustomerapp.data.DataManager;

import javax.inject.Inject;

/**
 * Created by suhaas on 22/7/17.
 */

public class LoginFragmentPresenter implements LoginFragmentMvpPresenter {

    private final DataManager dataManager;

    @Inject
    public LoginFragmentPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
