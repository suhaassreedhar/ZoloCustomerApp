package com.suhaas.zolocustomerapp;

import android.app.Application;
import android.content.Context;

import com.suhaas.zolocustomerapp.data.DataManager;
import com.suhaas.zolocustomerapp.di.component.ApplicationComponent;
import com.suhaas.zolocustomerapp.di.component.DaggerApplicationComponent;
import com.suhaas.zolocustomerapp.di.module.ApplicationModule;

import javax.inject.Inject;

import io.realm.Realm;


/**
 * Created by suhaas on 20/7/17.
 */

public class CustomerApplication extends Application {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent applicationComponent;

    public static CustomerApplication get(Context context) {
        return (CustomerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
        Realm.init(this);
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
