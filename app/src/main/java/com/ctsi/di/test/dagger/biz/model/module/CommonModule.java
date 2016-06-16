package com.ctsi.di.test.dagger.biz.model.module;

import com.ctsi.di.test.dagger.biz.model.entity.Device;
import com.ctsi.di.test.dagger.biz.model.scope.ActivtiyScope;
import com.ctsi.di.test.dagger.biz.model.scope.HuaweiQualifier;
import com.ctsi.di.test.dagger.biz.model.scope.XiaomiQualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doulala on 16/6/6.
 */


@Module
public class CommonModule {

    @Provides
    @ActivtiyScope
    @XiaomiQualifier
    Device providerXiaomi() {
        return new Device("xiaomi");
    }


    @Provides
    @ActivtiyScope
    @HuaweiQualifier
    Device providerHuawei() {
        return new Device("huawei");
    }





}
