package com.priyank.threetaps.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VolumeControl extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MyService.class));
    }
}
