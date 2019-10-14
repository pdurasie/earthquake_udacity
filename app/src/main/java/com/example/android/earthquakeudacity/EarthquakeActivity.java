package com.example.android.earthquakeudacity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        final ListView earthquakeListView = findViewById(R.id.list);
        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute();

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake clickedItem =
                        (Earthquake) earthquakeListView.getItemAtPosition(position);
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedItem.getUrl()));
                startActivity(i);
            }
        });

    }

    private void updateUi(List<Earthquake> earthquakes){
        mAdapter.clear();
        if(earthquakes != null && !earthquakes.isEmpty()){
            mAdapter.addAll(earthquakes);
        }
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {
        @Override
        protected List<Earthquake> doInBackground(String... strings) {

            URL url = QueryUtils.createUrl(QueryUtils.SAMPLE_JSON_RESPONSE);
            String jsonResponse = "";

            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e){
                Log.e(LOG_TAG, "IOException thrown", e);
            }
            return QueryUtils.extractEarthquakes(jsonResponse);
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            updateUi(earthquakes);
        }


    }
}
