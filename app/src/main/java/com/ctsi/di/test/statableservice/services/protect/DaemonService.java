package com.ctsi.di.test.statableservice.services.protect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.ctsi.di.test.statableservice.services.core.Daemon;

public class DaemonService extends Service {
    private static boolean sPower = true, isRunning;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning) {
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (sPower) {
                        if (System.currentTimeMillis() >= 123456789000000L) {
                            sPower = false;
                        }
                        Daemon.start(DaemonService.this);
                        SystemClock.sleep(3000);
                    }
                }
            }).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}