package com.taitsmith.friendlier.data;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.taitsmith.friendlier.R;
import com.taitsmith.friendlier.activities.EditDetailsActivity;
import com.taitsmith.friendlier.activities.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModel;

import static com.taitsmith.friendlier.activities.FriendlierApplication.getFriendlierContext;


public class EditDetailsViewModel extends ViewModel {
    private Person me;
    private FirebaseUser user;
    private Context context;

    public void init(FirebaseUser user, Context context) {
        this.user = user;
        this.context = context;
    }

    public Person getMe() {
        me = new Person();
        me.setName(user.getDisplayName());
        me.setAccountPhotoUrl(user.getPhotoUrl().toString());

        return me;
    }

    //save the selected image to a firebase db under the user's uid
    public void uploadImage(Uri toUpload) {
        String uploadPath = String.format(context.getString(R.string.firebase_storage_base_path),
                user.getUid(), toUpload.getLastPathSegment());

        StorageReference reference = FirebaseStorage.getInstance().getReference();
        reference = reference.child(uploadPath);

        reference.putFile(toUpload)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //TODO this
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("UPLOAD ", e.toString());
                    }
                });
    }
}