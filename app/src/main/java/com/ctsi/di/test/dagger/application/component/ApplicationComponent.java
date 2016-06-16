package com.ctsi.di.test.dagger.application.component;

import android.content.Context;

import com.ctsi.di.test.dagger.application.CTSIApplication;
import com.ctsi.di.test.dagger.application.manager.CacheManager;
import com.ctsi.di.test.dagger.application.manager.FileManager;
import com.ctsi.di.test.dagger.application.module.AppModule;
import com.ctsi.di.test.dagger.application.scope.AppScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by doulala on 16/4/27.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {


    CacheManager getCacheManager();

    FileManager getFileManager();

    Context context();

    void inject(CTSIApplication application);

}
