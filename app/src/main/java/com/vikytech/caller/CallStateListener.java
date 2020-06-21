package com.vikytech.caller;

import android.os.Environment;
import android.telephony.PhoneStateListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class CallStateListener extends PhoneStateListener {
    LogViewerActivity logViewerActivity = LogViewerActivity.getInstace();

    void update(String text) {
        logViewerActivity.refreshCallLog();
        appendLog(text);
    }

    void appendLog(String text) {
        File logFile = new File(Environment.getExternalStorageDirectory() + "/call.log");

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
