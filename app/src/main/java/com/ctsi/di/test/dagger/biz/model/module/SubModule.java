package com.ctsi.di.test.dagger.biz.model.module;

import com.ctsi.di.test.dagger.biz.model.entity.Customer;
import com.ctsi.di.test.dagger.biz.model.scope.ActivtiyScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doulala on 16/6/12.
 */


@Module
public class SubModule {

    @Provides
    Customer provideCustomer() {
        return new Customer("liyao");
    }
}
