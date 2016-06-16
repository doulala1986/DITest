package com.ctsi.di.test.dagger.biz.model.component;

import com.ctsi.di.test.dagger.biz.model.module.SubModule;
import com.ctsi.di.test.dagger.biz.model.scope.ActivtiyScope;
import com.ctsi.di.test.dagger.biz.ui.Activity_Scope;

import dagger.Subcomponent;

/**
 * Created by doulala on 16/6/13.
 */

@Subcomponent(modules ={SubModule.class})
public interface SubComponent {

    void inject(Activity_Scope activity);
}
