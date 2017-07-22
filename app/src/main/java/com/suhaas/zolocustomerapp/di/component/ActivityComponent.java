package com.suhaas.zolocustomerapp.di.component;

import com.suhaas.zolocustomerapp.ui.forgotpassword.ForgotPasswordFragment;
import com.suhaas.zolocustomerapp.ui.LoginActivity;
import com.suhaas.zolocustomerapp.di.PerActivity;
import com.suhaas.zolocustomerapp.di.module.ActivityModule;
import com.suhaas.zolocustomerapp.ui.register.RegisterFragment;
import com.suhaas.zolocustomerapp.ui.login.LoginActivityFragment;
import com.suhaas.zolocustomerapp.ui.profile.ProfileActivity;
import com.suhaas.zolocustomerapp.ui.profile.ProfileActivityFragment;
import com.suhaas.zolocustomerapp.ui.profile.ProfileEditFragment;

import dagger.Component;

/**
 * Created by suhaas on 20/7/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);
    void inject(LoginActivityFragment loginActivityFragment);
    void inject(RegisterFragment registerFragment);
    void inject(ForgotPasswordFragment forgotPasswordFragment);
    void inject(ProfileActivity profileActivity);
    void inject(ProfileActivityFragment profileActivityFragment);
    void inject(ProfileEditFragment profileEditFragment);
}
