package com.priyank.threetaps.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.SystemClock;

public class MyService extends Service {
    private static BroadcastReceiver m_ScreenOffReceiver;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        registerScreenOffReceiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Intent broadcastIntent = new Intent("restart.volume.service.again");
        sendBroadcast(broadcastIntent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceTask = new Intent(getApplicationContext(), this.getClass());
        restartServiceTask.setPackage(getPackageName());
        PendingIntent restartPendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceTask, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager myAlarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        myAlarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartPendingIntent);

        super.onTaskRemoved(rootIntent);
    }

    private void registerScreenOffReceiver() {
        m_ScreenOffReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION")) {

                    int newVolume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
                    int oldVolume = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);

                    if (newVolume != oldVolume) {
                        if (isLauncherIconVisible()) {
                            fn_hideicon();
                        } else {
                            fn_unhide();
                        }
                    }
                }
            }
        };
        registerReceiver(m_ScreenOffReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
    }

    private static final ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
            "com.priyank.threetaps", "com.priyank.threetaps.activity.Launcher");

    private boolean isLauncherIconVisible() {
        int enabledSetting = getPackageManager().getComponentEnabledSetting(LAUNCHER_COMPONENT_NAME);
        return enabledSetting != PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
    }

    private void fn_hideicon() {
        getPackageManager().setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void fn_unhide() {
        PackageManager p = getPackageManager();
        p.setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}
