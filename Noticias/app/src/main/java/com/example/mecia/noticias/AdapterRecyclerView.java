package com.example.mecia.noticias;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<ViewHolder> {
    List<String> mNews;
    private RecyclerView.ViewHolder viewHolder;


    public AdapterRecyclerView(ArrayList news) { mNews = news; }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder
                (LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_item_recyclerview
                                , viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemViewH.setText("Casa");
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
