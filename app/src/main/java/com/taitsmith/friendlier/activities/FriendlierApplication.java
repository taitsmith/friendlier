package com.taitsmith.friendlier.activities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.taitsmith.friendlier.R;

import io.realm.Realm;

public class FriendlierApplication extends Application {
    public static final String PREFERENCE_NAME = "SharedPrefs";
    private static Context context;
    public static SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = this.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        FriendlierApplication.context = getApplicationContext();
        Realm.init(this);

        //check for the first run boolean to determine if users should
        //be shown the explanation of why we request permissions
        if (preferences.getBoolean("first_run", true)) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("first_run", false);
            editor.putBoolean("show_perm_dialog", true); //indicate we should show perm dialog

            editor.apply();
        }
    }

    public static Context getFriendlierContext() {
        return FriendlierApplication.context;
    }
}
