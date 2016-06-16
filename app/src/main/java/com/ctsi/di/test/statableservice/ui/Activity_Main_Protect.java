package com.ctsi.di.test.statableservice.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ctsi.di.test.statableservice.services.protect.DaemonService;

/**
 * Created by doulala on 16/5/9.
 */
public class Activity_Main_Protect extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, DaemonService.class));
//
//        PendingIntent intent = PendingIntent.getService(this, 0x123,
//                new Intent(this, DaemonService.class), PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, AlarmManager.INTERVAL_HALF_HOUR,
//                AlarmManager.INTERVAL_HALF_HOUR, intent);
    }
}