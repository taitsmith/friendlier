package com.taitsmith.friendlier.data;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;

import androidx.lifecycle.ViewModel;

public class EditDetailsViewModel extends ViewModel {
    private static final String TAG = EditDetailsViewModel.class.getSimpleName();
    private Person me;
    private FirebaseUser user;

    public void init(FirebaseUser user) {
        this.user = user;
    }

    public Person getMe() {
        me = new Person();
        me.setName(user.getDisplayName());

        try {
            me.setPhotoUrl(user.getPhotoUrl().toString());
        } catch (NullPointerException e) {
            Log.d(TAG, e.toString());
        }
        return me;
    }
}
