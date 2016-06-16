package com.ctsi.di.test.dagger.biz.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.ctsi.di.test.dagger.application.CTSIApplication;
import com.ctsi.di.test.dagger.application.component.ApplicationComponent;
import com.ctsi.di.test.dagger.biz.model.component.CommonComponent;
import com.ctsi.di.test.dagger.biz.model.component.DaggerCommonComponent;

/**
 * Created by doulala on 16/4/27.
 */
public class BaseActivity extends FragmentActivity {

    CommonComponent commonComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commonComponent = DaggerCommonComponent.builder().applicationComponent(CTSIApplication.get(this).getApplicationComponent()).build();


    }

    public CommonComponent getCommonComponent() {
        return commonComponent;
    }
}
