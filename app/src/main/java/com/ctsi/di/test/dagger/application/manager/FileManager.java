package com.ctsi.di.test.dagger.application.manager;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by doulala on 16/4/27.
 */
//@AppScope
public class FileManager {
    Context context;
    @Inject
    public FileManager(Context context){
        this.context=context;
        Log.e("inject","FileManager");

    }

    public Context getContext(){
        return context;

    }


}
