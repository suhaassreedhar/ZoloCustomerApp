package com.suhaas.zolocustomerapp.di.module;

import android.app.Activity;
import android.content.Context;

import com.suhaas.zolocustomerapp.di.ActivityContext;

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
}
