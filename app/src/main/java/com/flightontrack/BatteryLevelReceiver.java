package com.flightontrack;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.telephony.SmsManager;
import static com.flightontrack.Const.*;
import android.util.Log;

import static com.flightontrack.Const.APPBOOT_DELAY_MILLISEC;
import static com.flightontrack.Const.FONT_RECEIVER_FILTER;

public class BatteryLevelReceiver extends BroadcastReceiver {
    private static final String TAG = "BatteryLevelReceiver:";

    @Override
    public void onReceive(Context context, Intent intent) {
        Util.appendLog(TAG + "onReceive intent: "+intent, 'd');
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale;
        String phoneNo = SMS_RECEIPIENT_PHONE;

        if (intent.getAction().contains("BATTERY_LOW")) {

            Util.appendLog(TAG + "BatteryPct low: "+batteryPct+" %", 'd');
            String message = Util.getUserName()+" "+ SMS_LOWBATTERY_TEXT;

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
        }
        if (intent.getAction().contains("BATTERY_OKAY")) {
            Util.appendLog(TAG + "Battery Restored: "+batteryPct +" %", 'd');
        }
        }
    }
