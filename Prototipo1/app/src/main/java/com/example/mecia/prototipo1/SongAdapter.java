package com.example.mecia.prototipo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter {

    List<Song> songItems;


    public SongAdapter(Song[] songs){
        songItems = new ArrayList<>();
        for (Song song: songs){
            songItems.add(song);

        }
    }

    public SongAdapter(List<Song> songs) {songItems = songs;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView =
                LayoutInflater.from(viewGroup.getContext()).
                        inflate(R.layout.music_row, viewGroup, false);

        return new SongViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

        Song song = songItems.get(i);

        SongViewHolder songViewHolder = (SongViewHolder) viewHolder;

        songViewHolder.musicaTextView.setText(song.getSongName());
        songViewHolder.albumTextView.setText(song.getAlbumName());
        songViewHolder.autorTextView.setText(song.getBandName());

//        ((SongViewHolder) viewHolder).deletarBotao.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Song itemAserDeletado = songItems.get(i);
//
//                songItems.remove(i);
//
//                notifyItemRemoved(i);
//
//                notifyItemRangeChanged(i, songItems.size());
//
//                Toast.makeText(null, "removed"+itemAserDeletado, Toast.LENGTH_SHORT).show();
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return songItems.size();
    }



    public void addItem(Song newSong){
        songItems.add(newSong);

        notifyItemInserted(songItems.size()-1);
    }



    public static class SongViewHolder extends RecyclerView.ViewHolder {
        public TextView musicaTextView;
        public TextView albumTextView;
        public TextView autorTextView;
//        public Button deletarBotao;


        public SongViewHolder(@NonNull View itemView) {
            super(itemView);

            musicaTextView = itemView.findViewById(R.id.musicaTextView);
            albumTextView = itemView.findViewById(R.id.albumTextView);
            autorTextView = itemView.findViewById(R.id.autorTextView);
//            deletarBotao = itemView.findViewById(R.id.deletarBotao);

        }


    }



}
