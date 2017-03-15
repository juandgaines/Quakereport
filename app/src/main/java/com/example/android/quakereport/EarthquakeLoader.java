package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by JuanDavid on 15/03/2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        this.mUrl=url;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground( ) {
        if (mUrl == null) {
            return null;
        }
        List<Earthquake> mEvent= QueryUtils.fetchEarthquakeData(mUrl);
        return mEvent;
    }
}
