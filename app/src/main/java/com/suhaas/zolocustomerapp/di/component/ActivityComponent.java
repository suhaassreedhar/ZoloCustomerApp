package com.suhaas.zolocustomerapp.di.component;

import com.suhaas.zolocustomerapp.ui.LoginActivity;
import com.suhaas.zolocustomerapp.di.PerActivity;
import com.suhaas.zolocustomerapp.di.module.ActivityModule;

import dagger.Component;

/**
 * Created by suhaas on 20/7/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);

}
