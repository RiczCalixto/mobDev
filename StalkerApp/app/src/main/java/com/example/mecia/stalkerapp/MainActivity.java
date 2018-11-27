package com.example.mecia.stalkerapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Geocoder geocoder;
    private TextView latView;
    private TextView lonView;
    private TextView accView;
    private TextView altView;
    private TextView addressView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null){
                updateLocationInfo(lastKnownLocation);
            }

        }

    }

    public void updateLocationInfo(Location location){
        latView = findViewById(R.id.latTextView);
        lonView = findViewById(R.id.longTextView);
        accView = findViewById(R.id.accuracyTextView);
        altView = findViewById(R.id.altitudeTextView);
        addressView = findViewById(R.id.adressTextView);

        latView.setText(String.format("Latitude: %.2f", location.getLatitude()));
        lonView.setText(String.format("Longitude: %.2f", location.getLongitude()));
        accView.setText(String.format("Accuracy: %.2f", location.getAccuracy()));
        altView.setText(String.format("Altitde: %.2f", location.getAltitude()));

        String address = "Could not find any address :(";


        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if(listAddress != null && listAddress.size() > 0){
                address = "Address:\n";
                if (listAddress.get(0).getThoroughfare() !=null ){
                    address+= listAddress.get(0).getThoroughfare()+"\n";
                }
                if (listAddress.get(0).getPostalCode() !=null ){
                    address+= listAddress.get(0).getPostalCode()+"\n";
                }
                if (listAddress.get(0).getLocality() !=null ){
                    address+= listAddress.get(0).getLocality()+"\n";
                }
                if (listAddress.get(0).getAdminArea() !=null ){
                    address+= listAddress.get(0).getAdminArea();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        addressView.setText(address);

    }

    public void startListening(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    } //metodo criado pra inserir no onRequestPremissionsResult - professor quis simplificar

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }
    }
}
