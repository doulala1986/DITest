package com.ctsi.di.test.dagger.biz.presenter;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by doulala on 16/5/3.
 */


public class MainPagePresenter {

    public interface MainPageView {
        void loginSuccess();
    }


    Context context;
    private MainPageView mainPageView;

    @Inject
    public MainPagePresenter(Context context) {

        this.context = context;

    }

    public void setMainPageView(MainPageView mainPageView) {
        this.mainPageView = mainPageView;
    }

    public void login() {
        if (mainPageView != null)
            mainPageView.loginSuccess();

    }
}
