package com.ctsi.di.test.dagger.biz.model.module;

import android.content.Context;

import com.ctsi.di.test.dagger.biz.presenter.MainPagePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by doulala on 16/5/3.
 */


@Module
public class MainPageModule {


    Context context;

    public MainPageModule(Context context) {

        this.context = context;

    }


    @Provides
    MainPagePresenter provideMainPagePresenter() {
        return new MainPagePresenter(this.context);
    }


}
