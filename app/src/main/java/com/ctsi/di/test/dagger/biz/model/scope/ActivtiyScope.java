package com.ctsi.di.test.dagger.biz.model.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by doulala on 16/6/6.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivtiyScope {
}
