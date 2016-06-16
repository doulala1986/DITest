package com.ctsi.di.test.statableservice.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ctsi.di.test.statableservice.services.protect.DaemonService;
import com.ctsi.di.test.statableservice.services.safeprocess.DaemonService2;

/**
 * Created by doulala on 16/5/9.
 */
public class Activity_Main_SafeProcess extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, DaemonService2.class));

    }
}