package com.taitsmith.friendlier.data;

import android.app.AlertDialog;
import android.content.Context;

public class AlertDialogs {
    public Context context;
    static AlertDialog.Builder builder;
    static AlertDialog dialog;

    public AlertDialogs(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }
}
