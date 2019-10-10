package com.example.android.earthquakeudacity;

import android.util.Log;

import com.example.android.earthquakeudacity.Earthquake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    public static final String SAMPLE_JSON_RESPONSE =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        try {
            JSONObject earthquakeResponse = new JSONObject(jsonResponse);
            JSONArray earthquakeFeatures = earthquakeResponse.getJSONArray("features");
            for (int i = 0; i < earthquakeFeatures.length(); i++){
                JSONObject currentEarthquake = earthquakeFeatures.getJSONObject(i);
                JSONObject earthquakeProperties = currentEarthquake.getJSONObject("properties");
                double mag = earthquakeProperties.getDouble("mag");
                String place = earthquakeProperties.getString("place");
                long time = earthquakeProperties.getLong("time");
                String url = earthquakeProperties.getString("url");

                //add new earthquake object to ArrayList
                earthquakes.add(new Earthquake(mag, place, time, url));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}
