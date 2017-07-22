package com.suhaas.zolocustomerapp.di.module;

import android.app.Application;
import android.content.Context;

import com.suhaas.zolocustomerapp.data.DataManager;
import com.suhaas.zolocustomerapp.di.ApplicationContext;
import com.suhaas.zolocustomerapp.di.PreferenceInfo;
import com.suhaas.zolocustomerapp.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by suhaas on 20/7/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }
}
