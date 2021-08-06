package com.priyank.threetaps.activity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ComponentName LAUNCHER_COMPONENT_NAME = new ComponentName(
                "com.priyank.threetaps", "com.priyank.threetaps.activity.Launcher");

        getPackageManager().setComponentEnabledSetting(LAUNCHER_COMPONENT_NAME,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        finish();
    }
}