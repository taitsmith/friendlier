package com.taitsmith.friendlier.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.taitsmith.friendlier.R;

import butterknife.ButterKnife;

import static com.taitsmith.friendlier.activities.FriendlierApplication.preferences;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        editor = preferences.edit();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            informUserSignIn();
        } else {
            Intent intent = new Intent(this, EditDetailsActivity.class);
            startActivity(intent);
        }

        //should we show the permission explanation?
        if (preferences.getBoolean("show_perm_dialog", false) &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            informUserPermissions();

            editor.putBoolean("show_perm_dialog", false);
            editor.apply();
        }
    }

    //explain to users why we're requesting various permissions
    private void informUserPermissions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false); //we need to be sure they've seen the explanation

        builder.setMessage(getString(R.string.dialog_request_location_permission));

        builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    //users are required to sign in before there's any functionality
    //we'll allow users to use google sign in or email/password.
    //TODO needs better authentication.
    private void informUserSignIn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false);

        builder.setMessage(R.string.dialog_inform_sign_in);

        builder.setPositiveButton("got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //request the permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION}, 13);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 13: {
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    //great, we're good to go. we'll store this in
                    editor.putBoolean("location_perm_granted", true);
                }
            }
        }
        editor.apply();
    }
}
