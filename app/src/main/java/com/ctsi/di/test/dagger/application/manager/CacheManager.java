package com.ctsi.di.test.dagger.application.manager;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by doulala on 16/4/27.
 */
public class CacheManager {
    @Inject
    public CacheManager(Context context) {

        Log.e("inject", "CacheManager");

    }


}
