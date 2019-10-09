package com.example.android.earthquakeudacity;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

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
        mag.setText(formatMagnitude(currentEarthquake.getMagnitude()));
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        TextView nearTextView = listItemView.findViewById(R.id.near_text_view);
        TextView locationTextView = listItemView.findViewById(R.id.location_text_view);
        setLocation(currentEarthquake.getLocation(), nearTextView, locationTextView);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView date = listItemView.findViewById(R.id.date_text_view);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView time = listItemView.findViewById(R.id.time_text_view);
        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeRounded = (int) Math.floor(magnitude);
        int color;
        switch(magnitudeRounded){
            case 0:
            case 1: color = R.color.magnitude1;
                    break;
            case 2: color = R.color.magnitude2;
                    break;
            case 3: color = R.color.magnitude3;
                    break;
            case 4: color = R.color.magnitude4;
                    break;
            case 5: color = R.color.magnitude5;
                    break;
            case 6: color = R.color.magnitude6;
                    break;
            case 7: color = R.color.magnitude7;
                    break;
            case 8: color = R.color.magnitude8;
                    break;
            case 9: color = R.color.magnitude9;
                    break;
            case 10: color = R.color.magnitude10plus;
                    break;
            default: color = R.color.magnitude1;
                    break;
        }
        return ContextCompat.getColor(getContext(), color);
    }

    private void setLocation(String initialLocation,
                             TextView nearTextView,
                             TextView locationTextView){

        if (initialLocation.contains("km") && initialLocation.contains("of")){
            //(?<=) is called a positive lookaround in regular expressions
            //it makes the "of" be included in the split string
            String [] splitString = initialLocation.split("(?<=of )");
            nearTextView.setText(splitString[0]);
            locationTextView.setText(splitString[1]);
        } else{
            nearTextView.setText(R.string.near_of);
            locationTextView.setText(initialLocation);
        }
    }
}
