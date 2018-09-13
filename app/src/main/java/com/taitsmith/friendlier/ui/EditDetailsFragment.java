package com.taitsmith.friendlier.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
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

import static android.app.Activity.RESULT_OK;
import static com.taitsmith.friendlier.activities.FriendlierApplication.getFriendlierContext;


public class EditDetailsFragment extends Fragment {

    private EditDetailsViewModel viewModel;
    private Person me;
    private ImageView myProfilePhoto, imageView2, imageView3;
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
        imageView2 = rootView.findViewById(R.id.imageView2);
        imageView3 = rootView.findViewById(R.id.imageView3);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(EditDetailsViewModel.class);
        viewModel.init(FirebaseAuth.getInstance().getCurrentUser(), getFriendlierContext());
        me = viewModel.getMe();

        myNameTV.setText(me.getName());

        Picasso.get().load(me.getAccountPhotoUrl()).into(myProfilePhoto);

        myProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 44);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 44 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();

            Picasso.get().load(selectedImage).into(myProfilePhoto);
            Picasso.get().load(selectedImage).into(imageView2);
            Picasso.get().load(selectedImage).into(imageView3);

            viewModel.uploadImage(selectedImage);
        }
    }
}

