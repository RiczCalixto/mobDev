package br.edu.infnet.travelapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Place> {

    public CustomArrayAdapter(@NonNull Context context,
                              int resource,
                              @NonNull List<Place> objects) {
        super(context, resource, objects);
    }

    public void addAndUpdate(Place place){
        add(place);
        notifyDataSetChanged();
    }

    public String getLatLong(String name){
        for (int i = 0; i < getCount(); i++){
            Place place = getItem(i);
            if (place.name.equals(name)){
                return place.getLatLong();
            }
        }
        return null;
    }
}


