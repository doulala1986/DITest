package com.ctsi.di.test.dagger.biz.model.entity;

import javax.inject.Inject;

/**
 * Created by doulala on 16/6/6.
 */
public class Customer {

    String name;

    @Inject
    public Customer(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
