package com.suhaas.zolocustomerapp.ui;

import com.suhaas.zolocustomerapp.data.DataManager;

import javax.inject.Inject;

/**
 * Created by suhaas on 22/7/17.
 */

public class LoginPresenter implements LoginMvpPresenter {

    private final DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
