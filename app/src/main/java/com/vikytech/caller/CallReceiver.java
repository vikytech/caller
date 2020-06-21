package com.vikytech.caller;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.telephony.TelephonyManager.EXTRA_INCOMING_NUMBER;
import static android.telephony.TelephonyManager.EXTRA_STATE;
import static android.telephony.TelephonyManager.EXTRA_STATE_RINGING;

public class CallReceiver extends BroadcastReceiver {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        CallStateListener customPhoneListener = new CallStateListener();


        telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        Bundle bundle = intent.getExtras();
        String phone_number = bundle.getString(EXTRA_INCOMING_NUMBER);

        String stateStr = intent.getExtras().getString(EXTRA_STATE);
        if (stateStr.equals(EXTRA_STATE_RINGING)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());
            String text = currentDateandTime + ": Call From: " + phone_number;
            Log.i("Call Log:", text);
            Toast.makeText(context, "Phone Number " + phone_number, Toast.LENGTH_SHORT).show();
            customPhoneListener.update(text);
        }
        if (phone_number == null || "".equals(phone_number)) {
            return;
        }
    }


}

