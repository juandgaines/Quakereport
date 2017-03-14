package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JuanDavid on 9/03/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>{

    public EarthquakeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Earthquake> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Earthquake mEarthquake= getItem(position);
        View listViewConvert=convertView;

        if (listViewConvert==null){

            listViewConvert= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView magView=(TextView) listViewConvert.findViewById(R.id.magtext);
        TextView locView1=(TextView) listViewConvert.findViewById(R.id.locationtext1);
        TextView locView2=(TextView) listViewConvert.findViewById(R.id.locationtext2);
        TextView dateView=(TextView) listViewConvert.findViewById(R.id.datetext);
        TextView timeView=(TextView) listViewConvert.findViewById(R.id.time);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format((int)mEarthquake.getMagnitude());
        setBackGround(mEarthquake, magView);

        String stringLocation=mEarthquake.getmLocation();
        String firstLocation;
        String secondLocationText;
        if(!stringLocation.contains(" of ")){

            firstLocation=getContext().getResources().getString(R.string.near_the);
            secondLocationText = stringLocation;
        }
        else {
            int index=stringLocation.indexOf("of ")+3;
            firstLocation = stringLocation.substring(0, index);
            secondLocationText = stringLocation.substring(index, stringLocation.length());
        }

        Date dateObject = new Date(mEarthquake.getmDate());
        String formattedDate = formatDate(dateObject);
        String formattedTime= formatTime(dateObject);

        locView1.setText(firstLocation);
        locView2.setText(secondLocationText);
        dateView.setText(formattedDate);
        timeView.setText(formattedTime);
        magView.setText(output);

        return listViewConvert;
    }

    private void setBackGround(Earthquake mEarthquake, TextView magView) {
        GradientDrawable magnitudeCircle =(GradientDrawable) magView.getBackground();
        int magnitudeColor = getMagnitudeColor((int)mEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
    }

    private int getMagnitudeColor(int magnitude) {
        switch (magnitude){
            case 0:
            case 1:
                return getContext().getResources().getColor(R.color.magnitude1);

            case 2:
                return getContext().getResources().getColor(R.color.magnitude2);

            case 3:
                return getContext().getResources().getColor(R.color.magnitude3);

            case 4:
                return getContext().getResources().getColor(R.color.magnitude4);

            case 5:
                return getContext().getResources().getColor(R.color.magnitude5);

            case 6:
                return getContext().getResources().getColor(R.color.magnitude6);

            case 7:
                return getContext().getResources().getColor(R.color.magnitude7);

            case 8:
                return getContext().getResources().getColor(R.color.magnitude8);

            case 9:
                return getContext().getResources().getColor(R.color.magnitude8);

            case 10:
                return getContext().getResources().getColor(R.color.magnitude9);

            default:
                return getContext().getResources().getColor(R.color.magnitude10plus);

        }
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
