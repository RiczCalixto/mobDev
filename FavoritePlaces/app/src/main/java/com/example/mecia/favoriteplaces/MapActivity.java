package com.example.mecia.favoriteplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.CamcorderProfile;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Geocoder geocoder;

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
//        switch (item.getItemId()) {
//            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
//                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
//                finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
//                break;
//            default:
//                break;
//        }
//        return true;
//    }

    public void centerMapOnLocation(Location location, String title){
        if (location != null) {
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.clear();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocation, 16);
            mMap.addMarker(new MarkerOptions().position(currentLocation).title(title));
            mMap.animateCamera(cameraUpdate);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this); //usa o método la de baixo

        Intent intent = getIntent();

        if (intent.getIntExtra("placeNumber", 0) == 0){
            //Zoom in user location
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    centerMapOnLocation(location, "Your Location");
                    locationManager.removeUpdates(this);

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
                    centerMapOnLocation(lastKnownLocation, "Your Location");
                }
            }
        } else {
            Location placeLocation = new Location(LocationManager.GPS_PROVIDER);
            placeLocation.setLatitude(MainActivity.locationsLatLong.get(intent.getIntExtra("placeNumber", 0)).latitude);
            placeLocation.setLongitude(MainActivity.locationsLatLong.get(intent.getIntExtra("placeNumber", 0)).longitude);

            centerMapOnLocation(placeLocation, MainActivity.placesList.get(intent.getIntExtra("placeNumber", 0)));

        }

    }

    public void startListening(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListening();
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";

        try {
            List<Address> listAddress = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (listAddress != null && listAddress.size() > 0){
                if (listAddress.get(0).getThoroughfare() != null){
                    if (listAddress.get(0).getSubThoroughfare() !=null) {
                        address += listAddress.get(0).getSubThoroughfare() + " ";
                    }
                    address += listAddress.get(0).getThoroughfare();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        if(address.equals("")){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            address+= simpleDateFormat.format(new Date());//pega o tempo do dispositivo
        }
        mMap.addMarker(new MarkerOptions().position(latLng).title(address).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        MainActivity.placesList.add(address);
        MainActivity.locationsLatLong.add(latLng);

        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.mecia.favoriteplaces", Context.MODE_PRIVATE);

        try {
            //não da pra fazer o serialize do LatLng por se tratar de uma classe nao abordada, sendo assim, temos que fazer o seguinte:

            ArrayList<String> latitudes = new ArrayList<>();
            ArrayList<String> longitudes = new ArrayList<>();

            //criar um forEach loop pra add latitude e longitude a cada item da locationsLatLong
            for (LatLng coord : MainActivity.locationsLatLong){
                latitudes.add(Double.toString(coord.latitude));
                longitudes.add(Double.toString(coord.longitude));
            }

            sharedPreferences.edit().putString("placesSaving", ObjectSerializer.serialize(MainActivity.placesList)).apply();
            sharedPreferences.edit().putString("placeLats", ObjectSerializer.serialize(latitudes)).apply();
            sharedPreferences.edit().putString("placesLongs", ObjectSerializer.serialize(longitudes)).apply();



        } catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, address+" salvo com sucesso", Toast.LENGTH_SHORT).show();
        finish();
        }
    }