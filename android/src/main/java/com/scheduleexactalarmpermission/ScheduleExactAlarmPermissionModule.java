package com.scheduleexactalarmpermission;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import com.facebook.react.bridge.Callback;
import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

@ReactModule(name = ScheduleExactAlarmPermissionModule.NAME)
public class ScheduleExactAlarmPermissionModule extends ReactContextBaseJavaModule {

    public static final String NAME = "ScheduleEA";
    private static final String TAG = "SEAModule";
    private final AlarmManager alarmManager;

    ScheduleExactAlarmPermissionModule (ReactApplicationContext context) {
        super(context);
        alarmManager = (AlarmManager) getReactApplicationContext().getSystemService(Context.ALARM_SERVICE);
    }


    @Override
    public String getName() {
        return NAME;
    }


    @ReactMethod
    public void checkPermission(Callback callback) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            boolean checkAlarmPermission = alarmManager.canScheduleExactAlarms();
            callback.invoke(checkAlarmPermission);
        } else {
            callback.invoke(true);
        }
    }

    @ReactMethod
    public void getPermission() {

        if (getReactApplicationContext() == null) {
            return;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            // If not, request the SCHEDULE_EXACT_ALARM permission
                Intent intent = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.fromParts("package", getReactApplicationContext().getPackageName(), null));
                getReactApplicationContext().startActivity(intent);
        }
    }

}
