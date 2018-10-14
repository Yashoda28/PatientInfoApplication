package com.example.yashoda.patientinfoapplication2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

class CommonUtils {

    static void showMessage(final Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    static void Log(Exception se) {
        Log.e(se.getClass().getName(), se.getMessage());
    }

    static void handleException(Context context, Exception e, String message) {
        Log(e);
        showMessage(context, message);
    }
}
