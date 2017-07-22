package com.suhaas.zolocustomerapp.ui.register;

import com.suhaas.zolocustomerapp.data.DataManager;

import javax.inject.Inject;

/**
 * Created by suhaas on 22/7/17.
 */

public class RegisterPresenter implements RegisterMvpPresenter {

    private final DataManager dataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
