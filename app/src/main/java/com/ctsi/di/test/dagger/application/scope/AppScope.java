package com.ctsi.di.test.dagger.application.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by doulala on 16/4/27.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {

}
