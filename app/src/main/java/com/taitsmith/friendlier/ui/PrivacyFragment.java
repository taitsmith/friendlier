package com.taitsmith.friendlier.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taitsmith.friendlier.R;
import com.taitsmith.friendlier.data.PrivacyViewModel;

public class PrivacyFragment extends Fragment {

    private PrivacyViewModel mViewModel;

    public static PrivacyFragment newInstance() {
        return new PrivacyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.privacy_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrivacyViewModel.class);
        // TODO: Use the ViewModel
    }

}
