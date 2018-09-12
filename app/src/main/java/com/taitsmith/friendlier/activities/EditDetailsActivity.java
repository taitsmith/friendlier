package com.taitsmith.friendlier.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.taitsmith.friendlier.R;
import com.taitsmith.friendlier.ui.EditDetailsFragment;

public class EditDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EditDetailsFragment.newInstance())
                    .commitNow();
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            requestStoragePermission();
        }
    }

    private void requestStoragePermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(false); //we need to be sure they've seen the explanation

        builder.setMessage(getString(R.string.dialog_request_storage_permission));

        builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(EditDetailsActivity.this, new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE }, 420);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
