package com.ctsi.di.test.dagger.biz.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ctsi.di.test.dagger.application.CTSIApplication;
import com.ctsi.di.test.dagger.application.manager.CacheManager;
import com.ctsi.di.test.dagger.biz.model.entity.Customer;
import com.ctsi.di.test.dagger.biz.model.entity.Device;
import com.ctsi.di.test.dagger.biz.model.module.SubModule;
import com.ctsi.di.test.dagger.biz.model.scope.HuaweiQualifier;
import com.ctsi.di.test.dagger.biz.model.scope.XiaomiQualifier;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

/**
 * Created by doulala on 16/6/6.
 */
public class Activity_Scope extends BaseActivity {

    @Inject
    @HuaweiQualifier
    Device device;

    @Inject
    @HuaweiQualifier
    Device device2;

    @Inject
    @XiaomiQualifier
    Device device3;


    @Inject
    Provider<Customer> customer;

    @Inject
    Lazy<Customer> lazyCustomer;


    @Inject
    CacheManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCommonComponent().plus(new SubModule()).inject(this);
//        Log.e("device", device.getName());
//        Customer c = customer.get();
//        Log.e("device", c.getName());
//        Customer lazyc = lazyCustomer.get();
//        Log.e("device", lazyc.getName());
    }
}
