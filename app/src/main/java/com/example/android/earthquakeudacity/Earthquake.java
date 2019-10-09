package com.example.android.earthquakeudacity;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mDate;

    public Earthquake(double mag, String location, long date){
        mMagnitude = mag;
        mLocation = location;
        mDate = date;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getTimeInMilliseconds() {
        return mDate;
    }

    public String getLocation() {
        return mLocation;
    }
}
