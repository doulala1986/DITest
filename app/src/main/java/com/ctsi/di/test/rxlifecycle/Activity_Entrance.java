package com.ctsi.di.test.rxlifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by doulala on 16/6/14.
 */
public class Activity_Entrance extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this,Activity_LifeCycle.class);
        startActivity(intent);

    }
}
