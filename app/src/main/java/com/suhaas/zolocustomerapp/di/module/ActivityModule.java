package com.suhaas.zolocustomerapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.suhaas.zolocustomerapp.di.ActivityContext;
import com.suhaas.zolocustomerapp.di.PerActivity;
import com.suhaas.zolocustomerapp.ui.LoginPresenter;
import com.suhaas.zolocustomerapp.ui.forgotpassword.ForgotPasswordPresenter;
import com.suhaas.zolocustomerapp.ui.login.LoginFragmentPresenter;
import com.suhaas.zolocustomerapp.ui.register.RegisterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by suhaas on 20/7/17.
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter(
            LoginPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginFragmentPresenter provideLoginFragmentPresenter(
            LoginFragmentPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RegisterPresenter provideRegisterPresenter(
            RegisterPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ForgotPasswordPresenter provideForgotPasswordPresenter(
            ForgotPasswordPresenter presenter) {
        return presenter;
    }
}
