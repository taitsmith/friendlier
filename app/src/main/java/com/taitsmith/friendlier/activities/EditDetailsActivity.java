package com.taitsmith.friendlier.activities;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}
