package com.ctsi.di.test.dagger.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.ctsi.di.test.dagger.application.component.ApplicationComponent;
import com.ctsi.di.test.dagger.application.component.DaggerApplicationComponent;
import com.ctsi.di.test.dagger.application.manager.CacheManager;
import com.ctsi.di.test.dagger.application.module.AppModule;

import javax.inject.Inject;

/**
 * Created by doulala on 16/4/27.
 */
public class CTSIApplication extends MultiDexApplication {


    ApplicationComponent applicationComponent;

    @Inject
    CacheManager cacheManager;

    public static CTSIApplication get(Context context) {

        return (CTSIApplication) context.getApplicationContext();

    }


    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule(CTSIApplication.this)).build();
        applicationComponent.inject(this);
        Log.e("cacheMananger",cacheManager.toString());
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
