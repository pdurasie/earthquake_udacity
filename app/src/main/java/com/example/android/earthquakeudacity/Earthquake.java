package com.example.android.earthquakeudacity;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;

    public Earthquake(double mag, String location, long date, String url){
        mMagnitude = mag;
        mLocation = location;
        mDate = date;
        mUrl = url;
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

    public String getUrl() { return mUrl; }
}
