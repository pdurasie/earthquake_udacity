package com.example.android.earthquakeudacity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView mag = listItemView.findViewById(R.id.mag_text_view);
        mag.setText(String.valueOf(currentEarthquake.getMagnitude()));

        TextView location = listItemView.findViewById(R.id.location_text_view);
        location.setText(currentEarthquake.getLocation());

        TextView date = listItemView.findViewById(R.id.date_text_view);
        date.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
