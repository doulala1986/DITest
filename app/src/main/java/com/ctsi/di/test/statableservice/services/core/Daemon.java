package com.ctsi.di.test.statableservice.services.core;

//import android.app.ActivityManagerNative;
//import android.app.IActivityManager;
//import android.app.IApplicationThread;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Looper;


import com.ctsi.di.test.statableservice.utils.LogUtil;
import com.ctsi.di.test.statableservice.utils.SysUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Map;

public final class Daemon {
    private static final String PROCESS = "com.ctsi.di.test:service";
    private static boolean sPower = true;
    private static final File FILE =
            new File(new File(Environment.getDataDirectory(), "data"), "com.ctsi.di.test");

    private Daemon() {
    }

    public static void main(String[] args) {
        Looper.prepare();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (sPower) {
                    String cmd = String.format("am startservice%s-n com.ctsi.di.test/" +
                                    "com.ctsi.di.test.statableservice.services.protect.DaemonService",
                            SysUtil.isAfter17() ? " --user 0 " : " ");
                    LogUtil.i("CMD exec " + cmd);
                    try {
                        Runtime.getRuntime().exec(cmd);
                    } catch (IOException e) {


                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        LogUtil.w("Thread sleep failed:" + e.toString());
                    }
                }
            }
        }).start();
        Looper.loop();
        LogUtil.i("====================Daemon exit with error====================");
    }

    public static void start(Context context) {
        LogUtil.i("====================Daemon will be start====================");
        File[] processes = new File("/proc").listFiles();
        for (File file : processes) {
            if (file.isDirectory()) {
                File cmd = new File(file, "cmdline");
                if (!cmd.exists())
                    continue;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(cmd));
                    String line = br.readLine();

                    LogUtil.w("line:"+line);
                    if (null != line && line.startsWith(PROCESS)) {
                        LogUtil.w("Daemon already running");
                        return;
                    }
                    br.close();
                } catch (IOException e) {
                    LogUtil.e("Check daemon running with error:" + e.toString());
                }
            }
        }
        ProcessBuilder builder = new ProcessBuilder();
        Map<String, String> env = builder.environment();
        String classpath = env.get("CLASSPATH");
        if (null == classpath)
            classpath = context.getPackageCodePath();
        else
            classpath = classpath + ":" + context.getPackageCodePath();
        env.put("CLASSPATH", classpath);
        builder.directory(new File("/"));
        try {
            Process process = builder.command("sh").redirectErrorStream(false).start();
            OutputStream os = process.getOutputStream();
            String cmd = "id\n";
            os.write(cmd.getBytes("utf8"));
            os.flush();
            LogUtil.i("Exec cmd " + cmd);
            cmd = "cd " + FILE.getAbsolutePath() + "\n";
            os.write(cmd.getBytes("utf8"));
            os.flush();
            LogUtil.i("Exec cmd " + cmd);
            cmd = "app_process / " + Daemon.class.getName() + " --nice-name=" + PROCESS + " &\n";
            os.write(cmd.getBytes("utf8"));
            os.flush();
            LogUtil.i("Exec cmd " + cmd);
            os.write("exit\n".getBytes("utf8"));
            os.flush();
            LogUtil.i("Exec cmd " + cmd);
        } catch (IOException e) {
            LogUtil.e("Exec cmd with error:" + e.toString());
        }
    }
}