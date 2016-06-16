package com.ctsi.di.test.statableservice.services.safeprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.ctsi.di.test.statableservice.services.core.Daemon;

import java.util.Timer;
import java.util.TimerTask;


/**
 * @author AigeStudio
 * @since 2016-05-05
 */
public class DaemonService2 extends Service {
    private static boolean sPower = true, isRunning;

    Timer timer = new Timer();

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Log.e("DaemonService2","alive");
                Toast.makeText(DaemonService2.this, "alive", Toast.LENGTH_SHORT).show();
            }
        }, 2, 3000);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("DaemonService2", "START");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}