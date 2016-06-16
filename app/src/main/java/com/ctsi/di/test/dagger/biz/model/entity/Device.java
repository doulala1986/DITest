package com.ctsi.di.test.dagger.biz.model.entity;

import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * Created by doulala on 16/6/6.
 */
public class Device {
    String name;


    @Inject
    public Device(@Nullable  String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
