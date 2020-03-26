package com.bb.googlebooks.util;

import android.util.Log;

import static com.bb.googlebooks.util.Constants.TAG;

public class DebugLogger {

    public static void logError(Throwable throwable){
        Log.d(TAG, throwable.getLocalizedMessage());
    }

    public static void logDebug(String log){
        Log.d(TAG, log);
    }

}
