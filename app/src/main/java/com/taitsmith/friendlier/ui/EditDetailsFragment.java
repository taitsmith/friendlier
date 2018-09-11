package com.taitsmith.friendlier.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.taitsmith.friendlier.R;
import com.taitsmith.friendlier.data.EditDetailsViewModel;
import com.taitsmith.friendlier.data.Person;


public class EditDetailsFragment extends Fragment {

    private EditDetailsViewModel mViewModel;
    private Person me;
    private ImageView myProfilePhoto;
    private TextView myNameTV;

    public static EditDetailsFragment newInstance() {
        return new EditDetailsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.edit_details_fragment, container, false);
        myNameTV = rootView.findViewById(R.id.editDetailsNameTV);
        myProfilePhoto = rootView.findViewById(R.id.myProfilePhoto);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditDetailsViewModel.class);
        mViewModel.init(FirebaseAuth.getInstance().getCurrentUser());
        me = mViewModel.getMe();

        myNameTV.setText(me.getName());
        Picasso.get().load(me.getPhotoUrl()).into(myProfilePhoto);
    }
}
