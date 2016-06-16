package com.ctsi.di.test.dagger.biz.model.component;

import com.ctsi.di.test.dagger.application.component.ApplicationComponent;
import com.ctsi.di.test.dagger.biz.model.module.CommonModule;
import com.ctsi.di.test.dagger.biz.model.module.SubModule;
import com.ctsi.di.test.dagger.biz.model.scope.ActivtiyScope;

import dagger.Component;

/**
 * Created by doulala on 16/6/6.
 */
@ActivtiyScope
@Component(dependencies = ApplicationComponent.class, modules = {CommonModule.class})
public interface CommonComponent {

    SubComponent plus(SubModule subModule);

}
