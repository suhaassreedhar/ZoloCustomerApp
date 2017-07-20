package com.suhaas.zolocustomerapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by suhaas on 20/7/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
