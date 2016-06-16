package com.ctsi.di.test.dagger.application.module;

import android.content.Context;

import com.ctsi.di.test.dagger.application.manager.CacheManager;
import com.ctsi.di.test.dagger.application.scope.AppScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doulala on 16/4/27.
 */


@Module
public class AppModule {


    Context context;

    public AppModule(Context appContext) {

        this.context = appContext;

    }

    @Provides
    @Singleton
    public CacheManager provideCacheManager() {

        return new CacheManager(context);

    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }


}
