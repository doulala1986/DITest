package com.ctsi.di.test.dagger.biz.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.ctsi.di.test.dagger.biz.model.component.DaggerMainPageComponent;
import com.ctsi.di.test.dagger.biz.model.component.MainPageComponent;
import com.ctsi.di.test.dagger.biz.model.module.MainPageModule;
import com.ctsi.di.test.dagger.biz.presenter.MainPagePresenter;

import javax.inject.Inject;

/**
 * Created by doulala on 16/4/27.
 */
public class Activity_Main extends BaseActivity implements MainPagePresenter.MainPageView {

    private static final String TAG = "Activity_Main";

    MainPageComponent mainPageComponent;
    @Inject
    MainPagePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        mainPageComponent.inject(this);
        presenter.setMainPageView(this);
        presenter.login();

    }


    @Override
    public void loginSuccess() {

        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }
}
