package com.ctsi.di.test.dagger.biz.model.component;

import com.ctsi.di.test.dagger.application.component.ApplicationComponent;
import com.ctsi.di.test.dagger.application.scope.ActivityScope;
import com.ctsi.di.test.dagger.biz.model.module.MainPageModule;
import com.ctsi.di.test.dagger.biz.presenter.MainPagePresenter;
import com.ctsi.di.test.dagger.biz.ui.Activity_Main;

import dagger.Component;

/**
 * Created by doulala on 16/5/3.
 */

@ActivityScope
@Component(dependencies = {ApplicationComponent.class}, modules = {MainPageModule.class})
public interface MainPageComponent {

    void inject(Activity_Main activity);

}
