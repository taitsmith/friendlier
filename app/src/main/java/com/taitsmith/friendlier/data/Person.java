package com.taitsmith.friendlier.data;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * A person.
 *
 */
public class Person extends RealmObject {
    private String name, shortBio, accountPhotoUrl;
    private double lat, lon; //we'll keep the double that we return from getLastKnownLocation();
    private int age;
    private RealmList<String> photoUrls; //for the photos stored in firebase

    @PrimaryKey
    private int _id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAccountPhotoUrl() {
        return accountPhotoUrl;
    }

    public void setAccountPhotoUrl(String accountPhotoUrl) {
        this.accountPhotoUrl = accountPhotoUrl;
    }
}
