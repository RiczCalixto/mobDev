package com.example.mecia.prototipo1;

import android.os.Parcel;
import android.os.Parcelable;

public class Song {
    String songName;
    String albumName;
    String bandName;

    public Song(String songName, String albumName, String bandName) {
        this.songName = songName;
        this.albumName = albumName;
        this.bandName = bandName;
    }

    public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            this.songName = songName;
        }

        public String getAlbumName() {
            return albumName;
        }

        public void setAlbumName(String albumName) {
            this.albumName = albumName;
        }

        public String getBandName() {
            return bandName;
        }

        public void setBandName(String bandName) {
            this.bandName = bandName;
        }


    @Override
    public String toString() {
        return String.format("#\n%s\n%s\n%d\n%f\n", songName, albumName, bandName);
    }

}
