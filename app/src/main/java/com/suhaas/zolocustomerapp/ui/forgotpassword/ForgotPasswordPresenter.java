package com.suhaas.zolocustomerapp.ui.forgotpassword;

import com.suhaas.zolocustomerapp.data.DataManager;

import javax.inject.Inject;

/**
 * Created by suhaas on 22/7/17.
 */

public class ForgotPasswordPresenter implements ForgotPresenterMvpPresenter{

    private final DataManager dataManager;

    @Inject
    public ForgotPasswordPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
