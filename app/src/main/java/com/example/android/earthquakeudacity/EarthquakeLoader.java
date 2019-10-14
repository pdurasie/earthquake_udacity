package com.example.android.earthquakeudacity;

import android.content.Context;
import android.util.Log;

import android.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        URL url = QueryUtils.createUrl(QueryUtils.SAMPLE_JSON_RESPONSE);
        String jsonResponse = "";

        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException e){
            Log.e("", "IOException thrown", e);
        }
        return QueryUtils.extractEarthquakes(jsonResponse);
    }
}