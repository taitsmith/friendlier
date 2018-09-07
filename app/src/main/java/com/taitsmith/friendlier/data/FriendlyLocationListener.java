package com.taitsmith.friendlier.data;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class FriendlyLocationListener implements LocationListener {
    Context context;
    private final String TAG = getClass().getSimpleName();

    public FriendlyLocationListener(Context context) {
        this.context = context;
    }
    @Override
    public void onLocationChanged(Location location) {
        double lat, lon;
        lat = location.getLatitude();
        lon = location.getLongitude();
        Toast.makeText(context, Double.toString(lat), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, " Status change: " + Integer.toString(status));
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, " Provider enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, " Provider disabled");
    }
}
