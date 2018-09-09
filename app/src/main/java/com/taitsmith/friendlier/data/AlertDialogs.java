package com.taitsmith.friendlier.data;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

public class AlertDialogs {
    public Context context;
    static AlertDialog.Builder builder;
    static AlertDialog dialog;

    public AlertDialogs(Context context) {
        this.context = context;
        builder = new AlertDialog.Builder(context);
    }
}
