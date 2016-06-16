package com.ctsi.di.test.parcelable;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by doulala on 16/5/6.
 */
public class Activity_Parcelable_B extends Activity {


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = Parcels.unwrap(getIntent().getParcelableExtra("parcelable"));

        HashMap<String, User> u = user.getModules();
        User user0 = u.get("1");
        Log.e("username", user.getName());
       //git commit test
    }
}
