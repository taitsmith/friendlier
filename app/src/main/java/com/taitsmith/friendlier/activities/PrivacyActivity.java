package com.taitsmith.friendlier.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.taitsmith.friendlier.R;
import com.taitsmith.friendlier.ui.PrivacyFragment;

public class PrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PrivacyFragment.newInstance())
                    .commitNow();
        }
    }
}
