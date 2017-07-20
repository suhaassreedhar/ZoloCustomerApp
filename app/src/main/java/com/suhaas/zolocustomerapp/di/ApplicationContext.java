package com.suhaas.zolocustomerapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by suhaas on 20/7/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
