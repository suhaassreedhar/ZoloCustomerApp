package com.suhaas.zolocustomerapp.di.component;

import android.app.Application;
import android.content.Context;

import com.suhaas.zolocustomerapp.CustomerApplication;
import com.suhaas.zolocustomerapp.di.ApplicationContext;
import com.suhaas.zolocustomerapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by suhaas on 20/7/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(CustomerApplication customerApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();
}
